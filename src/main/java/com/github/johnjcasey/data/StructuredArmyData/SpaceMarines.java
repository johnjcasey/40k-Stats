package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class SpaceMarines implements StructuredArmyData.FactionData {

    public static SpaceMarines INSTANCE = new SpaceMarines();

    private SpaceMarines() {
    }

    @Override
    public Class<? extends StructuredArmyData.DetachmentList> getDetachments() {
        return Detachments.class;
    }

    @Override
    public Class<? extends StructuredArmyData.DataSheetList> getDataSheets() {
        return DataSheets.class;
    }

    enum Detachments implements StructuredArmyData.DetachmentList {
        //The name for the Gladius Strike Force is shortened to "Gladius" so it matches Gladius Task Force, which is the name in BattleScribe
        Gladius_Strike_Force("Gladius", List.of("Adept of the Codex", "Artificer Armour", "Fire Discipline", "The Honour Vehement")),
        First_Company_Task_Force("1st Company Task Force", List.of("Fear Made Manifest", "Iron Resolve", "Rites of War", "The Imperium’s Sword")),
        Anvil_Siege_Force("Anvil Siege Force", List.of("Architect of War", "Fleet Commander", "Indomitable Fury", "Stoic Defender")),
        Ironstorm_Spearhead("Ironstorm Spearhead", List.of("Adept of the Omnissiah", "Master of Machine War", "Target Augury Web", "The Flesh is Weak")),
        Stormlance_Task_Force("Stormlance Task Force", List.of("Feinting Withdrawal", "Fury of the Storm", "Hunter’s Instincts", "Portents of Wisdom")),
        Firestorm_Assault_Force("Firestorm Assault Force", List.of("Adamantine Mantle", "Champion of Humanity", "Forged in Battle", "War-tempered Artifice")),
        Vanguard_Spearhead("Vanguard Spearhead", List.of("Execute and Redeploy", "Ghostweave Cloak", "Shadow War Veteran", "The Blade Driven Deep")),
        Righteous_Crusaders("Righteous Crusaders", List.of("Perdition’s Edge", "Sigismund’s Seal", "Tännhauser’s Bones", "Witchseeker Bolts")),
        Sons_of_Sanguinius("Sons of Sanguinius", List.of("Archangel’s Shard", "Artisan of War", "Icon of the Angel", "Visage of Death")),
        Inner_Circle_Task_Force("Inner Circle Task Force", List.of("Champion of the Deathwing", "Deathwing Assault", "Eye of the Unseen", "Singular Will")),
        Company_Of_Hunters("Company of Hunters", List.of("Master of Manoeuvre", "Master-crafted Weapon", "Mounted Strategist", "Recon Hunter")),
        Unforgiven_Task_Force("Unforgiven Task Force", List.of("Pennant of Remembrance", "Shroud of Heroes", "Stubborn Tenacity", "Weapons of the First Legion")),
        Black_Spear_Task_Force("Black Spear Task Force", List.of("Beacon Angelis", "Osseus Key", "The Tome of Ectoclades", "Thief of Secrets")),
        Champions_of_Russ("Champions of Russ", List.of("Black Death", "Frost Weapon", "The Pelt of Balewolf", "Wolf Tail Talisman"));

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
        Arjac_Rockfist("Arjac Rockfist"),
        Bjorn_the_Fell_Handed("Bjorn the Fell-Handed"),
        Blood_Claws("Blood Claws"),
        Canis_Wolfborn("Canis Wolfborn"),
        Cyberwolf("Cyberwolf"),
        Fenrisian_Wolves("Fenrisian Wolves"),
        Grey_Hunters("Grey Hunters"),
        Harald_Deathwolf("Harald Deathwolf"),
        Hounds_of_Morkai("Hounds of Morkai"),
        Iron_Priest("Iron Priest"),
        Krom_Dragongaze("Krom Dragongaze"),
        Logan_Grimnar("Logan Grimnar"),
        Logan_Grimnar_on_Stormrider("Logan Grimnar on Stormrider"),
        Long_Fangs("Long Fangs"),
        Lukas_the_Trickster("Lukas the Trickster"),
        Murderfang("Murderfang"),
        Njal_Stormcaller("Njal Stormcaller"),
        Ragnar_Blackmane("Ragnar Blackmane"),
        Skyclaws("Skyclaws"),
        Space_Wolves_Venerable_Dreadnought("Space Wolves Venerable Dreadnought"),
        Stormfang_Gunship("Stormfang Gunship"),
        Stormwolf("Stormwolf"),
        Thunderwolf_Cavalry("Thunderwolf Cavalry"),
        Ulrik_the_Slayer("Ulrik the Slayer"),
        Wolf_Guard("Wolf Guard"),
        Wolf_Guard_Battle_Leader_in_Terminator_Armour("Wolf Guard Battle Leader in Terminator Armour"),
        Wolf_Guard_Battle_Leader_on_Thunderwolf("Wolf Guard Battle Leader on Thunderwolf"),
        Wolf_Guard_Pack_Leader("Wolf Guard Pack Leader"),
        Wolf_Guard_Pack_Leader_in_Terminator_Armour("Wolf Guard Pack Leader in Terminator Armour"),
        Wolf_Guard_Pack_Leader_with_Jump_Pack("Wolf Guard Pack Leader with Jump Pack"),
        Wolf_Guard_Terminators("Wolf Guard Terminators"),
        Wolf_Lord_on_Thunderwolf("Wolf Lord on Thunderwolf"),
        Wolf_Scouts("Wolf Scouts"),
        Wulfen("Wulfen"),
        Wulfen_Dreadnought("Wulfen Dreadnought"),
        Adrax_Agatone("Adrax Agatone"),
        Aggressor_Squad("Aggressor Squad"),
        Ancient("Ancient"),
        Ancient_in_Terminator_Armour("Ancient in Terminator Armour"),
        Apothecary("Apothecary"),
        Apothecary_Biologis("Apothecary Biologis"),
        Assault_Intercessor_Squad("Assault Intercessor Squad"),
        Assault_Intercessors_with_Jump_Packs("Assault Intercessors with Jump Packs"),
        Ballistus_Dreadnought("Ballistus Dreadnought"),
        Bladeguard_Ancient("Bladeguard Ancient"),
        Bladeguard_Veteran_Squad("Bladeguard Veteran Squad"),
        Brutalis_Dreadnought("Brutalis Dreadnought"),
        Captain("Captain"),
        Captain_in_Gravis_Armour("Captain in Gravis Armour"),
        Captain_in_Phobos_Armour("Captain in Phobos Armour"),
        Captain_in_Terminator_Armour("Captain in Terminator Armour"),
        Captain_Sicarius("Captain Sicarius"),
        Captain_with_Jump_Pack("Captain with Jump Pack"),
        Centurion_Assault_Squad("Centurion Assault Squad"),
        Centurion_Devastator_Squad("Centurion Devastator Squad"),
        Chaplain("Chaplain"),
        Chaplain_in_Terminator_Armour("Chaplain in Terminator Armour"),
        Chaplain_on_Bike("Chaplain on Bike"),
        Chaplain_with_Jump_Pack("Chaplain with Jump Pack"),
        Chief_Librarian_Tigurius("Chief Librarian Tigurius"),
        Company_Heroes("Company Heroes"),
        Darnath_Lysander("Darnath Lysander"),
        Desolation_Squad("Desolation Squad"),
        Devastator_Squad("Devastator Squad"),
        Dreadnought("Dreadnought"),
        Drop_Pod("Drop Pod"),
        Eliminator_Squad("Eliminator Squad"),
        Eradicator_Squad("Eradicator Squad"),
        Firestrike_Servo_turrets("Firestrike Servo-turrets"),
        Gladiator_Lancer("Gladiator Lancer"),
        Gladiator_Reaper("Gladiator Reaper"),
        Gladiator_Valiant("Gladiator Valiant"),
        Hammerfall_Bunker("Hammerfall Bunker"),
        Heavy_Intercessor_Squad("Heavy Intercessor Squad"),
        Hellblaster_Squad("Hellblaster Squad"),
        Impulsor("Impulsor"),
        Inceptor_Squad("Inceptor Squad"),
        Incursor_Squad("Incursor Squad"),
        Infernus_Squad("Infernus Squad"),
        Infiltrator_Squad("Infiltrator Squad"),
        Intercessor_Squad("Intercessor Squad"),
        Invader_ATV("Invader ATV"),
        Invictor_Tactical_Warsuit("Invictor Tactical Warsuit"),
        Iron_Father_Feirros("Iron Father Feirros"),
        Judiciar("Judiciar"),
        Kayvaan_Shrike("Kayvaan Shrike"),
        Korsarro_Khan("Kor’sarro Khan"),
        Land_Raider("Land Raider"),
        Land_Raider_Crusader("Land Raider Crusader"),
        Land_Raider_Redeemer("Land Raider Redeemer"),
        Librarian("Librarian"),
        Librarian_in_Phobos_Armour("Librarian in Phobos Armour"),
        Librarian_in_Terminator_Armour("Librarian in Terminator Armour"),
        Lieutenant("Lieutenant"),
        Lieutenant_in_Phobos_Armour("Lieutenant in Phobos Armour"),
        Lieutenant_in_Reiver_Armour("Lieutenant in Reiver Armour"),
        Lieutenant_with_Combi_weapon("Lieutenant with Combi-weapon"),
        Marneus_Calgar("Marneus Calgar"),
        Outrider_Squad("Outrider Squad"),
        Pedro_Kantor("Pedro Kantor"),
        Predator_Annihilator("Predator Annihilator"),
        Predator_Destructor("Predator Destructor"),
        Razorback("Razorback"),
        Redemptor_Dreadnought("Redemptor Dreadnought"),
        Reiver_Squad("Reiver Squad"),
        Repulsor("Repulsor"),
        Repulsor_Executioner("Repulsor Executioner"),
        Rhino("Rhino"),
        Roboute_Guilliman("Roboute Guilliman"),
        Scout_Squad("Scout Squad"),
        Sternguard_Veteran_Squad("Sternguard Veteran Squad"),
        Storm_Speeder_Hailstrike("Storm Speeder Hailstrike"),
        Storm_Speeder_Hammerstrike("Storm Speeder Hammerstrike"),
        Storm_Speeder_Thunderstrike("Storm Speeder Thunderstrike"),
        Stormhawk_Interceptor("Stormhawk Interceptor"),
        Stormraven_Gunship("Stormraven Gunship"),
        Stormtalon_Gunship("Stormtalon Gunship"),
        Suppressor_Squad("Suppressor Squad"),
        Tactical_Squad("Tactical Squad"),
        Techmarine("Techmarine"),
        Terminator_Assault_Squad("Terminator Assault Squad"),
        Terminator_Squad("Terminator Squad"),
        Tor_Garadon("Tor Garadon"),
        Uriel_Ventris("Uriel Ventris"),
        Vanguard_Veteran_Squad_with_Jump_Packs("Vanguard Veteran Squad with Jump Packs"),
        Vindicator("Vindicator"),
        Vulkan_Hestan("Vulkan He’stan"),
        Whirlwind("Whirlwind"),
        Astraeus("Astraeus"),
        Thunderhawk_Gunship("Thunderhawk Gunship"),
        Corvus_Blackstar("Corvus Blackstar"),
        Deathwatch_Terminator_Squad("Deathwatch Terminator Squad"),
        Deathwatch_Veterans("Deathwatch Veterans"),
        Fortis_Kill_Team("Fortis Kill Team"),
        Indomitor_Kill_Team("Indomitor Kill Team"),
        Kill_Team_Cassius("Kill Team Cassius"),
        Proteus_Kill_Team("Proteus Kill Team"),
        Spectrus_Kill_Team("Spectrus Kill Team"),
        Veteran_Bike_Squad("Veteran Bike Squad"),
        Watch_Captain_Artemis("Watch Captain Artemis"),
        Watch_Master("Watch Master"),
        Asmodai("Asmodai"),
        Azrael("Azrael"),
        Belial("Belial"),
        Deathwing_Knights("Deathwing Knights"),
        Deathwing_Terminator_Squad("Deathwing Terminator Squad"),
        Ezekiel("Ezekiel"),
        Inner_Circle_Companions("Inner Circle Companions"),
        Land_Speeder_Vengeance("Land Speeder Vengeance"),
        Lazarus("Lazarus"),
        Lion_ElJonson("Lion El’Jonson"),
        Nephilim_Jetfighter("Nephilim Jetfighter"),
        Ravenwing_Black_Knights("Ravenwing Black Knights"),
        Ravenwing_Command_Squad("Ravenwing Command Squad"),
        Ravenwing_Dark_Talon("Ravenwing Dark Talon"),
        Ravenwing_Darkshroud("Ravenwing Darkshroud"),
        Sammael("Sammael"),
        Astorath("Astorath"),
        Baal_Predator("Baal Predator"),
        Brother_Corbulo("Brother Corbulo"),
        Captain_Tycho("Captain Tycho"),
        Chief_Librarian_Mephiston("Chief Librarian Mephiston"),
        Commander_Dante("Commander Dante"),
        Death_Company_Dreadnought("Death Company Dreadnought"),
        Death_Company_Intercessors("Death Company Intercessors"),
        Death_Company_Marines("Death Company Marines"),
        Death_Company_Marines_with_Jump_Packs("Death Company Marines with Jump Packs"),
        Furioso_Dreadnought("Furioso Dreadnought"),
        Gabriel_Seth("Gabriel Seth"),
        Lemartes("Lemartes"),
        Librarian_Dreadnought("Librarian Dreadnought"),
        Sanguinary_Guard("Sanguinary Guard"),
        Sanguinary_Priest("Sanguinary Priest"),
        Sanguinary_Priest_with_Jump_Pack("Sanguinary Priest with Jump Pack"),
        The_Sanguinor("The Sanguinor"),
        Tycho_the_Lost("Tycho the Lost"),
        Black_Templars_Gladiator_Lancer("Black Templars Gladiator Lancer"),
        Black_Templars_Gladiator_Reaper("Black Templars Gladiator Reaper"),
        Black_Templars_Gladiator_Valiant("Black Templars Gladiator Valiant"),
        Black_Templars_Impulsor("Black Templars Impulsor"),
        Black_Templars_Repulsor("Black Templars Repulsor"),
        Black_Templars_Repulsor_Executioner("Black Templars Repulsor Executioner"),
        Castellan("Castellan"),
        Chaplain_Grimaldus("Chaplain Grimaldus"),
        Crusader_Squad("Crusader Squad"),
        High_Marshal_Helbrecht("High Marshal Helbrecht"),
        Marshal("Marshal"),
        Primaris_Crusader_Squad("Primaris Crusader Squad"),
        Primaris_Sword_Brethren("Primaris Sword Brethren"),
        The_Emperors_Champion("The Emperor’s Champion");

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