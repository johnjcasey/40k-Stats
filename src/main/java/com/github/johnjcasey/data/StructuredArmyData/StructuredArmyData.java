package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class StructuredArmyData {
    public enum Faction {
        Adeptus_Custodes("Adeptus Custodes", AdeptusCustodes.INSTANCE),
        Grey_Knights("Grey Knights", GreyKnights.INSTANCE),
        Adeptus_Mechanicus("Adeptus Mechanicus", AdeptusMechanicus.INSTANCE),
        Astra_Militarum("Astra Militarum", AstraMilitarum.INSTANCE),
        Imperial_Knights("Imperial Knights", ImperialKnights.INSTANCE),
        Adepta_Sororitas("Adepta Sororitas", AdeptaSororitas.INSTANCE),
        Space_Marines("Space Marines", SpaceMarines.INSTANCE),
        Agents_Of_The_Imperium("Agents of the Imperium", AgentsOfTheImperium.INSTANCE),
        World_Eaters("World Eaters", WorldEaters.INSTANCE),
        Thousand_Sons("Thousand Sons", ThousandSons.INSTANCE),
        Chaos_Space_Marines("Chaos Space Marines", ChaosSpaceMarines.INSTANCE),
        Death_Guard("Death Guard", DeathGuard.INSTANCE),
        Chaos_Knights("Chaos Knights", ChaosKnights.INSTANCE),
        Chaos_Daemons("Chaos Daemons", ChaosDaemons.INSTANCE),
        Tau_Empire("T'au Empire", TauEmpire.INSTANCE),
        Necrons("Necrons", com.github.johnjcasey.data.StructuredArmyData.Necrons.INSTANCE),
        Leagues_Of_Votann("Leagues Of Votann", LeaguesOfVotann.INSTANCE),
        Orks("Orks", com.github.johnjcasey.data.StructuredArmyData.Orks.INSTANCE),
        Genestealer_Cult("Genestealer Cult", GenestealerCults.INSTANCE),
        Drukhari("Drukhari", com.github.johnjcasey.data.StructuredArmyData.Drukhari.INSTANCE),
        Aeldari("Aeldari", com.github.johnjcasey.data.StructuredArmyData.Aeldari.INSTANCE),
        Tyranids("Tyranids", com.github.johnjcasey.data.StructuredArmyData.Tyranids.INSTANCE);

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
    }

    public interface DataSheetList {
        String getName();
    }
}
