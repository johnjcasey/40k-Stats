package com.github.johnjcasey.pipelines;

import com.github.johnjcasey.data.StructuredArmyList;
import com.github.johnjcasey.transforms.ParseList;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

public class ParseArmyLists {
    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.create();
        Pipeline pipeline = Pipeline.create(options);

        pipeline.apply(Create.of(TEST_LIST))
                .apply(new ParseList())
                .apply(ParDo.of(new DoFn<StructuredArmyList, StructuredArmyList>() {
                    @ProcessElement
                    public void processElement(@Element StructuredArmyList element){
                        System.out.println(element);
                    }
                }));

        pipeline.run();
    }

    private static final String TEST_LIST= "\n++ Army Roster (Imperium - Adepta Sororitas) [1,995pts] ++\n\n+ Configuration +\n\nBattle Size: 2. Strike Force (2000 Point limit)\n\nDetachment Choice: Hallowed Martyrs Detachment\n\nShow/Hide Options: Agents of the Imperium are visible\n\n+ Epic Hero +\n\nMorvenn Vahl [145pts]\n\nSaint Celestine [135pts]\n\nTriumph of Saint Katherine [125pts]\n\n+ Character +\n\nPalatine [65pts]: Blade of Saint Ellynor, Bolt Pistol\n\n+ Battleline +\n\nBattle Sisters Squad [100pts]\n. 6x Battle Sister: 6x Bolt Pistol, 6x Boltgun, 6x Close combat weapon\n. Battle Sister w/ Simulacrum Imperialus\n. Battle Sister w/ Special or Heavy Weapon: Multi-melta\n. Battle Sister w/ Special Weapon: Meltagun\n. Sister Superior: Condemnor boltgun, Power weapon\n\n+ Infantry +\n\nArco-Flagellants [150pts]\n. 10x Arco-Flagellant: 10x Arco-flails\n\nArco-Flagellants [150pts]\n. 10x Arco-Flagellant: 10x Arco-flails\n\nCrusaders [25pts]\n. Crusaders\n. . 2x Crusader: 2x Power Weapon\n\nCrusaders [25pts]\n. Crusaders\n. . 2x Crusader: 2x Power Weapon\n\nCrusaders [25pts]\n. Crusaders\n. . 2x Crusader: 2x Power Weapon\n\nRetributor Squad [105pts]\n. Retributor: Multi-melta\n. Retributor: Multi-melta\n. Retributor: Multi-melta\n. Retributor: Multi-melta\n. Retributor Superior: Condemnor boltgun, Power weapon\n\nRetributor Squad [105pts]\n. Retributor: Multi-melta\n. Retributor: Multi-melta\n. Retributor: Multi-melta\n. Retributor: Multi-melta\n. Retributor Superior: Condemnor boltgun, Power weapon\n\nSeraphim Squad [70pts]\n. 2x Seraphim: 2x Close combat weapon\n. . 2x 2 Bolt Pistols: 4x Bolt Pistol\n. Seraphim Superior\n. . Plasma Pistol and Power Weapon\n. Seraphim with Special Weapons\n. . 2x Ministorum Hand Flamers\n. Seraphim with Special Weapons\n. . 2x Ministorum Hand Flamers\n\nSeraphim Squad [70pts]\n. 2x Seraphim: 2x Close combat weapon\n. . 2x 2 Bolt Pistols: 4x Bolt Pistol\n. Seraphim Superior\n. . Plasma Pistol and Power Weapon\n. Seraphim with Special Weapons\n. . 2x Ministorum Hand Flamers\n. Seraphim with Special Weapons\n. . 2x Ministorum Hand Flamers\n\nSisters Novitiate Squad [85pts]\n. Novitiate Superior\n. . Plasma Pistol and Power Weapon\n. Novitiate with Sacred Banner\n. Novitiate with Simulacrum Imperialis\n. 7x Sister Novitiate (Melee Weapon): 7x Autopistol, 7x Noviatiate melee weapon\n\n+ Vehicle +\n\nCastigator [140pts]: Castigator battle cannon, Hunter-Killer Missile, Storm bolter\n\nParagon Warsuits [170pts]\n. Paragon Superior: Multi-melta, Paragon Grenade Launchers, Paragon War Blade\n. Paragon Warsuit: Multi-melta, Paragon Grenade Launchers, Paragon War Blade\n. Paragon Warsuit: Multi-melta, Paragon Grenade Launchers, Paragon War Blade\n\n+ Dedicated Transport +\n\nImmolator [115pts]: Hunter-Killer Missile, Twin Multi-melta\n\nImmolator [115pts]: Hunter-Killer Missile, Twin Multi-melta\n\nSororitas Rhino [75pts]: Hunter-Killer Missile\n\n++ Total: [1,995pts] ++";
}
