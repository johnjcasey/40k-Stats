package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class LeaguesOfVotann implements StructuredArmyData.FactionData {

    public static LeaguesOfVotann INSTANCE = new LeaguesOfVotann();

    private LeaguesOfVotann() {
    }

    @Override
    public Class<? extends StructuredArmyData.DetachmentList> getDetachments() {
        return Detachments.class;
    }

    @Override
    public Class<? extends StructuredArmyData.DataSheetList> getDataSheets() {
        return DataSheets.class;
    }

    @Override
    public List<StructuredArmyData.Faction> getAllies() {
        return List.of();
    }

    enum Detachments implements StructuredArmyData.DetachmentList {
        Oathband("Oathband", List.of("A Long List", "Appraising Glare", "Grim Demeanour", "Wayfarer’s Grace"));

        public final String name;

        public final List<String> enhancements;

        Detachments(String name, List<String> enhancements) {
            this.name = name;
            this.enhancements = enhancements;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<String> getEnhancements() {
            return enhancements;
        }
    }

    public enum DataSheets implements StructuredArmyData.DataSheetList {
        Brôkhyr_Iron_master("Brôkhyr Iron-master"),
        Brôkhyr_Thunderkyn("Brôkhyr Thunderkyn"),
        Cthonian_Beserks("Cthonian Beserks"),
        Einhyr_Champion("Einhyr Champion"),
        Einhyr_Hearthguard("Einhyr Hearthguard"),
        Grimnyr("Grimnyr"),
        Hearthkyn_Warriors("Hearthkyn Warriors"),
        Hekaton_Land_Fortress("Hekaton Land Fortress"),
        Hernkyn_Pioneers("Hernkyn Pioneers"),
        Kâhl("Kâhl"),
        Sagitaur("Sagitaur"),
        Ûthar_the_Destined("Ûthar the Destined");

        public String name;

        DataSheets(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}