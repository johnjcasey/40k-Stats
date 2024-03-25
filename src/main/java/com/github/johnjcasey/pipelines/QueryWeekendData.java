package com.github.johnjcasey.pipelines;

import com.github.johnjcasey.data.ArmyList;
import com.github.johnjcasey.data.Event;
import com.github.johnjcasey.data.PlayerAtEvent;
import com.github.johnjcasey.transforms.QueryEvents;
import com.github.johnjcasey.transforms.QueryLists;
import com.github.johnjcasey.transforms.QueryPlayerAtEvents;
import com.google.api.services.bigquery.model.TableReference;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.ValueProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.Impulse;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class QueryWeekendData {
    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.create();
        Pipeline pipeline = Pipeline.create(options);

        PCollection<Event> events = pipeline.apply(Impulse.create())
                //get the last 7 days of 40k events
                .apply(ParDo.of(new DoFn<byte[], KV<Instant, Instant>>() {
                    @ProcessElement
                    public void processElement(OutputReceiver<KV<Instant, Instant>> outputReceiver) {
                        outputReceiver.output(KV.of(Instant.now().minus(7, ChronoUnit.DAYS), Instant.now()));
                    }
                })).apply(new QueryEvents())
                //filter to events that fired, and are GT sized
                .apply(Filter.by(event -> event.started && event.ended && event.totalPlayers >= 25 && event.numberOfRounds >= 5));

        PCollection<PlayerAtEvent> playerAtEvent = events.apply(new QueryPlayerAtEvents());

        PCollection<ArmyList> armyLists = playerAtEvent.apply("Get List Ids", ParDo.of(new DoFn<PlayerAtEvent, String>() {
            @ProcessElement
            public void processElement(@Element PlayerAtEvent player, OutputReceiver<String> outputReceiver) {
                if (null != player.armyListObjectId) {
                    outputReceiver.output(player.armyListObjectId);
                }
            }
        })).apply(new QueryLists());

        //Upload data to BQ

        ValueProvider<String> tempBucket = ValueProvider.StaticValueProvider.of("gs://bcp_beam_temp");

        events.apply(BigQueryIO.<Event>write().withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND).to(new TableReference().setProjectId("earnest-smoke-417317").setDatasetId("bcp_data").setTableId("events")).useBeamSchema().withCustomGcsTempLocation(tempBucket));
        playerAtEvent.apply(BigQueryIO.<PlayerAtEvent>write().withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND).to(new TableReference().setProjectId("earnest-smoke-417317").setDatasetId("bcp_data").setTableId("player_at_event")).useBeamSchema().withCustomGcsTempLocation(tempBucket));
        armyLists.apply(BigQueryIO.<ArmyList>write().withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND).to(new TableReference().setProjectId("earnest-smoke-417317").setDatasetId("bcp_data").setTableId("army_lists")).useBeamSchema().withCustomGcsTempLocation(tempBucket));

        pipeline.run();
    }
}
