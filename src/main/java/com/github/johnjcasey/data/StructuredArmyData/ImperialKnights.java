package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class ImperialKnights implements StructuredArmyData.FactionData {

    public static ImperialKnights INSTANCE = new ImperialKnights();

    private ImperialKnights() {
    }

    @Override
    public Class<? extends StructuredArmyData.DetachmentList> getDetachments() {
        return Detachments.class;
    }

    @Override
    public Class<? extends StructuredArmyData.DataSheetList> getDataSheets() {
        return DataSheets.class;
    }

    enum Detachments implements StructuredArmyData.DetachmentList {
        Noble_Lance("Noble Lance", List.of("Banner of Macharius Triumphant", "Mysterious Guardian", "Mythic Hero", "Revered Knight", "Unyielding Paragon"));

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
        Armiger_Helverin("Armiger Helverin"),
        Armiger_Warglaive("Armiger Warglaive"),
        Canis_Rex("Canis Rex"),
        Knight_Castellan("Knight Castellan"),
        Knight_Crusader("Knight Crusader"),
        Knight_Errant("Knight Errant"),
        Knight_Gallant("Knight Gallant"),
        Knight_Paladin("Knight Paladin"),
        Knight_Preceptor("Knight Preceptor"),
        Knight_Valiant("Knight Valiant"),
        Knight_Warden("Knight Warden"),
        Acastus_Knight_Asterius("Acastus Knight Asterius"),
        Acastus_Knight_Porphyrion("Acastus Knight Porphyrion"),
        Armiger_Moirax("Armiger Moirax"),
        Cerastus_Knight_Acheron("Cerastus Knight Acheron"),
        Cerastus_Knight_Atrapos("Cerastus Knight Atrapos"),
        Cerastus_Knight_Castigator("Cerastus Knight Castigator"),
        Cerastus_Knight_Lancer("Cerastus Knight Lancer"),
        Questoris_Knight_Magaera("Questoris Knight Magaera"),
        Questoris_Knight_Styrix("Questoris Knight Styrix");

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