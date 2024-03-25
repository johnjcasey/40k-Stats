package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class Drukhari implements StructuredArmyData.FactionData {

    public static Drukhari INSTANCE = new Drukhari();

    private Drukhari() {
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
        return List.of(StructuredArmyData.Faction.Aeldari);
    }

    enum Detachments implements StructuredArmyData.DetachmentList {
        Realspace_Raiders("Realspace Raiders", List.of("Crucible of Malediction", "Blood Dancer", "Labyrinthine Cunning", "The Art of Pain")),
        Skysplinter_Assault("Skysplinter Assault", List.of("Nightmare Shroud", "Phantasmal Smoke", "Sadistic Fulcrum", "Spiteful Raider"));


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
        Archon("Archon"),
        Beastmaster("Beastmaster"),
        Court_of_the_Archon("Court of the Archon"),
        Cronos("Cronos"),
        Drazhar("Drazhar"),
        Grotesques("Grotesques"),
        Haemonculus("Haemonculus"),
        Hellions("Hellions"),
        Incubi("Incubi"),
        Kabalite_Warriors("Kabalite Warriors"),
        Lelith_Hesperax("Lelith Hesperax"),
        Mandrakes("Mandrakes"),
        Raider("Raider"),
        Ravager("Ravager"),
        Razorwing_Jetfighter("Razorwing Jetfighter"),
        Reavers("Reavers"),
        Scourges("Scourges"),
        Succubus("Succubus"),
        Talos("Talos"),
        Urien_Rakarth("Urien Rakarth"),
        Venom("Venom"),
        Voidraven_Bomber("Voidraven Bomber"),
        Wracks("Wracks"),
        Wyches("Wyches"),
        Tantalus("Tantalus");

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