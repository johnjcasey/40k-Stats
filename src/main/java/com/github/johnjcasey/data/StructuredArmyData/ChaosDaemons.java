package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class ChaosDaemons implements StructuredArmyData.FactionData {

    public static ChaosDaemons INSTANCE = new ChaosDaemons();

    private ChaosDaemons(){}

    @Override
    public Class<? extends StructuredArmyData.DetachmentList> getDetachments() {
        return Detachments.class;
    }

    @Override
    public Class<? extends StructuredArmyData.DataSheetList> getDataSheets() {
        return DataSheets.class;
    }

    enum Detachments implements StructuredArmyData.DetachmentList {
        Daemonic_Incursion("Daemonic Incursion", List.of("A’rgath, the King of Blades","Soulstealer","The Endless Gift","The Everstave"));
       
        public final String name;

        public final List<String> enhancements;

        @Override
        public String getName(){
            return name;
        }

        @Override
        public List<String> getEnhancements() {
            return enhancements;
        }


        Detachments(String name, List<String> enhancements){
            this.name = name;
            this.enhancements = enhancements;
        }
    }

    public enum DataSheets implements StructuredArmyData.DataSheetList {
        Beasts_of_Nurgle("Beasts of Nurgle"),
        Belakor("Be’lakor"),
        Bloodcrushers("Bloodcrushers"),
        Bloodletters("Bloodletters"),
        Bloodmaster("Bloodmaster"),
        Bloodthirster("Bloodthirster"),
        Blue_Horrors("Blue Horrors"),
        Burning_Chariot("Burning Chariot"),
        Changecaster("Changecaster"),
        Contorted_Epitome("Contorted Epitome"),
        Daemon_Prince_of_Chaos("Daemon Prince of Chaos"),
        Daemon_Prince_of_Chaos_with_Wings("Daemon Prince of Chaos with Wings"),
        Daemonettes("Daemonettes"),
        Epidemius("Epidemius"),
        Exalted_Flamer("Exalted Flamer"),
        Exalted_Seeker_Chariot("Exalted Seeker Chariot"),
        Fateskimmer("Fateskimmer"),
        Feculent_Gnarlmaw("Feculent Gnarlmaw"),
        Fiends("Fiends"),
        Flamers("Flamers"),
        Flesh_Hounds("Flesh Hounds"),
        Fluxmaster("Fluxmaster"),
        Great_Unclean_One("Great Unclean One"),
        Hellflayer("Hellflayer"),
        Horticulous_Slimux("Horticulous Slimux"),
        Infernal_Enrapturess("Infernal Enrapturess"),
        Kairos_Fateweaver("Kairos Fateweaver"),
        Karanak("Karanak"),
        Keeper_of_Secrets("Keeper of Secrets"),
        Lord_of_Change("Lord of Change"),
        Nurglings("Nurglings"),
        Pink_Horrors("Pink Horrors"),
        Plague_Drones("Plague Drones"),
        Plaguebearers("Plaguebearers"),
        Poxbringer("Poxbringer"),
        Rendmaster_on_Blood_Throne("Rendmaster on Blood Throne"),
        Rotigus("Rotigus"),
        Screamers("Screamers"),
        Seeker_Chariot("Seeker Chariot"),
        Seekers("Seekers"),
        Shalaxi_Helbane("Shalaxi Helbane"),
        Skarbrand("Skarbrand"),
        Skull_Altar("Skull Altar"),
        Skull_Cannon("Skull Cannon"),
        Skullmaster("Skullmaster"),
        Skulltaker("Skulltaker"),
        Sloppity_Bilepiper("Sloppity Bilepiper"),
        Soul_Grinder("Soul Grinder"),
        Spoilpox_Scrivener("Spoilpox Scrivener"),
        Syllesske("Syll’esske"),
        The_Blue_Scribes("The Blue Scribes"),
        The_Changeling("The Changeling"),
        The_Masque_of_Slaanesh("The Masque of Slaanesh"),
        Tormentbringer_on_Exalted_Seeker_Chariot("Tormentbringer on Exalted Seeker Chariot"),
        Tranceweaver("Tranceweaver");

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
