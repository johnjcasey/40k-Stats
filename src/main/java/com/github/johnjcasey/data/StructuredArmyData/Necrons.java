package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class Necrons implements StructuredArmyData.FactionData {

    public static Necrons INSTANCE = new Necrons();

    private Necrons() {
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
        Annihilation_Legion("Annihilation Legion", List.of("Eldritch Nightmare","Eternal Madness","Ingrained Superiority","Soulless Reaper")),
        Awakened_Dynasty("Awakened Dynasty", List.of("Enaegic Dermal Bond","Nether-realm Casket","Phasal Subjugator","Veil of Darkness")),
        Canoptek_Court("Canoptek Court", List.of("Autodivinator","Dimensional Sanctum","Hyperphasic Fulcrum","Metalodermal Tesla Weave")),
        Hypercrypt_Legion("Hypercrypt Legion", List.of("Arisen Tyrant","Dimensional Overseer","Hyperspatial Transfer Node","Osteoclave Fulcrum")),
        Obeisance_Phalanx("Obeisance Phalanx", List.of("Eternal Conqueror","Honourable Combatant","Unflinching Will","Warrior Noble"));

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
        Annihilation_Barge("Annihilation Barge"),
        Ctan_Shard_of_the_Deceiver("C’tan Shard of the Deceiver"),
        Ctan_Shard_of_the_Nightbringer("C’tan Shard of the Nightbringer"),
        Ctan_Shard_of_the_Void_Dragon("C’tan Shard of the Void Dragon"),
        Canoptek_Doomstalker("Canoptek Doomstalker"),
        Canoptek_Reanimator("Canoptek Reanimator"),
        Canoptek_Scarab_Swarms("Canoptek Scarab Swarms"),
        Canoptek_Spyders("Canoptek Spyders"),
        Canoptek_Wraiths("Canoptek Wraiths"),
        Catacomb_Command_Barge("Catacomb Command Barge"),
        Chronomancer("Chronomancer"),
        Convergence_of_Dominion("Convergence of Dominion"),
        Cryptothralls("Cryptothralls"),
        Deathmarks("Deathmarks"),
        Doom_Scythe("Doom Scythe"),
        Doomsday_Ark("Doomsday Ark"),
        Flayed_Ones("Flayed Ones"),
        Ghost_Ark("Ghost Ark"),
        Hexmark_Destroyer("Hexmark Destroyer"),
        Illuminor_Szeras("Illuminor Szeras"),
        Immortals("Immortals"),
        Imotekh_the_Stormlord("Imotekh the Stormlord"),
        Lokhust_Destroyers("Lokhust Destroyers"),
        Lokhust_Heavy_Destroyers("Lokhust Heavy Destroyers"),
        Lokhust_Lord("Lokhust Lord"),
        Lychguard("Lychguard"),
        Monolith("Monolith"),
        Necron_Warriors("Necron Warriors"),
        Night_Scythe("Night Scythe"),
        Obelisk("Obelisk"),
        Ophydian_Destroyers("Ophydian Destroyers"),
        Orikan_the_Diviner("Orikan the Diviner"),
        Overlord("Overlord"),
        Overlord_with_Translocation_Shroud("Overlord with Translocation Shroud"),
        Plasmancer("Plasmancer"),
        Psychomancer("Psychomancer"),
        Royal_Warden("Royal Warden"),
        Skorpekh_Destroyers("Skorpekh Destroyers"),
        Skorpekh_Lord("Skorpekh Lord"),
        Technomancer("Technomancer"),
        Tesseract_Vault("Tesseract Vault"),
        The_Silent_King("The Silent King"),
        Tomb_Blades("Tomb Blades"),
        Transcendent_Ctan("Transcendent C’tan"),
        Trazyn_the_Infinite("Trazyn the Infinite"),
        Triarch_Praetorians("Triarch Praetorians"),
        Triarch_Stalker("Triarch Stalker"),
        Canoptek_Acanthrites("Canoptek Acanthrites"),
        Canoptek_Tomb_Sentinel("Canoptek Tomb Sentinel"),
        Canoptek_Tomb_Stalker("Canoptek Tomb Stalker"),
        Seraptek_Heavy_Construct("Seraptek Heavy Construct"),
        Tesseract_Ark("Tesseract Ark");

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