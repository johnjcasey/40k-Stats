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
import org.apache.beam.sdk.schemas.transforms.Filter;
import org.apache.beam.sdk.values.KV;
import org.joda.time.Days;
import org.joda.time.Instant;

public class ParseArmyLists {

    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).create();
        Pipeline pipeline = Pipeline.create(options);

        pipeline.apply(BigQueryIO.readTableRowsWithSchema().from(new TableReference().setProjectId("earnest-smoke-417317").setDatasetId("bcp_data").setTableId("army_lists")))
                .apply(Convert.to(ArmyList.class))
                .apply(Filter.<ArmyList>create().whereFieldName("queryDate", queryDate -> ((Instant) queryDate).isAfter(Instant.now().minus(Days.days(3).toStandardDuration()))))
                .apply(new ParseList())
                .apply(BigQueryIO.<StructuredArmyList>write().withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND).to(new TableReference().setProjectId("earnest-smoke-417317").setDatasetId("bcp_data").setTableId("structured_army_lists")).useBeamSchema());


        pipeline.run();
    }
}
