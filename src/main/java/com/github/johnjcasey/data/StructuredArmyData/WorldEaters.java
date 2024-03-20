package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class WorldEaters implements StructuredArmyData.FactionData {

    public static WorldEaters INSTANCE = new WorldEaters();

    private WorldEaters(){}

    @Override
    public Class<? extends StructuredArmyData.DetachmentList> getDetachments() {
        return Detachments.class;
    }

    @Override
    public Class<? extends StructuredArmyData.DataSheetList> getDataSheets() {
        return DataSheets.class;
    }

    enum Detachments implements StructuredArmyData.DetachmentList {
        Berzerker_Warband("Berzerker Warband", List.of("Favoured of Khorne", "Berzerker Glaive", "Battle-lust", "Helm of Brazen Ire"));

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
        Angron("Angron"),
        Eightbound("Eightbound"),
        Exalted_Eightbound("Exalted Eightbound"),
        Jakhals("Jakhals"),
        Khorne_Berzerkers("Khorne Berzerkers"),
        Khorne_Lord_of_Skulls("Khorne Lord of Skulls"),
        Khârn_the_Betrayer("Khârn the Betrayer"),
        Lord_Invocatus("Lord Invocatus"),
        World_Eaters_Chaos_Spawn("World Eaters Chaos Spawn"),
        World_Eaters_Daemon_Prince("World Eaters Daemon Prince"),
        World_Eaters_Daemon_Prince_with_Wings("World Eaters Daemon Prince with Wings"),
        World_Eaters_Defiler("World Eaters Defiler"),
        World_Eaters_Forgefiend("World Eaters Forgefiend"),
        World_Eaters_Helbrute("World Eaters Helbrute"),
        World_Eaters_Heldrake("World Eaters Heldrake"),
        World_Eaters_Land_Raider("World Eaters Land Raider"),
        World_Eaters_Lord_on_Juggernaut("World Eaters Lord on Juggernaut"),
        World_Eaters_Master_of_Executions("World Eaters Master of Executions"),
        World_Eaters_Maulerfiend("World Eaters Maulerfiend"),
        World_Eaters_Predator_Annihilator("World Eaters Predator Annihilator"),
        World_Eaters_Predator_Destructor("World Eaters Predator Destructor"),
        World_Eaters_Rhino("World Eaters Rhino"),
        World_Eaters_Terminator_Squad("World Eaters Terminator Squad");

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