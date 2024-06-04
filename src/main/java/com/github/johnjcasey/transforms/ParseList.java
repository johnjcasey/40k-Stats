package com.github.johnjcasey.transforms;

import com.github.johnjcasey.data.StructuredArmyData.StructuredArmyData;
import com.github.johnjcasey.data.StructuredArmyList;
import com.github.johnjcasey.data.bcp.ArmyList;
import com.github.johnjcasey.data.bcp.EventWithPlayersAndLists;
import com.github.johnjcasey.data.bcp.PlayerAndList;
import com.github.johnjcasey.data.bcp.PlayerAtEvent;
import org.apache.beam.repackaged.core.org.apache.commons.lang3.tuple.Pair;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.github.johnjcasey.data.StructuredArmyData.StructuredArmyData.Faction.*;

public class ParseList extends PTransform<@NonNull PCollection<EventWithPlayersAndLists>, @NonNull PCollection<EventWithPlayersAndLists>> {

    @NotNull
    @Override
    public PCollection<EventWithPlayersAndLists> expand(@NonNull PCollection<EventWithPlayersAndLists> input) {
        return input.apply(ParDo.of(new ParseListFn()));
    }

    private Pair<Optional<StructuredArmyData.Faction>, Optional<StructuredArmyList.Unit>> parseBundle(List<String> bundle, List<String> dataSheets, List<KV<StructuredArmyData.Faction, List<String>>> allies, StructuredArmyData.DetachmentList detachment) {
        if (bundle.isEmpty()) {
            return Pair.of(Optional.empty(), Optional.empty());
        }
        String unitNameLine = bundle.get(0);
        StructuredArmyData.Faction additionalFaction = null;
        String sheet = null;
        String enhancement = null;
        String lowerUnit = unitNameLine.toLowerCase();
        List<String> matchingDatasheets = matchingDatasheets(dataSheets, lowerUnit);
        if (!matchingDatasheets.isEmpty()){
            sheet = matchingDatasheets.get(0);
        }
        if (null == sheet) {
            for (KV<StructuredArmyData.Faction, List<String>> ally : allies) {
                List<String> matchingAllyDatasheets = matchingDatasheets(ally.getValue(), lowerUnit);
                if (!matchingAllyDatasheets.isEmpty()){
                    sheet = matchingAllyDatasheets.get(0);
                }
                additionalFaction = ally.getKey();
            }
        }

        //Enhancements in GW are on a separate line
        enhancement = bundle.stream().filter(s -> s.contains("Enhancement")).findFirst().map(s -> {
            for (String detachmentEnhancement : detachment.getEnhancements()) {
                if (s.toLowerCase().contains(detachmentEnhancement.toLowerCase())) {
                    return detachmentEnhancement;
                }
            }
            return null;
        }).orElse(null);

        //Enhancements in Battlescribe are on the unit name line
        for (String detachmentEnhancement : detachment.getEnhancements()) {
            if (unitNameLine.contains(detachmentEnhancement)) {
                enhancement = detachmentEnhancement;
            }
        }

        if (null != sheet) {
            return Pair.of(Optional.ofNullable(additionalFaction), Optional.of(new StructuredArmyList.Unit(sheet, enhancement, String.join("\n", bundle))));
        } else {
            return Pair.of(Optional.empty(), Optional.empty());
        }
    }

    List<String> matchingDatasheets(List<String> dataSheets, String lowerUnit){
        return dataSheets.stream().map(String::toLowerCase).filter(lowerUnit::contains).sorted(Comparator.comparingInt(String::length).reversed()).toList();
    }

    private static class ParseException extends Exception {
        private ParseException(String exception) {
            super(exception);
        }
    }

    private final class ParseListFn extends DoFn<EventWithPlayersAndLists, EventWithPlayersAndLists> {
        @ProcessElement
        public void processElement(@Element EventWithPlayersAndLists epl, OutputReceiver<EventWithPlayersAndLists> outputReceiver) {
            EventWithPlayersAndLists newEpl = new EventWithPlayersAndLists();
            newEpl.event = epl.event;
            newEpl.playersWithList = new ArrayList<>();
            for (PlayerAndList pal : epl.playersWithList) {
                PlayerAndList newPal = new PlayerAndList();
                newPal.player = pal.player;
                newPal.list = pal.list;
                newEpl.playersWithList.add(newPal);
                ArmyList list = pal.list;
                try {
                    List<String> lines = list.armyListText.lines().toList();
                    StructuredArmyData.Faction declaredFaction = getDeclaredFaction(newPal.player);
                    StructuredArmyData.Faction parsedFaction = getParsedFaction(lines, declaredFaction);
                    StructuredArmyData.Faction factionForParsing;
                    if (null != parsedFaction) {
                        factionForParsing = parsedFaction;
                    } else if (null != declaredFaction) {
                        factionForParsing = parsedFaction;
                    } else {
                        throw new RuntimeException("No faction available for parsing");
                    }
                    StructuredArmyData.DetachmentList detachment = getDetachment(list.armyListText, factionForParsing.factionData.getDetachments());
                    StructuredArmyData.SubFaction subFaction = getSubFaction(list.armyListText);
                    StructuredArmyList armyList = new StructuredArmyList(list.userId, list.playerId, list.event, list.user, list.id, null == declaredFaction ? null : declaredFaction.name(), null == parsedFaction ? null : parsedFaction.name(), null == subFaction ? null : subFaction.name(), null == detachment ? null : detachment.getName());
                    populateUnits(lines, factionForParsing, detachment, armyList);
                    newPal.parsedList = armyList;
                } catch (Exception e) {
                    System.out.println("Unable to parse list: " + list + " Exception: " + e);
                }
            }
            outputReceiver.output(newEpl);
        }

        private StructuredArmyData.Faction getDeclaredFaction(PlayerAtEvent player) {
            if (null == player.army || null == player.army.name) {
                return null;
            }
            String factionName = player.army.name;
            for (StructuredArmyData.Faction maybeFaction : StructuredArmyData.Faction.values()) {
                for (String name : maybeFaction.names) {
                    if (factionName.toLowerCase().equals(name.toLowerCase())) {
                        return maybeFaction;
                    }
                }
            }
            return null;
        }

        private StructuredArmyData.Faction getParsedFaction(List<String> list, StructuredArmyData.Faction declaredFaction) throws ParseException {
            Set<StructuredArmyData.Faction> lineOneFactions = new HashSet<>();
            Set<StructuredArmyData.Faction> factions = new HashSet<>();
            StructuredArmyData.Faction firstFoundFaction = null;
            boolean isFirstLine = true;
            boolean probablyWTCHeader = false;
            for (String line : list) {
                for (StructuredArmyData.Faction maybeFaction : StructuredArmyData.Faction.values()) {
                    for (String name : maybeFaction.names) {
                        if (line.toLowerCase().contains(name.toLowerCase()) && !line.contains("Show/Hide") && !line.contains("ALLIED")) {
                            factions.add(maybeFaction);
                            if (null == firstFoundFaction) {
                                firstFoundFaction = maybeFaction;
                            }
                            if (line.toLowerCase().contains("used")) {
                                probablyWTCHeader = true;
                            }
                        }
                        if (isFirstLine && !factions.isEmpty()) {
                            lineOneFactions.addAll(factions);
                        }
                    }
                }
                isFirstLine = false;
            }
            if (factions.isEmpty()) {
                return null;
            }
            if (factions.size() == 1) {
                return factions.stream().toList().get(0);
            }
            if (!lineOneFactions.isEmpty() && factions.size() > lineOneFactions.size()) {
                factions.removeAll(lineOneFactions);
                if (factions.size() == 1) {
                    return factions.stream().toList().get(0);
                }
            }
            //prefer CSM over SM as the CSM name includes SM
            if (factions.contains(Space_Marines) && factions.contains(Chaos_Space_Marines)) {
                return Chaos_Space_Marines;
            }
            if (factions.contains(Aeldari) && factions.contains(Drukhari)) {
                for (String line : list) {
                    if (line.contains("Aeldari - Drukhari")) {
                        return Drukhari;
                    }
                }
                return firstFoundFaction;
            }
            // START Imperial Allies
            if (factions.size() == 3 && factions.contains(Imperial_Knights) && factions.contains(Agents_Of_The_Imperium)) {
                factions.remove(Imperial_Knights);
                factions.remove(Agents_Of_The_Imperium);
                return factions.stream().toList().get(0);
            }
            if (factions.size() == 2 && factions.contains(Imperial_Knights) && !factions.contains(Agents_Of_The_Imperium)) {
                factions.remove(Imperial_Knights);
                return factions.stream().toList().get(0);
            }
            // We assume no agents armies, and I'm not worried about the 3 people a year playing them for auto-parsing
            if (factions.size() == 2 && factions.contains(Agents_Of_The_Imperium)) {
                factions.remove(Agents_Of_The_Imperium);
                return factions.stream().toList().get(0);
            }
            // END Imperial Allies
            // START Chaos Allies
            if (factions.size() == 3 && factions.contains(Chaos_Daemons) && factions.contains(Chaos_Knights)) {
                factions.remove(Chaos_Daemons);
                factions.remove(Chaos_Knights);
                return factions.stream().toList().get(0);
            }
            if (factions.size() == 2 && factions.contains(Chaos_Daemons) && !factions.contains(Chaos_Knights)) {
                factions.remove(Chaos_Daemons);
                return factions.stream().toList().get(0);
            }
            if (factions.size() == 2 && !factions.contains(Chaos_Daemons) && factions.contains(Chaos_Knights)) {
                factions.remove(Chaos_Knights);
                return factions.stream().toList().get(0);
            }
            //Prefer the first of Daemons and Knights in the WTC Header
            if (factions.size() == 2 && factions.contains(Chaos_Daemons) && factions.contains(Chaos_Knights) && probablyWTCHeader) {
                for (String line : list) {
                    //grab the WTC Header line
                    if (line.toLowerCase().contains("used")) {
                        int daemonIndex = Integer.MAX_VALUE;
                        for (String name : Chaos_Daemons.names) {
                            daemonIndex = Integer.min(daemonIndex, line.indexOf(name));
                        }
                        int knightsIndex = Integer.MAX_VALUE;
                        for (String name : Chaos_Knights.names) {
                            knightsIndex = Integer.min(knightsIndex, line.indexOf(name));
                        }
                        if (-1 == daemonIndex && -1 == knightsIndex) {
                            throw new ParseException("Neither Daemons nor Knights could be found in the WTC Header");
                        }
                        if (-1 == daemonIndex) {
                            return Chaos_Knights;
                        }
                        if (-1 == knightsIndex) {
                            return Chaos_Daemons;
                        }
                        if (daemonIndex < knightsIndex) {
                            return Chaos_Daemons;
                        }
                        return Chaos_Knights;
                    }
                }
            }
            // END Chaos Allies

            //If we parse a faction, and it matches the declared faction, use that
            if (null != declaredFaction && factions.contains(declaredFaction)) {
                return declaredFaction;
            }

            System.out.println("Multiple Factions Found:" + factions);
            return null;
        }

        private StructuredArmyData.SubFaction getSubFaction(String list) throws ParseException {
            StructuredArmyData.SubFaction subFaction = null;
            for (StructuredArmyData.SubFaction maybeSubFaction : StructuredArmyData.SubFaction.values()) {
                if (list.toLowerCase().contains(maybeSubFaction.name.toLowerCase())) {
                    if (subFaction == null) {
                        subFaction = maybeSubFaction;
                    } else {
                        throw new ParseException("Multiple SubFactions Found");
                    }
                }
            }
            return subFaction;
        }

        private StructuredArmyData.DetachmentList getDetachment(String list, Class<? extends StructuredArmyData.DetachmentList> detachments) throws ParseException {
            StructuredArmyData.DetachmentList detachmentList = null;
            for (StructuredArmyData.DetachmentList maybeDetachmentList : detachments.getEnumConstants()) {
                if (list.toLowerCase().contains(maybeDetachmentList.getName().toLowerCase())) {
                    if (detachmentList == null) {
                        detachmentList = maybeDetachmentList;
                    } else {
                        throw new ParseException("Multiple Detachments Found: " + detachmentList + ", " + maybeDetachmentList);
                    }
                }
            }
            return detachmentList;
        }

        private void populateUnits(List<String> list, StructuredArmyData.Faction faction, StructuredArmyData.DetachmentList detachment, StructuredArmyList armyList) throws ParseException {
            List<String> dataSheets = new ArrayList<>(Arrays.stream(faction.factionData.getDataSheets().getEnumConstants()).map(StructuredArmyData.DataSheetList::getName).toList());
            List<KV<StructuredArmyData.Faction, List<String>>> allies = new ArrayList<>();
            for (StructuredArmyData.Faction ally : faction.factionData.getAllies()) {
                allies.add(KV.of(ally, Arrays.stream(ally.factionData.getDataSheets().getEnumConstants()).map(StructuredArmyData.DataSheetList::getName).toList()));
            }
            List<List<String>> bundled = new ArrayList<>();
            List<String> current = new ArrayList<>();
            //this splits the list into bundles. Some of them are garbage, but most should be full units
            //because of the limits of the parser, some units will have extra lines, such as "OTHER DATASHEETS" at the
            //end
            for (String line : list) {
                //This checks if a line starts with a letter, and contains [.*[1-9]{2,}.*] or (.*[1-9]{2,}.*).
                //This matches the first line of a datasheet in either the Battlescribe or the GW format
                if (line.matches("^.*[a-zA-Z].*(\\[.*[0-9]{2,}.*]|\\(.*[0-9]{2,}.*\\)).*")) {
                    bundled.add(current);
                    current = new ArrayList<>();
                }
                current.add(line);
            }
            for (List<String> bundle : bundled) {
                Pair<Optional<StructuredArmyData.Faction>, Optional<StructuredArmyList.Unit>> parsedUnit = parseBundle(bundle, dataSheets, allies, detachment);
                parsedUnit.getValue().ifPresent(armyList::addUnit);
                parsedUnit.getKey().ifPresent(armyList::addAlly);
            }
            if (armyList.units.isEmpty()) {
                throw new ParseException("Faction and Detachment have been determined, but no Units found:" + list);
            }
        }
    }
}
