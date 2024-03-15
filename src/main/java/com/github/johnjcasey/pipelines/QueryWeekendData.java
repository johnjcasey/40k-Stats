package com.github.johnjcasey.pipelines;

import com.github.johnjcasey.data.Event;
import com.github.johnjcasey.data.PlayerAtEvent;
import com.github.johnjcasey.transforms.QueryEvents;
import com.github.johnjcasey.transforms.QueryPlayerAtEvents;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.KV;

public class QueryWeekendData {
    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.create();
        Pipeline pipeline = Pipeline.create(options);

        pipeline.apply(Impulse.create())
                //get the last 7 days of 40k events
                .apply(new QueryEvents())
                //filter to events that fired, and are GT sized
                .apply(Filter.by(event -> event.started && event.ended && event.totalPlayers >= 25 && event.numberOfRounds >= 5))
                .apply(ParDo.of(new DoFn<Event, Event>() {
                    @ProcessElement
                    public void processElement(@Element Event e, OutputReceiver<Event> outputReceiver) {
                        System.out.println(e.id);
                        outputReceiver.output(e);
                    }
                }))
                .apply(new QueryPlayerAtEvents())
                .apply(ParDo.of(new DoFn<KV<Event, PlayerAtEvent>, KV<Event, PlayerAtEvent>>() {
                    @ProcessElement
                    public void processElement(@Element KV<Event, PlayerAtEvent> element){
                        System.out.println(element.getKey().id + ":" + element.getValue().name);
                    }
                }));

        pipeline.run();
    }
}
