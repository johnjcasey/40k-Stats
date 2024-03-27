package com.github.johnjcasey.transforms;

import com.github.johnjcasey.data.StructuredArmyData.StructuredArmyData;
import com.github.johnjcasey.data.StructuredArmyList;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.github.johnjcasey.data.StructuredArmyData.StructuredArmyData.Faction.*;

public class ParseList extends PTransform<@NonNull PCollection<KV<String, String>>, @NonNull PCollection<StructuredArmyList>> {

    @NotNull
    @Override
    public PCollection<StructuredArmyList> expand(@NonNull PCollection<KV<String, String>> input) {
        return input.apply(ParDo.of(new ParseListFn()));
    }

    private StructuredArmyList.Unit parseBundle(List<String> bundle, List<String> dataSheets, StructuredArmyData.DetachmentList detachment) {
        if (bundle.isEmpty()) {
            return null;
        }
        String unitNameLine = bundle.get(0);
        String sheet = null;
        String enhancement = null;
        for (String dataSheet : dataSheets) {
            if (unitNameLine.toLowerCase().contains(dataSheet.toLowerCase())) {
                sheet = dataSheet;
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
            return new StructuredArmyList.Unit(sheet, enhancement, String.join("\n", bundle));
        } else {
            return null;
        }
    }

    private static class ParseException extends Exception {
        private ParseException(String exception) {
            super(exception);
        }
    }

    private final class ParseListFn extends DoFn<KV<String, String>, StructuredArmyList> {
        @ProcessElement
        public void processElement(@Element KV<String, String> list, OutputReceiver<StructuredArmyList> outputReceiver) {
            try {
                List<String> lines = list.getValue().lines().toList();
                StructuredArmyData.Faction faction = getFaction(lines);
                StructuredArmyData.DetachmentList detachment = getDetachment(list.getValue(), faction.factionData.getDetachments());
                StructuredArmyData.SubFaction subFaction = getSubFaction(list.getValue());
                StructuredArmyList armyList = new StructuredArmyList(list.getKey(), faction.name(), null == subFaction ? null : subFaction.name(), null == detachment ? null : detachment.getName());
                populateUnits(lines, faction, detachment, armyList);
                outputReceiver.output(armyList);
            } catch (Exception e) {
                System.out.println("Unable to parse list: " + list + " Exception: " + e);
            }
        }

        private StructuredArmyData.Faction getFaction(List<String> list) throws ParseException {
            Set<StructuredArmyData.Faction> lineOneFactions = new HashSet<>();
            Set<StructuredArmyData.Faction> factions = new HashSet<>();
            boolean isFirstLine = true;
            for (String line : list) {
                for (StructuredArmyData.Faction maybeFaction : StructuredArmyData.Faction.values()) {
                    for (String name : maybeFaction.names) {
                        if (line.toLowerCase().contains(name.toLowerCase()) && !line.contains("Show/Hide") && !line.contains("ALLIED")) {
                            factions.add(maybeFaction);
                        }
                        if (isFirstLine && !factions.isEmpty()) {
                            lineOneFactions.addAll(factions);
                        }
                    }
                }
                isFirstLine = false;
            }
            if (factions.isEmpty()) {
                throw new ParseException("No Faction Found");
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
                //Prefer Aeldari as some drukhari weapons include the name drukhari
                return Aeldari;
            }
            if (factions.size() == 2 && factions.contains(Agents_Of_The_Imperium)) {
                factions.remove(Agents_Of_The_Imperium);
                return factions.stream().toList().get(0);
            }
            throw new ParseException("Multiple Factions Found:" + factions);
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
                        throw new ParseException("Multiple Detachments Found");
                    }
                }
            }
            return detachmentList;
        }

        private void populateUnits(List<String> list, StructuredArmyData.Faction faction, StructuredArmyData.DetachmentList detachment, StructuredArmyList armyList) throws ParseException {
            List<String> dataSheets = new ArrayList<>(Arrays.stream(faction.factionData.getDataSheets().getEnumConstants()).map(StructuredArmyData.DataSheetList::getName).toList());
            for (StructuredArmyData.Faction ally : faction.factionData.getAllies()) {
                dataSheets.addAll(Arrays.stream(ally.factionData.getDataSheets().getEnumConstants()).map(StructuredArmyData.DataSheetList::getName).toList());
            }
            List<List<String>> bundled = new ArrayList<>();
            List<String> current = new ArrayList<>();
            //this splits the list into bundles. Some of them are garbage, but most should be full units
            //because of the limits of the parser, some units will have extra lines, such as "OTHER DATASHEETS" at the
            //end
            for (String line : list) {
                //This checks if a line starts with a letter, and contains [.*] or (.*).
                //This matches the first line of a datasheet in either the Battlescribe or the GW format
                if (line.matches("^.*[a-zA-Z].*(\\[.*]|\\(.*\\)).*")) {
                    bundled.add(current);
                    current = new ArrayList<>();
                }
                current.add(line);
            }
            for (List<String> bundle : bundled) {
                StructuredArmyList.Unit parsedUnit = parseBundle(bundle, dataSheets, detachment);
                if (null != parsedUnit) {
                    armyList.addUnit(parsedUnit);
                }
            }
            if (armyList.units.isEmpty()) {
                throw new ParseException("Faction and Detachment have been determined, but no Units found:" + list);
            }
        }
    }
}
