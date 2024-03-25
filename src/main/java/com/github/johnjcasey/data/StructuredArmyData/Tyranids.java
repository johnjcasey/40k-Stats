package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class Tyranids implements StructuredArmyData.FactionData {

    public static Tyranids INSTANCE = new Tyranids();

    private Tyranids() {
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
        return List.of();
    }

    enum Detachments implements StructuredArmyData.DetachmentList {
        Assimilation_Swarm("Assimilation Swarm", List.of("Biophagic Flow", "Instinctive Defence", "Parasitic Biomorphology", "Regenerating Monstrosity")),
        Crusher_Stampede("Crusher Stampede", List.of("Enraged Reserves", "Monstrous Nemesis", "Null Nodules", "Ominous Presence")),
        //Typo in GW list builder means that linchpin is spelled incorrectly
        Invasion_Fleet("Invasion Fleet", List.of("Adaptive Biology", "Alien Cunning", "Perfectly Adapted", "Synaptic Linchpin", "Synaptic Lynchpin")),
        Synaptic_Nexus("Synaptic Nexus", List.of("Power of the Hive Mind", "Psychostatic Disruption", "Synaptic Control", "The Dirgeheart of Kharis")),
        Unending_Swarm("Unending Swarm", List.of("Adrenalised Onslaught", "Naturalised Camouflage", "Piercing Talons", "Relentless Hunger")),
        Vanguard_Onslaught("Vanguard Onslaught", List.of("Chameleonic", "Hunting Grounds", "Neuronode", "Stalker"));

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
        Barbgaunts("Barbgaunts"),
        Biovores("Biovores"),
        Broodlord("Broodlord"),
        Carnifexes("Carnifexes"),
        Deathleaper("Deathleaper"),
        Exocrine("Exocrine"),
        Gargoyles("Gargoyles"),
        Genestealers("Genestealers"),
        Harpy("Harpy"),
        Haruspex("Haruspex"),
        Hive_Crone("Hive Crone"),
        Hive_Guard("Hive Guard"),
        Hive_Tyrant("Hive Tyrant"),
        Hormagaunts("Hormagaunts"),
        Lictor("Lictor"),
        Maleceptor("Maleceptor"),
        Mawloc("Mawloc"),
        Mucolid_Spores("Mucolid Spores"),
        Neurogaunts("Neurogaunts"),
        Neurolictor("Neurolictor"),
        Neurotyrant("Neurotyrant"),
        Norn_Assimilator("Norn Assimilator"),
        Norn_Emissary("Norn Emissary"),
        Old_One_Eye("Old One Eye"),
        Parasite_of_Mortrex("Parasite of Mortrex"),
        Psychophage("Psychophage"),
        Pyrovores("Pyrovores"),
        Raveners("Raveners"),
        Ripper_Swarms("Ripper Swarms"),
        Screamer_Killer("Screamer-Killer"),
        Spore_Mines("Spore Mines"),
        Sporocyst("Sporocyst"),
        Termagants("Termagants"),
        Tervigon("Tervigon"),
        The_Swarmlord("The Swarmlord"),
        Toxicrene("Toxicrene"),
        Trygon("Trygon"),
        Tyranid_Warriors_with_Melee_Bio_weapons("Tyranid Warriors with Melee Bio-weapons"),
        Tyranid_Warriors_with_Ranged_Bio_weapons("Tyranid Warriors with Ranged Bio-weapons"),
        Tyrannocyte("Tyrannocyte"),
        Tyrannofex("Tyrannofex"),
        Tyrant_Guard("Tyrant Guard"),
        Venomthropes("Venomthropes"),
        Von_Ryans_Leapers("Von Ryan’s Leapers"),
        Winged_Hive_Tyrant("Winged Hive Tyrant"),
        Winged_Tyranid_Prime("Winged Tyranid Prime"),
        Zoanthropes("Zoanthropes"),
        Barbed_Hierodule("Barbed Hierodule"),
        Harridan("Harridan"),
        Hierophant("Hierophant"),
        Scythed_Hierodule("Scythed Hierodule");

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