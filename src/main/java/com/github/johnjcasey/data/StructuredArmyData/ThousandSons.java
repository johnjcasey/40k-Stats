package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class ThousandSons implements StructuredArmyData.FactionData {

    public static ThousandSons INSTANCE = new ThousandSons();

    private ThousandSons() {
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
        Cult_of_Magic("Cult of Magic", List.of("Arcane Vortex", "Athenaean Scrolls", "Lord of Forbidden Lore", "Umbralefic Crystal"));

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
        Ahriman("Ahriman"),
        Ahriman_on_Disc_of_Tzeentch("Ahriman on Disc of Tzeentch"),
        Exalted_Sorcerer("Exalted Sorcerer"),
        Exalted_Sorcerer_on_Disc_of_Tzeentch("Exalted Sorcerer on Disc of Tzeentch"),
        Infernal_Master("Infernal Master"),
        Magnus_the_Red("Magnus the Red"),
        Mutalith_Vortex_Beast("Mutalith Vortex Beast"),
        Rubric_Marines("Rubric Marines"),
        Scarab_Occult_Terminators("Scarab Occult Terminators"),
        Thousand_Sons_Chaos_Spawn("Thousand Sons Chaos Spawn"),
        Thousand_Sons_Cultists("Thousand Sons Cultists"),
        Thousand_Sons_Daemon_Prince("Thousand Sons Daemon Prince"),
        Thousand_Sons_Daemon_Prince_with_Wings("Thousand Sons Daemon Prince with Wings"),
        Thousand_Sons_Defiler("Thousand Sons Defiler"),
        Thousand_Sons_Forgefiend("Thousand Sons Forgefiend"),
        Thousand_Sons_Helbrute("Thousand Sons Helbrute"),
        Thousand_Sons_Heldrake("Thousand Sons Heldrake"),
        Thousand_Sons_Land_Raider("Thousand Sons Land Raider"),
        Thousand_Sons_Maulerfiend("Thousand Sons Maulerfiend"),
        Thousand_Sons_Predator_Annihilator("Thousand Sons Predator Annihilator"),
        Thousand_Sons_Predator_Destructor("Thousand Sons Predator Destructor"),
        Thousand_Sons_Rhino("Thousand Sons Rhino"),
        Thousand_Sons_Sorcerer("Thousand Sons Sorcerer"),
        Thousand_Sons_Sorcerer_in_Terminator_Armour("Thousand Sons Sorcerer in Terminator Armour"),
        Thousand_Sons_Vindicator("Thousand Sons Vindicator"),
        Tzaangor_Enlightened("Tzaangor Enlightened"),
        Tzaangor_Shaman("Tzaangor Shaman"),
        Tzaangors("Tzaangors");

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