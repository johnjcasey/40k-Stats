package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class DeathGuard implements StructuredArmyData.FactionData {

    public static DeathGuard INSTANCE = new DeathGuard();

    private DeathGuard() {
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
        Plague_Company("Plague Company", List.of("Deadly Pathogen", "Living Plague", "Shamblerot", "The Droning"));

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
        Biologus_Putrifier("Biologus Putrifier"),
        Blightlord_Terminators("Blightlord Terminators"),
        Death_Guard_Chaos_Lord("Death Guard Chaos Lord"),
        Death_Guard_Chaos_Lord_in_Terminator_Armour("Death Guard Chaos Lord in Terminator Armour"),
        Death_Guard_Chaos_Spawn("Death Guard Chaos Spawn"),
        Death_Guard_Cultists("Death Guard Cultists"),
        Death_Guard_Daemon_Prince("Death Guard Daemon Prince"),
        Death_Guard_Daemon_Prince_with_Wings("Death Guard Daemon Prince with Wings"),
        Death_Guard_Defiler("Death Guard Defiler"),
        Death_Guard_Helbrute("Death Guard Helbrute"),
        Death_Guard_Icon_Bearer("Death Guard Icon Bearer"),
        Death_Guard_Land_Raider("Death Guard Land Raider"),
        Death_Guard_Predator_Annihilator("Death Guard Predator Annihilator"),
        Death_Guard_Predator_Destructor("Death Guard Predator Destructor"),
        Death_Guard_Rhino("Death Guard Rhino"),
        Death_Guard_Sorcerer_in_Terminator_Armour("Death Guard Sorcerer in Terminator Armour"),
        Deathshroud_Terminators("Deathshroud Terminators"),
        Foetid_Bloat_drone("Foetid Bloat-drone"),
        Foul_Blightspawn("Foul Blightspawn"),
        Lord_of_Contagion("Lord of Contagion"),
        Lord_of_Virulence("Lord of Virulence"),
        Malignant_Plaguecaster("Malignant Plaguecaster"),
        Miasmic_Malignifier("Miasmic Malignifier"),
        Mortarion("Mortarion"),
        Myphitic_Blight_haulers("Myphitic Blight-haulers"),
        Noxious_Blightbringer("Noxious Blightbringer"),
        Plague_Marines("Plague Marines"),
        Plague_Surgeon("Plague Surgeon"),
        Plagueburst_Crawler("Plagueburst Crawler"),
        Poxwalkers("Poxwalkers"),
        Tallyman("Tallyman"),
        Typhus("Typhus");

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