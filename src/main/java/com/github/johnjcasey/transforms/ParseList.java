package com.github.johnjcasey.transforms;

import com.github.johnjcasey.data.StructuredArmyData.StructuredArmyData;
import com.github.johnjcasey.data.StructuredArmyList;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseList extends PTransform<@NonNull PCollection<String>, @NonNull PCollection<StructuredArmyList>> {

    @NotNull
    @Override
    public PCollection<StructuredArmyList> expand(@NonNull PCollection<String> input) {
        return input.apply(ParDo.of(new ParseListFn()));
    }

    private final class ParseListFn extends DoFn<String, StructuredArmyList> {
        @ProcessElement
        public void processElement(@Element String list, OutputReceiver<StructuredArmyList> outputReceiver) {
            try {
                StructuredArmyData.Faction faction = getFaction(list);
                StructuredArmyData.DetachmentList detachment = getDetachment(list, faction.factionData.getDetachments());
                StructuredArmyList armyList = new StructuredArmyList(faction, getSubFaction(list), detachment);
                populateUnits(list, faction.factionData.getDataSheets(), detachment, armyList);
                outputReceiver.output(armyList);
            } catch (ParseException e) {
                throw new RuntimeException("Unable to Parse List");
            }
        }

        private StructuredArmyData.Faction getFaction(String list) throws ParseException {
            StructuredArmyData.Faction faction = null;
            for (StructuredArmyData.Faction maybeFaction : StructuredArmyData.Faction.values()) {
                if (list.contains(maybeFaction.name)) {
                    if (faction == null) {
                        faction = maybeFaction;
                    } else {
                        throw new ParseException("Multiple Factions Found");
                    }
                }
            }
            if (faction == null) {
                throw new ParseException("No Faction Found");
            }
            return faction;
        }

        private StructuredArmyData.SubFaction getSubFaction(String list) throws ParseException {
            StructuredArmyData.SubFaction subFaction = null;
            for (StructuredArmyData.SubFaction maybeSubFaction : StructuredArmyData.SubFaction.values()) {
                if (list.contains(maybeSubFaction.name)) {
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
                if (list.contains(maybeDetachmentList.getName())) {
                    if (detachmentList == null) {
                        detachmentList = maybeDetachmentList;
                    } else {
                        throw new ParseException("Multiple Detachments Found");
                    }
                }
            }
            return detachmentList;
        }

        private void populateUnits(String list, Class<? extends StructuredArmyData.DataSheetList> dataSheetList, StructuredArmyData.DetachmentList detachment, StructuredArmyList armyList) {
            List<String> dataSheets = Arrays.stream(dataSheetList.getEnumConstants()).map(StructuredArmyData.DataSheetList::getName).toList();
            List<String> lines = list.lines().toList();
            List<List<String>> bundled = new ArrayList<>();
            List<String> current = new ArrayList<>();
            //this splits the list into bundles. Some of them are garbage, but most should be full units
            //because of the limits of the parser, some units will have extra lines, such as "OTHER DATASHEETS" at the
            //end
            for (String line : lines) {
                //This checks if a line starts with a letter, and contains [.*] or (.*).
                //This matches the first line of a datasheet in either the Battlescribe or the GW format
                if (line.matches("^[a-zA-Z].*(\\[.*]|\\(.*\\)).*")) {
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
        }
    }

    private StructuredArmyList.Unit parseBundle(List<String> bundle, List<String> dataSheets, StructuredArmyData.DetachmentList detachment) {
        String unitNameLine = bundle.get(0);
        String sheet = null;
        String enhancement = null;
        for (String dataSheet : dataSheets) {
            if (unitNameLine.contains(dataSheet)) {
                sheet = dataSheet;
            }
        }
        //Enhancements in GW are on a separate line
        enhancement = bundle.stream().filter(s -> s.contains("Enhancement")).findFirst().map(s -> {
            for (String detachmentEnhancement : detachment.getEnhancements()) {
                if (unitNameLine.contains(detachmentEnhancement)) {
                    return detachmentEnhancement;
                }
            }
            throw new RuntimeException("Line with Enhancement found, but no matching enhancement");
        }).orElse(null);

        //Enhancements in Battlescribe are on the unit name line
        for (String detachmentEnhancement : detachment.getEnhancements()) {
            if (unitNameLine.contains(detachmentEnhancement)) {
                enhancement = detachmentEnhancement;
            }
        }

        if (null != sheet) {
            return new StructuredArmyList.Unit(sheet, enhancement);
        } else {
            return null;
        }
    }

    private static class ParseException extends Exception {
        private ParseException(String exception) {
            super(exception);
        }
    }
}
