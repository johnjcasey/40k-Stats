package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class AgentsOfTheImperium implements StructuredArmyData.FactionData {

    public static AgentsOfTheImperium INSTANCE = new AgentsOfTheImperium();

    private AgentsOfTheImperium() {
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
        return List.of(StructuredArmyData.Faction.Imperial_Knights);
    }

    enum Detachments implements StructuredArmyData.DetachmentList {
        None("None", List.of());

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
        Callidus_Assassin("Callidus Assassin"),
        Culexus_Assassin("Culexus Assassin"),
        Eversor_Assassin("Eversor Assassin"),
        Exaction_Squad("Exaction Squad"),
        Imperial_Navy_Breachers("Imperial Navy Breachers"),
        Inquisitor("Inquisitor"),
        Inquisitor_Coteaz("Inquisitor Coteaz"),
        Inquisitor_Eisenhorn("Inquisitor Eisenhorn"),
        Inquisitor_Greyfax("Inquisitor Greyfax"),
        Inquisitor_Karamazov("Inquisitor Karamazov"),
        Inquisitorial_Henchmen("Inquisitorial Henchmen"),
        Lord_Inquisitor_Kyria_Draxus("Lord Inquisitor Kyria Draxus"),
        Rogue_Trader_Entourage("Rogue Trader Entourage"),
        Subductor_Squad("Subductor Squad"),
        Vigilant_Squad("Vigilant Squad"),
        Vindicare_Assassin("Vindicare Assassin"),
        Voidsmen_at_Arms("Voidsmen-at-Arms");

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
