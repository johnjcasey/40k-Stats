package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class GreyKnights implements StructuredArmyData.FactionData {

    public static GreyKnights INSTANCE = new GreyKnights();

    private GreyKnights() {
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
        return List.of(StructuredArmyData.Faction.Agents_Of_The_Imperium, StructuredArmyData.Faction.Imperial_Knights);
    }

    enum Detachments implements StructuredArmyData.DetachmentList {
        Teleport_Strike_Force("Teleport Strike Force", List.of("Domina Liber Daemonica", "First to the Fray", "Inescapable Wrath", "Sigil of Exigence"));

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
        Brother_Captain("Brother-Captain"),
        Brother_Captain_Stern("Brother-Captain Stern"),
        Brotherhood_Champion("Brotherhood Champion"),
        Brotherhood_Chaplain("Brotherhood Chaplain"),
        Brotherhood_Librarian("Brotherhood Librarian"),
        Brotherhood_Techmarine("Brotherhood Techmarine"),
        Brotherhood_Terminator_Squad("Brotherhood Terminator Squad"),
        Castellan_Crowe("Castellan Crowe"),
        Grand_Master("Grand Master"),
        Grand_Master_in_Nemesis_Dreadknight("Grand Master in Nemesis Dreadknight"),
        Grand_Master_Voldus("Grand Master Voldus"),
        Grey_Knights_Land_Raider("Grey Knights Land Raider"),
        Grey_Knights_Land_Raider_Crusader("Grey Knights Land Raider Crusader"),
        Grey_Knights_Land_Raider_Redeemer("Grey Knights Land Raider Redeemer"),
        Grey_Knights_Razorback("Grey Knights Razorback"),
        Grey_Knights_Rhino("Grey Knights Rhino"),
        Grey_Knights_Stormhawk_Interceptor("Grey Knights Stormhawk Interceptor"),
        Grey_Knights_Stormraven_Gunship("Grey Knights Stormraven Gunship"),
        Grey_Knights_Stormtalon_Gunship("Grey Knights Stormtalon Gunship"),
        Grey_Knights_Venerable_Dreadnought("Grey Knights Venerable Dreadnought"),
        Interceptor_Squad("Interceptor Squad"),
        Kaldor_Draigo("Kaldor Draigo"),
        Nemesis_Dreadknight("Nemesis Dreadknight"),
        Paladin_Squad("Paladin Squad"),
        Purgation_Squad("Purgation Squad"),
        Purifier_Squad("Purifier Squad"),
        Servitors("Servitors"),
        Strike_Squad("Strike Squad"),
        Grey_Knights_Thunderhawk_Gunship("Grey Knights Thunderhawk Gunship"),
        Land_Raider_Banisher("Land Raider Banisher");

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