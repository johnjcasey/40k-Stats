package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class AdeptaSororitas implements StructuredArmyData.FactionData {

    public static AdeptaSororitas INSTANCE = new AdeptaSororitas();

    private AdeptaSororitas() {
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
        Hallowed_Martyrs("Hallowed Martyrs", List.of("Saintly Example", "Blade of Saint Ellynor", "Litanies of Faith", "Mantle of Ophelia"));

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
        Aestred_Thurga_and_Agathae_Dolan("Aestred Thurga and Agathae Dolan"),
        Arco_flagellants("Arco-flagellants"),
        Battle_Sisters_Squad("Battle Sisters Squad"),
        Canoness("Canoness"),
        Castigator("Castigator"),
        Celestian_Sacresants("Celestian Sacresants"),
        Crusaders("Crusaders"),
        Daemonifuge("Daemonifuge"),
        Death_Cult_Assassins("Death Cult Assassins"),
        Dialogus("Dialogus"),
        Dogmata("Dogmata"),
        Dominion_Squad("Dominion Squad"),
        Exorcist("Exorcist"),
        Hospitaller("Hospitaller"),
        Imagifier("Imagifier"),
        Immolator("Immolator"),
        Junith_Eruita("Junith Eruita"),
        Missionary("Missionary"),
        Mortifiers("Mortifiers"),
        Morvenn_Vahl("Morvenn Vahl"),
        Palatine("Palatine"),
        Paragon_Warsuits("Paragon Warsuits"),
        Penitent_Engines("Penitent Engines"),
        Preacher("Preacher"),
        Repentia_Squad("Repentia Squad"),
        Retributor_Squad("Retributor Squad"),
        Saint_Celestine("Saint Celestine"),
        Seraphim_Squad("Seraphim Squad"),
        Sisters_Novitiate_Squad("Sisters Novitiate Squad"),
        Sororitas_Rhino("Sororitas Rhino"),
        Triumph_of_Saint_Katherine("Triumph of Saint Katherine"),
        Zephyrim_Squad("Zephyrim Squad");

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
