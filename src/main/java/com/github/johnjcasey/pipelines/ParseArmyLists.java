package com.github.johnjcasey.pipelines;

import com.github.johnjcasey.data.ArmyList;
import com.github.johnjcasey.data.StructuredArmyList;
import com.github.johnjcasey.transforms.ParseList;
import com.google.api.services.bigquery.model.TableReference;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.KvCoder;
import org.apache.beam.sdk.coders.StringUtf8Coder;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.schemas.transforms.Convert;
import org.apache.beam.sdk.values.KV;

public class ParseArmyLists {
    private static final String TEST_LIST = "\n" +
            "++ Army Roster (Chaos - World Eaters) [1,990pts] ++\n" +
            "\n" +
            "+ Configuration +\n" +
            "\n" +
            "Battle Size: 2. Strike Force (2000 Point limit)\n" +
            "\n" +
            "Blessings of Khorne Reference\n" +
            "\n" +
            "Detachment Choice: Berzerker Warband\n" +
            "\n" +
            "Show/Hide Options\n" +
            "\n" +
            "+ Epic Hero +\n" +
            "\n" +
            "Angron [415pts]\n" +
            "\n" +
            "Lord Invocatus [140pts]\n" +
            "\n" +
            "+ Battleline +\n" +
            "\n" +
            "Jakhals [70pts]: Icon of Khorne\n" +
            ". B: 1 mauler chainblade, 7 chainblades\n" +
            ". . 7x Jakhals: 7x Autopistol, 7x Jakhal chainblades\n" +
            ". Dishonoured w/ skullsmasher\n" +
            "\n" +
            "+ Infantry +\n" +
            "\n" +
            "Eightbound [145pts]\n" +
            ". 2x Eightbound: 2x Eightbound eviscerators\n" +
            ". Eightbound Champion: Lacerators\n" +
            "\n" +
            "Exalted Eightbound [320pts]\n" +
            ". 5x Exalted Eightbound: 5x Eightbound chainfist, 5x Eightbound eviscerator\n" +
            ". Exalted Eightbound Champion: Paired Eightbound chainfists\n" +
            "\n" +
            "Exalted Eightbound [160pts]\n" +
            ". 2x Exalted Eightbound: 2x Eightbound chainfist, 2x Eightbound eviscerator\n" +
            ". Exalted Eightbound Champion: Paired Eightbound chainfists\n" +
            "\n" +
            "Exalted Eightbound [160pts]\n" +
            ". 2x Exalted Eightbound: 2x Eightbound chainfist, 2x Eightbound eviscerator\n" +
            ". Exalted Eightbound Champion: Paired Eightbound chainfists\n" +
            "\n" +
            "+ Beast +\n" +
            "\n" +
            "World Eaters Chaos Spawn [65pts]\n" +
            ". 2x Chaos Spawn: 2x Hideous Mutations\n" +
            "\n" +
            "World Eaters Chaos Spawn [65pts]\n" +
            ". 2x Chaos Spawn: 2x Hideous Mutations\n" +
            "\n" +
            "+ Vehicle +\n" +
            "\n" +
            "Khorne Lord of Skulls [450pts]: Daemongore cannon, Skullhurler\n" +
            "\n" +
            "++ Total: [1,990pts] ++\n" +
            "\n" +
            "Created with BattleScribe (https://battlescribe.net)";

    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).create();
        Pipeline pipeline = Pipeline.create(options);

        pipeline.apply(BigQueryIO.readTableRowsWithSchema().from(new TableReference().setProjectId("earnest-smoke-417317").setDatasetId("bcp_data").setTableId("army_lists")))
                .apply(Convert.to(ArmyList.class))
                .apply(new ParseList())
                .apply(BigQueryIO.<StructuredArmyList>write().withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND).to(new TableReference().setProjectId("earnest-smoke-417317").setDatasetId("bcp_data").setTableId("structured_army_lists")).useBeamSchema());


        pipeline.run();
    }
}
