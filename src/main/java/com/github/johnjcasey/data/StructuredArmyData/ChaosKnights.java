package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class ChaosKnights implements StructuredArmyData.FactionData {

    public static ChaosKnights INSTANCE = new ChaosKnights();

    private ChaosKnights() {
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
    public List<StructuredArmyData.Faction> getAllies(){
        return List.of(StructuredArmyData.Faction.Chaos_Daemons);
    }

    enum Detachments implements StructuredArmyData.DetachmentList {
        Traitoris_Lance("Traitoris Lance", List.of("Lord of Dread", "Aura of Terror", "The Traitor’s Mark", "Panoply of the Cursed Knight"));

        public final String name;

        public final List<String> enhancements;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<String> getEnhancements() {
            return enhancements;
        }


        Detachments(String name, List<String> enhancements) {
            this.name = name;
            this.enhancements = enhancements;
        }
    }

    public enum DataSheets implements StructuredArmyData.DataSheetList {
        Knight_Abominant("Knight Abominant"),
        Knight_Desecrator("Knight Desecrator"),
        Knight_Despoiler("Knight Despoiler"),
        Knight_Rampager("Knight Rampager"),
        Knight_Tyrant("Knight Tyrant"),
        War_Dog_Brigand("War Dog Brigand"),
        War_Dog_Executioner("War Dog Executioner"),
        War_Dog_Huntsman("War Dog Huntsman"),
        War_Dog_Karnivore("War Dog Karnivore"),
        War_Dog_Stalker("War Dog Stalker"),
        Chaos_Acastus_Knight_Asterius("Chaos Acastus Knight Asterius"),
        Chaos_Acastus_Knight_Porphyrion("Chaos Acastus Knight Porphyrion"),
        Chaos_Cerastus_Knight_Acheron("Chaos Cerastus Knight Acheron"),
        Chaos_Cerastus_Knight_Atrapos("Chaos Cerastus Knight Atrapos"),
        Chaos_Cerastus_Knight_Castigator("Chaos Cerastus Knight Castigator"),
        Chaos_Cerastus_Knight_Lancer("Chaos Cerastus Knight Lancer"),
        Chaos_Questoris_Knight_Magaera("Chaos Questoris Knight Magaera"),
        Chaos_Questoris_Knight_Styrix("Chaos Questoris Knight Styrix"),
        War_Dog_Moirax("War Dog Moirax");

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