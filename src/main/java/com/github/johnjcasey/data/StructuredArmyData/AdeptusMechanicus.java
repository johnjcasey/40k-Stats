package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class AdeptusMechanicus implements StructuredArmyData.FactionData {

    public static AdeptusMechanicus INSTANCE = new AdeptusMechanicus();

    private AdeptusMechanicus(){}

    @Override
    public Class<? extends StructuredArmyData.DetachmentList> getDetachments() {
        return Detachments.class;
    }

    @Override
    public Class<? extends StructuredArmyData.DataSheetList> getDataSheets() {
        return DataSheets.class;
    }

    enum Detachments implements StructuredArmyData.DetachmentList {
        Cohort_Cybernetica("Cohort Cybernetica", List.of("Arch-negator","Emotionless Clarity","Lord of Machines","Necromechanic")),
        Data_psalm_Conclave("Data-psalm Conclave", List.of("Data-blessed Autosermon","Mantle of the Gnosticarch","Mechanicus Locum","Temporcopia")),
        Explorator_Maniple("Explorator Maniple", List.of("Artisan","Genetor","Logis","Magos")),
        Rad_zone_Corps("Rad-zone Corps", List.of("Autoclavic Denunciation","Malphonic Susurrus","Peerless Eradicator","Radial Suffusion")),
        Skitarii_Hunter_Cohort("Skitarii Hunter Cohort", List.of("Battle-sphere Uplink","Cantic Thrallnet","Clandestine Infiltrator","Veiled Hunter"));
        
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
        Archaeopter_Fusilave("Archaeopter Fusilave"),
        Archaeopter_Stratoraptor("Archaeopter Stratoraptor"),
        Archaeopter_Transvector("Archaeopter Transvector"),
        Belisarius_Cawl("Belisarius Cawl"),
        Corpuscarii_Electro_Priests("Corpuscarii Electro-Priests"),
        Cybernetica_Datasmith("Cybernetica Datasmith"),
        Fulgurite_Electro_Priests("Fulgurite Electro-Priests"),
        Ironstrider_Ballistarii("Ironstrider Ballistarii"),
        Kastelan_Robots("Kastelan Robots"),
        Kataphron_Breachers("Kataphron Breachers"),
        Kataphron_Destroyers("Kataphron Destroyers"),
        Onager_Dunecrawler("Onager Dunecrawler"),
        Pteraxii_Skystalkers("Pteraxii Skystalkers"),
        Pteraxii_Sterylizors("Pteraxii Sterylizors"),
        Serberys_Raiders("Serberys Raiders"),
        Serberys_Sulphurhounds("Serberys Sulphurhounds"),
        Sicarian_Infiltrators("Sicarian Infiltrators"),
        Sicarian_Ruststalkers("Sicarian Ruststalkers"),
        Skitarii_Marshal("Skitarii Marshal"),
        Skitarii_Rangers("Skitarii Rangers"),
        Skitarii_Vanguard("Skitarii Vanguard"),
        Skorpius_Disintegrator("Skorpius Disintegrator"),
        Skorpius_Dunerider("Skorpius Dunerider"),
        Sydonian_Dragoons_with_Radium_Jezzails("Sydonian Dragoons with Radium Jezzails"),
        Sydonian_Dragoons_with_Taser_Lances("Sydonian Dragoons with Taser Lances"),
        Sydonian_Skatros("Sydonian Skatros"),
        Tech_Priest_Dominus("Tech-Priest Dominus"),
        Tech_Priest_Enginseer("Tech-Priest Enginseer"),
        Tech_Priest_Manipulus("Tech-Priest Manipulus"),
        Technoarcheologist("Technoarcheologist");

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
