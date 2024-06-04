package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class ChaosSpaceMarines implements StructuredArmyData.FactionData {

    public static ChaosSpaceMarines INSTANCE = new ChaosSpaceMarines();

    private ChaosSpaceMarines() {
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
    public List<StructuredArmyData.Faction> getAllies() {
        return List.of(StructuredArmyData.Faction.Chaos_Daemons, StructuredArmyData.Faction.Chaos_Knights);
    }

    enum Detachments implements StructuredArmyData.DetachmentList {
        Slaves_to_Darkness("Slaves to Darkness", List.of("Eye of Tzeentch", "Intoxicating Elixir", "Liber Hereticus", "Orbs of Unlife", "Talisman of Burning Blood")),
        Chaos_Cult("Chaos Cult", List.of("Amulet of Tainted Vigour", "Cultist’s Brand", "Incendiary Goad", "Warped Foresight")),
        Deceptors("Deceptors", List.of("Cursed Fang", "Falsehood", "Shroud of Obfuscation", "Soul Link")),
        Dread_Talons("Dread Talons", List.of("Eater of Dread", "Night’s Shroud", "Warp-fuelled Thrusters", "Willbreaker")),
        Fellhammer_Siege_host("Fellhammer Siege-host", List.of("Bastion Plate", "Iron Artifice", "Ironbound Enmity", "Warp Tracer")),
        Pactbound_Zealots("Pactbound Zealots", List.of("Eye of Tzeentch", "Intoxicating Elixir", "Orbs of Unlife", "Talisman of Burning Blood")),
        Renegade_Raiders("Renegade Raiders", List.of("Despot’s Claim", "Dread Reaver", "Mark of the Hound", "Tyrant’s Lash")),
        Soulforged_Warpack("Soulforged Warpack", List.of("Forge’s Blessing", "Invigorated Mechatendrils", "Tempting Addendum", "Soul Harvester")),
        Veterans_of_the_Long_War("Veterans of the Long War", List.of("Eager for Vengeance", "Eye of Abaddon", "Mark of Legend", "Warmaster’s Gift"));

        public final String name;

        public final List<String> enhancements;

        Detachments(String name, List<String> enhancements) {
            this.name = name;
            this.enhancements = enhancements;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<String> getEnhancements() {
            return enhancements;
        }
    }

    public enum DataSheets implements StructuredArmyData.DataSheetList {
        Abaddon_the_Despoiler("Abaddon the Despoiler"),
        Accursed_Cultists("Accursed Cultists"),
        Chaos_Bikers("Chaos Bikers"),
        Chaos_Land_Raider("Chaos Land Raider"),
        Chaos_Lord("Chaos Lord"),
        Chaos_Lord_in_Terminator_Armour("Chaos Lord in Terminator Armour"),
        Chaos_Predator_Annihilator("Chaos Predator Annihilator"),
        Chaos_Predator_Destructor("Chaos Predator Destructor"),
        Chaos_Rhino("Chaos Rhino"),
        Chaos_Spawn("Chaos Spawn"),
        Chaos_Terminator_Squad("Chaos Terminator Squad"),
        Chaos_Vindicator("Chaos Vindicator"),
        Chosen("Chosen"),
        Cultist_Firebrand("Cultist Firebrand"),
        Cultist_Mob("Cultist Mob"),
        Cypher("Cypher"),
        Dark_Apostle("Dark Apostle"),
        Dark_Commune("Dark Commune"),
        Defiler("Defiler"),
        Exalted_Champion("Exalted Champion"),
        Fabius_Bile("Fabius Bile"),
        Fellgor_Beastmen("Fellgor Beastmen"),
        Forgefiend("Forgefiend"),
        Haarken_Worldclaimer("Haarken Worldclaimer"),
        Havocs("Havocs"),
        Helbrute("Helbrute"),
        Heldrake("Heldrake"),
        Heretic_Astartes_Daemon_Prince("Heretic Astartes Daemon Prince"),
        Heretic_Astartes_Daemon_Prince_with_Wings("Heretic Astartes Daemon Prince with Wings"),
        Huron_Blackheart("Huron Blackheart"),
        Khorne_Lord_of_Skulls("Khorne Lord of Skulls"),
        Legionaries("Legionaries"),
        Lord_Discordant_on_Helstalker("Lord Discordant on Helstalker"),
        Lucius_the_Eternal("Lucius the Eternal"),
        Master_of_Executions("Master of Executions"),
        Master_of_Possession("Master of Possession"),
        Maulerfiend("Maulerfiend"),
        Noctilith_Crown("Noctilith Crown"),
        Noise_Marines("Noise Marines"),
        Obliterators("Obliterators"),
        Possessed("Possessed"),
        Raptors("Raptors"),
        Sorcerer("Sorcerer"),
        Sorcerer_in_Terminator_Armour("Sorcerer in Terminator Armour"),
        Traitor_Enforcer("Traitor Enforcer"),
        Traitor_Guardsmen_Squad("Traitor Guardsmen Squad"),
        Vashtorr_the_Arkifane("Vashtorr the Arkifane"),
        Venomcrawler("Venomcrawler"),
        Warp_Talons("Warp Talons"),
        Warpsmith("Warpsmith");

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