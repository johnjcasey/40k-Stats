package com.github.johnjcasey.pipelines;

import com.github.johnjcasey.data.StructuredArmyList;
import com.github.johnjcasey.data.bcp.*;
import com.github.johnjcasey.data.unified.statcheck.Game;
import com.github.johnjcasey.data.unified.statcheck.Placing;
import com.github.johnjcasey.data.unified.statcheck.Player;
import com.github.johnjcasey.transforms.ParseList;
import com.github.johnjcasey.transforms.bcp.*;
import com.github.johnjcasey.utils.SchemaUtils;
import com.google.api.services.bigquery.model.TableReference;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.ValueProvider;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class QueryBCPWeekendData {
    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.create();
        Pipeline pipeline = Pipeline.create(options);

        PCollection<Event> events = pipeline.apply(Impulse.create())
                //get the last 7 days of 40k events
                .apply("Generate Range", ParDo.of(new DoFn<byte[], KV<Instant, Instant>>() {
                    @ProcessElement
                    public void processElement(OutputReceiver<KV<Instant, Instant>> outputReceiver) {
                        outputReceiver.output(KV.of(Instant.now().minus(6, ChronoUnit.DAYS), Instant.now()));
                    }
                })).apply(new QueryEvents())
                //filter to events that fired, and are GT sized
                .apply(Filter.by(event -> event.started && event.totalPlayers >= 25 && event.numberOfRounds >= 5));

        PCollection<KV<Event, List<PlayerAtEvent>>> playerAtEvent = events.apply(new QueryPlayerAtEvents());

        PCollection<EventWithPlayersAndLists> eventsWithArmyLists = playerAtEvent.apply(new QueryLists());

        PCollection<EventWithPlayersAndLists> eventsWithParsedLists = eventsWithArmyLists.apply(new ParseList());

        PCollection<PlayerAtEvent> flattenedPlayers = playerAtEvent.apply(Values.create()).apply(Flatten.iterables()).setCoder(SchemaUtils.getSchemaCoder(pipeline, PlayerAtEvent.class));
        PCollection<ArmyList> armyLists = eventsWithParsedLists.apply("Extract ArmyLists", ParDo.of(new DoFn<EventWithPlayersAndLists, ArmyList>() {
            @ProcessElement
            public void processElement(@Element EventWithPlayersAndLists epl, OutputReceiver<ArmyList> outputReceiver) {
                for (PlayerAndList pal : epl.playersWithList) {
                    if (null != pal.list) {
                        outputReceiver.output(pal.list);
                    }
                }
            }
        }));

        PCollection<StructuredArmyList> structuredArmyLists = eventsWithParsedLists.apply("Extract Parsed Lists", ParDo.of(new DoFn<EventWithPlayersAndLists, StructuredArmyList>() {
            @ProcessElement
            public void processElement(@Element EventWithPlayersAndLists epl, OutputReceiver<StructuredArmyList> outputReceiver) {
                for (PlayerAndList pal : epl.playersWithList) {
                    if (null != pal.parsedList) {
                        outputReceiver.output(pal.parsedList);
                    }
                }
            }
        }));

        //Upload data to BQ

        ValueProvider<String> tempBucket = ValueProvider.StaticValueProvider.of("gs://bcp_beam_temp");

        //Write Normalized Data
        events.apply(BigQueryIO.<Event>write().withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND).to(new TableReference().setProjectId("earnest-smoke-417317").setDatasetId("bcp_data").setTableId("events")).useBeamSchema().withCustomGcsTempLocation(tempBucket));
        flattenedPlayers.apply(BigQueryIO.<PlayerAtEvent>write().withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND).to(new TableReference().setProjectId("earnest-smoke-417317").setDatasetId("bcp_data").setTableId("player_at_event")).useBeamSchema().withCustomGcsTempLocation(tempBucket));
        armyLists.apply(BigQueryIO.<ArmyList>write().withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND).to(new TableReference().setProjectId("earnest-smoke-417317").setDatasetId("bcp_data").setTableId("army_lists")).useBeamSchema().withCustomGcsTempLocation(tempBucket));
        structuredArmyLists.apply(BigQueryIO.<StructuredArmyList>write().withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND).to(new TableReference().setProjectId("earnest-smoke-417317").setDatasetId("bcp_data").setTableId("structured_army_lists")).useBeamSchema().withCustomGcsTempLocation(tempBucket));


        //Format and write data for Stat Check
        eventsWithParsedLists.apply(new GeneratePlacings()).apply(BigQueryIO.<Placing>write().withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND).to(new TableReference().setProjectId("earnest-smoke-417317").setDatasetId("statcheck_tables").setTableId("event_placings")).useBeamSchema().withCustomGcsTempLocation(tempBucket));
        eventsWithParsedLists.apply(new GenerateGames()).apply(BigQueryIO.<Game>write().withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND).to(new TableReference().setProjectId("earnest-smoke-417317").setDatasetId("statcheck_tables").setTableId("double_sided_games")).useBeamSchema().withCustomGcsTempLocation(tempBucket));
        eventsWithParsedLists.apply(new GeneratePlayers()).apply(BigQueryIO.<Player>write().withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND).to(new TableReference().setProjectId("earnest-smoke-417317").setDatasetId("statcheck_tables").setTableId("player_at_event")).useBeamSchema().withCustomGcsTempLocation(tempBucket));

        pipeline.run();
    }
}