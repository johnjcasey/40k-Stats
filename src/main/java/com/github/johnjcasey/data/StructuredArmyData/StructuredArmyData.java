package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class StructuredArmyData {
    public enum Faction {
        Adeptus_Custodes("Adeptus Custodes", AdeptusCustodes.INSTANCE),
        Grey_Knights("Grey Knights", null),
        Adeptus_Mechanicus("Adeptus Mechanicus", AdeptusMechanicus.INSTANCE),
        Astra_Militarum("Astra Militarum", AstraMilitarum.INSTANCE),
        Imperial_Knights("Imperial Knights", null),
        Adepta_Sororitas("Adepta Sororitas", AdeptaSororitas.INSTANCE),
        Space_Marines("Space Marines", null),
        World_Eaters("World Eaters", WorldEaters.INSTANCE),
        Thousand_Sons("Thousand Sons", null),
        Chaos_Space_Marines("Chaos Space Marines", null),
        Death_Guard("Death Guard", null),
        Chaos_Knights("Chaos Knights", null),
        Chaos_Daemons("Chaos Daemons", null),
        Tau_Empire("T'au Empire", null),
        Necrons("Necrons", null),
        Leagues_Of_Votann("Leagues Of Votann", null),
        Orks("Orks", null),
        Genestealer_Cult("Genestealer Cult", null),
        Drukhari("Drukhari", null),
        Aeldari("Aeldari", Aeldari.INSTANCE),
        Tyranids("Tyranids", null);

        public final String name;

        public final FactionData factionData;

        Faction(String name, FactionData factionData) {
            this.name = name;
            this.factionData = factionData;
        }
    }

    public enum SubFaction {
        Black_Templars("Black Templars"),
        Blood_Angels("Blood Angels"),
        Deathwatch("Deathwatch"),
        Space_Wolves("Space Wolves"),
        Dark_Angels("Dark Angels"),
        Ultramarines("Ultramarines"),
        Imperial_Fists("Imperial Fists"),
        Raven_Guard("Raven Guard"),
        Salamanders("Salamanders"),
        White_Scars("White Scars"),
        Iron_Hands("Iron Hands");

        public String name;

        SubFaction(String name) {
            this.name = name;
        }
    }

    public interface FactionData {
        Class<? extends DetachmentList> getDetachments();

        Class<? extends DataSheetList> getDataSheets();
    }

    public interface DetachmentList {

        String getName();

        List<String> getEnhancements();

        enum SpaceMarine implements DetachmentList {
            Gladius_Strike_Force("Gladius Strike Force"),
            First_Company_Task_Force("First Company Task Force"),
            Anvil_Siege_Force("Anvil Siege Force"),
            Ironstorm_Spearhead("Ironstorm Spearhead"),
            Stormlance_Task_Force("Stormlance Task Force"),
            Firestorm_Assault_Force("Firestorm Assault Force"),
            Vanguard_Spearhead("Vanguard Spearhead"),
            Righteous_Crusaders("Righteous Crusaders"),
            Sons_of_Sanguinius("Sons of Sanguinius"),
            Inner_Circle_Task_Force("Inner Circle Task Force"),
            Company_Of_Hunters("Company of Hunters"),
            Unforgiven_Task_Force("Unforgiven Task Force"),
            Black_Spear_Task_Force("Black Spear Task Force"),
            Champions_of_Russ("Champions of Russ");

            public final String name;

            @Override
            public String getName() {
                return name;
            }

            @Override
            public List<String> getEnhancements() {
                return null;
            }


            SpaceMarine(String name) {
                this.name = name;
            }
        }
    }

    public interface DataSheetList {
        String getName();
    }
}
