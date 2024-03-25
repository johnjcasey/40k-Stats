package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class AstraMilitarum implements StructuredArmyData.FactionData {

    public static AstraMilitarum INSTANCE = new AstraMilitarum();

    private AstraMilitarum(){}

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
        return List.of(StructuredArmyData.Faction.Agents_Of_The_Imperium, StructuredArmyData.Faction.Imperial_Knights);
    }

    enum Detachments implements StructuredArmyData.DetachmentList {
        Combined_Regiment("Combined Regiment", List.of("Death Mask of Ollanius", "Drill Commander", "Grand Strategist", "Kurov’s Aquila"));

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
        Aegis_Defence_Line("Aegis Defence Line"),
        Armoured_Sentinels("Armoured Sentinels"),
        Attilan_Rough_Riders("Attilan Rough Riders"),
        Baneblade("Baneblade"),
        Banehammer("Banehammer"),
        Banesword("Banesword"),
        Basilisk("Basilisk"),
        Bullgryn_Squad("Bullgryn Squad"),
        Cadian_Castellan("Cadian Castellan"),
        Cadian_Command_Squad("Cadian Command Squad"),
        Cadian_Shock_Troops("Cadian Shock Troops"),
        Catachan_Jungle_Fighters("Catachan Jungle Fighters"),
        Chimera("Chimera"),
        Commissar("Commissar"),
        Death_Korps_of_Krieg("Death Korps of Krieg"),
        Deathstrike("Deathstrike"),
        Doomhammer("Doomhammer"),
        Field_Ordnance_Battery("Field Ordnance Battery"),
        Gaunts_Ghosts("Gaunt’s Ghosts"),
        Heavy_Weapons_Squad("Heavy Weapons Squad"),
        Hellhammer("Hellhammer"),
        Hellhound("Hellhound"),
        Hydra("Hydra"),
        Infantry_Squad("Infantry Squad"),
        Iron_Hand_Straken("‘Iron Hand’ Straken"),
        Kasrkin("Kasrkin"),
        Leman_Russ_Battle_Tank("Leman Russ Battle Tank"),
        Leman_Russ_Demolisher("Leman Russ Demolisher"),
        Leman_Russ_Eradicator("Leman Russ Eradicator"),
        Leman_Russ_Executioner("Leman Russ Executioner"),
        Leman_Russ_Exterminator("Leman Russ Exterminator"),
        Leman_Russ_Punisher("Leman Russ Punisher"),
        Leman_Russ_Vanquisher("Leman Russ Vanquisher"),
        Lord_Solar_Leontus("Lord Solar Leontus"),
        Manticore("Manticore"),
        Militarum_Tempestus_Command_Squad("Militarum Tempestus Command Squad"),
        Munitorum_Servitors("Munitorum Servitors"),
        Nork_Deddog("Nork Deddog"),
        Ogryn_Bodyguard("Ogryn Bodyguard"),
        Ogryn_Squad("Ogryn Squad"),
        Platoon_Command_Squad("Platoon Command Squad"),
        Primaris_Psyker("Primaris Psyker"),
        Ratling_Snipers("Ratling Snipers"),
        Regimental_Attachés("Regimental Attachés"),
        Regimental_Enginseer("Regimental Enginseer"),
        Regimental_Preacher("Regimental Preacher"),
        Rogal_Dorn_Battle_Tank("Rogal Dorn Battle Tank"),
        Scout_Sentinels("Scout Sentinels"),
        Sergeant_Harker("Sergeant Harker"),
        Shadowsword("Shadowsword"),
        Sly_Marbo("Sly Marbo"),
        Stormlord("Stormlord"),
        Stormsword("Stormsword"),
        Tank_Commander("Tank Commander"),
        Taurox("Taurox"),
        Taurox_Prime("Taurox Prime"),
        Tempestus_Scions("Tempestus Scions"),
        Ursula_Creed("Ursula Creed"),
        Valkyrie("Valkyrie"),
        Wyvern("Wyvern"),
        Avenger_Strike_Fighter("Avenger Strike Fighter"),
        Carnodon("Carnodon"),
        Colossus("Colossus"),
        Crassus("Crassus"),
        Cyclops_Demolition_Vehicle("Cyclops Demolition Vehicle"),
        Death_Korps_Marshal("Death Korps Marshal"),
        Death_Rider_Squadron("Death Rider Squadron"),
        Death_Rider_Squadron_Commander("Death Rider Squadron Commander"),
        Earthshaker_Carriage_Battery("Earthshaker Carriage Battery"),
        Hades_Breaching_Drill("Hades Breaching Drill"),
        Macharius("Macharius"),
        Macharius_Vanquisher("Macharius Vanquisher"),
        Macharius_Vulcan("Macharius Vulcan"),
        Malcador("Malcador"),
        Malcador_Annihilator("Malcador Annihilator"),
        Malcador_Defender("Malcador Defender"),
        Malcador_Infernus("Malcador Infernus"),
        Marauder_Bomber("Marauder Bomber"),
        Marauder_Destroyer("Marauder Destroyer"),
        Medusa_Carriage_Battery("Medusa Carriage Battery"),
        Praetor("Praetor"),
        Rapier_Laser_Destroyer_Battery("Rapier Laser Destroyer Battery"),
        Stormblade("Stormblade"),
        Tarantula_Battery("Tarantula Battery"),
        Thunderbolt_Heavy_Fighter("Thunderbolt Heavy Fighter"),
        Trojan_Support_Vehicle("Trojan Support Vehicle"),
        Valdor("Valdor"),
        Voss_pattern_Lightning("Voss-pattern Lightning"),
        Vulture_Gunship("Vulture Gunship");
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
