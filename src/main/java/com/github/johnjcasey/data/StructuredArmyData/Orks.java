package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class Orks implements StructuredArmyData.FactionData {

    public static Orks INSTANCE = new Orks();

    private Orks() {
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
        Waaagh_Tribe("Waaagh! Tribe", List.of("Follow Me Ladz", "Headwoppa’s Killchoppa", "Kunnin’ But Brutal", "Supa-Cybork Body"));

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
        Battlewagon("Battlewagon"),
        Beast_Snagga_Boyz("Beast Snagga Boyz"),
        Beastboss("Beastboss"),
        Beastboss_on_Squigosaur("Beastboss on Squigosaur"),
        Big_Mek_in_Mega_Armour("Big Mek in Mega Armour"),
        Big_Mek_with_Kustom_Force_Field("Big Mek with Kustom Force Field"),
        Big_Mek_with_Shokk_Attack_Gun("Big Mek with Shokk Attack Gun"),
        Biged_Bossbunka("Big’ed Bossbunka"),
        Blitza_bommer("Blitza-bommer"),
        Boomdakka_Snazzwagon("Boomdakka Snazzwagon"),
        Boss_Snikrot("Boss Snikrot"),
        Boss_Zagstruk("Boss Zagstruk"),
        Boyz("Boyz"),
        Burna_Boyz("Burna Boyz"),
        Burna_bommer("Burna-bommer"),
        Dakkajet("Dakkajet"),
        Deff_Dread("Deff Dread"),
        Deffkilla_Wartrike("Deffkilla Wartrike"),
        Deffkoptas("Deffkoptas"),
        Flash_Gitz("Flash Gitz"),
        Ghazghkull_Thraka("Ghazghkull Thraka"),
        Gorkanaut("Gorkanaut"),
        Gretchin("Gretchin"),
        Hunta_Rig("Hunta Rig"),
        Kaptin_Badrukk("Kaptin Badrukk"),
        Kill_Rig("Kill Rig"),
        Killa_Kans("Killa Kans"),
        Kommandos("Kommandos"),
        Kustom_Boosta_blasta("Kustom Boosta-blasta"),
        Lootas("Lootas"),
        Mad_Dok_Grotsnik("Mad Dok Grotsnik"),
        Meganobz("Meganobz"),
        Megatrakk_Scrapjet("Megatrakk Scrapjet"),
        Mek("Mek"),
        Mek_Gunz("Mek Gunz"),
        Mekboy_Workshop("Mekboy Workshop"),
        Morkanaut("Morkanaut"),
        Mozrog_Skragbad("Mozrog Skragbad"),
        Nob_on_Smasha_Squig("Nob on Smasha Squig"),
        Nob_with_Waaagh_Banner("Nob with Waaagh! Banner"),
        Nobz("Nobz"),
        Painboss("Painboss"),
        Painboy("Painboy"),
        Rukkatrukk_Squigbuggy("Rukkatrukk Squigbuggy"),
        Shokkjump_Dragsta("Shokkjump Dragsta"),
        Squighog_Boyz("Squighog Boyz"),
        Stompa("Stompa"),
        Stormboyz("Stormboyz"),
        Tankbustas("Tankbustas"),
        Trukk("Trukk"),
        Warbikers("Warbikers"),
        Warboss("Warboss"),
        Warboss_in_Mega_Armour("Warboss in Mega Armour"),
        Wazbom_Blastajet("Wazbom Blastajet"),
        Weirdboy("Weirdboy"),
        Wurrboy("Wurrboy"),
        Zodgrod_Wortsnagga("Zodgrod Wortsnagga"),
        Big_Trakk("Big Trakk"),
        Grot_Mega_tank("Grot Mega-tank"),
        Grot_Tanks("Grot Tanks"),
        Gargantuan_Squiggoth("Gargantuan Squiggoth"),
        Kill_Tank("Kill Tank"),
        Mega_Dread("Mega Dread"),
        Meka_dread("Meka-dread"),
        Nobz_on_Warbikes("Nobz on Warbikes"),
        Squiggoth("Squiggoth"),
        Warboss_on_Warbike("Warboss on Warbike");

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