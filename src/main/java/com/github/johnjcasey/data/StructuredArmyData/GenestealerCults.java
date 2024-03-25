package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class GenestealerCults implements StructuredArmyData.FactionData {

    public static GenestealerCults INSTANCE = new GenestealerCults();

    private GenestealerCults() {
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
        return List.of(StructuredArmyData.Faction.Astra_Militarum);
    }

    enum Detachments implements StructuredArmyData.DetachmentList {
        Ascension_Day("Ascension Day", List.of("Focus of Adoration", "Inscrutable Cunning", "Meticulous Planner", "Prowling Agitant"));

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
        Aberrants("Aberrants"),
        Abominant("Abominant"),
        Achilles_Ridgerunners("Achilles Ridgerunners"),
        Acolyte_Hybrids("Acolyte Hybrids"),
        Acolyte_Iconward("Acolyte Iconward"),
        Atalan_Jackals("Atalan Jackals"),
        Biophagus("Biophagus"),
        Clamavus("Clamavus"),
        Goliath_Rockgrinder("Goliath Rockgrinder"),
        Goliath_Truck("Goliath Truck"),
        Hybrid_Metamorphs("Hybrid Metamorphs"),
        Jackal_Alphus("Jackal Alphus"),
        Kelermorph("Kelermorph"),
        Locus("Locus"),
        Magus("Magus"),
        Neophyte_Hybrids("Neophyte Hybrids"),
        Nexos("Nexos"),
        Patriarch("Patriarch"),
        Primus("Primus"),
        Purestrain_Genestealers("Purestrain Genestealers"),
        Reductus_Saboteur("Reductus Saboteur"),
        Sanctus("Sanctus");

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