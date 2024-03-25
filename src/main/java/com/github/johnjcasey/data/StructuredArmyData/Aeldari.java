package com.github.johnjcasey.data.StructuredArmyData;

import java.util.List;

public class Aeldari implements StructuredArmyData.FactionData {

    public static Aeldari INSTANCE = new Aeldari();

    private Aeldari(){}

    @Override
    public Class<? extends StructuredArmyData.DetachmentList> getDetachments() {
        return Detachments.class;
    }

    @Override
    public Class<? extends StructuredArmyData.DataSheetList> getDataSheets() {
        return DataSheets.class;
    }

    enum Detachments implements StructuredArmyData.DetachmentList {
        Battle_Host("Battle Host", List.of("Fate’s Messenger","Reader of the Runes","The Phoenix Gem","The Weeping Stones");

        public final String name;

        public final List<String> enhancements;

        @Override
        public String getName(){
            return name;
        }

        @Override
        public List<String> getEnhancements() {
            return enhancements;
        }


        Detachments(String name, List<String> enhancements){
            this.name = name;
            this.enhancements = enhancements;
        }
    }

    public enum DataSheets implements StructuredArmyData.DataSheetList {
        Asurmen("Asurmen"),
        Autarch("Autarch"),
        Autarch_Skyrunner("Autarch Skyrunner"),
        Autarch_Wayleaper("Autarch Wayleaper"),
        Avatar_of_Khaine("Avatar of Khaine"),
        Baharroth("Baharroth"),
        Corsair_Voidreavers("Corsair Voidreavers"),
        Corsair_Voidscarred("Corsair Voidscarred"),
        Crimson_Hunter("Crimson Hunter"),
        Dark_Reapers("Dark Reapers"),
        Death_Jester("Death Jester"),
        Dire_Avengers("Dire Avengers"),
        Eldrad Ulthran("Eldrad Ulthran"),
        Falcon("Falcon"),
        Farseer("Farseer"),
        Farseer_Skyrunner("Farseer Skyrunner"),
        Fire_Dragons("Fire Dragons"),
        Fire_Prism("Fire Prism"),
        Fuegan("Fuegan"),
        Guardian_Defenders("Guardian Defenders"),
        Hemlock_Wraithfighter("Hemlock Wraithfighter"),
        Howling_Banshees("Howling Banshees"),
        Illic_Nightspear("Illic Nightspear"),
        Jain_Zar("Jain Zar"),
        Karandras("Karandras"),
        Maugan_Ra("Maugan Ra"),
        Night_Spinner("Night Spinner"),
        Prince_Yriel("Prince Yriel"),
        Rangers("Rangers"),
        Shadowseer("Shadowseer"),
        Shining Spears("Shining Spears"),
        Shroud Runners("Shroud Runners"),
        Skyweavers("Skyweavers"),
        Solitaire("Solitaire"),
        Spiritseer("Spiritseer"),
        Starweaver("Starweaver"),
        Storm_Guardians("Storm Guardians"),
        Striking_Scorpions("Striking Scorpions"),
        Support_Weapons("Support Weapons"),
        Swooping_Hawks("Swooping Hawks"),
        The_Visarch("The Visarch"),
        The_Yncarne("The Yncarne"),
        Troupe("Troupe"),
        Troupe_Master("Troupe Master"),
        Voidweaver("Voidweaver"),
        Vyper("Vyper"),
        War Walker("War Walker"),
        Warlock("Warlock"),
        Warlock_Conclave("Warlock Conclave"),
        Warlock_Skyrunner("Warlock Skyrunner"),
        Warlock_Skyrunner_Conclave("Warlock Skyrunner Conclave"),
        Warp_Spiders("Warp Spiders"),
        Wave_Serpent("Wave Serpent"),
        Webway_Gate("Webway Gate"),
        Windriders("Windriders"),
        Wraithblades("Wraithblades"),
        Wraithguard("Wraithguard"),
        Wraithknight("Wraithknight"),
        Wraithlord("Wraithlord"),
        Yvraine("Yvraine"),
        Cobra("Cobra"),
        Hornet("Hornet"),
        Irillyth("Irillyth"),
        Lynx("Lynx"),
        Nightwing("Nightwing"),
        Scorpion("Scorpion"),
        Skathach_Wraithknight("Skathach Wraithknight"),
        Shadow_Spectres("Shadow Spectres"),
        Phantom_Titan("Phantom Titan"),
        Revenant_Titan("Revenant Titan"),
        Warp_Hunter("Warp Hunter"),
        Wraithseer("Wraithseer");
 

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
