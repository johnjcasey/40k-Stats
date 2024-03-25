package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class StructuredArmyData {
    public enum Faction {
        Adeptus_Custodes(List.of("Adeptus Custodes"), AdeptusCustodes.INSTANCE),
        Grey_Knights(List.of("Grey Knights"), GreyKnights.INSTANCE),
        Adeptus_Mechanicus(List.of("Adeptus Mechanicus"), AdeptusMechanicus.INSTANCE),
        Astra_Militarum(List.of("Astra Militarum"), AstraMilitarum.INSTANCE),
        Imperial_Knights(List.of("Imperial Knights"), ImperialKnights.INSTANCE),
        Adepta_Sororitas(List.of("Adepta Sororitas"), AdeptaSororitas.INSTANCE),
        Space_Marines(List.of("Space Marines", "Adeptus Astartes", "Dark Angels", "Black Templars", "Space Wolves", "Deathwatch", "Blood Angels"), SpaceMarines.INSTANCE),
        Agents_Of_The_Imperium(List.of("Agents of the Imperium"), AgentsOfTheImperium.INSTANCE),
        World_Eaters(List.of("World Eaters"), WorldEaters.INSTANCE),
        Thousand_Sons(List.of("Thousand Sons"), ThousandSons.INSTANCE),
        Chaos_Space_Marines(List.of("Chaos Space Marines"), ChaosSpaceMarines.INSTANCE),
        Death_Guard(List.of("Death Guard"), DeathGuard.INSTANCE),
        Chaos_Knights(List.of("Chaos Knights"), ChaosKnights.INSTANCE),
        Chaos_Daemons(List.of("Chaos Daemons", "Legiones Daemonica"), ChaosDaemons.INSTANCE),
        Tau_Empire(List.of("T’au Empire","T'au Empire"), TauEmpire.INSTANCE),
        Necrons(List.of("Necrons"), com.github.johnjcasey.data.StructuredArmyData.Necrons.INSTANCE),
        Leagues_Of_Votann(List.of("Leagues of Votann"), LeaguesOfVotann.INSTANCE),
        Orks(List.of("Orks"), com.github.johnjcasey.data.StructuredArmyData.Orks.INSTANCE),
        Genestealer_Cult(List.of("Genestealer Cult"), GenestealerCults.INSTANCE),
        Drukhari(List.of("Drukhari"), com.github.johnjcasey.data.StructuredArmyData.Drukhari.INSTANCE),
        Aeldari(List.of("Aeldari"), com.github.johnjcasey.data.StructuredArmyData.Aeldari.INSTANCE),
        Tyranids(List.of("Tyranids"), com.github.johnjcasey.data.StructuredArmyData.Tyranids.INSTANCE);

        public final List<String> names;

        public final FactionData factionData;

        Faction(List<String> names, FactionData factionData) {
            this.names = names;
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
    }

    public interface DataSheetList {
        String getName();
    }
}
