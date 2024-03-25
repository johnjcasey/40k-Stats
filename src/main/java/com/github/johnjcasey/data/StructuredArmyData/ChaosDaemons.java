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
        Daemonic_Incursion("Daemonic Incursion", List.of("A’rgath, the King of Blades","Soulstealer","The Endless Gift","The Everstavec"),
       
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
        Archaeopter Transvector("Archaeopter Transvector"),
        Belisarius_Cawl("Belisarius Cawl"),
        Corpuscarii_Electro-Priests("Corpuscarii Electro-Priests"),
        Cybernetica_Datasmith("Cybernetica Datasmith"),
        Fulgurite_Electro-Priests("Fulgurite Electro-Priests"),
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
        Sydonian_Dragoons with Radium Jezzails("Sydonian Dragoons with Radium Jezzails"),
        Sydonian_Dragoons with Taser Lances("Sydonian Dragoons with Taser Lances"),
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
