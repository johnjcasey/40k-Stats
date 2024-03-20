package com.github.johnjcasey.transforms;

import com.github.johnjcasey.api.PlayerAtEventApi;
import com.github.johnjcasey.data.Event;
import com.github.johnjcasey.data.PlayerAtEvent;
import org.apache.beam.io.requestresponse.Caller;
import org.apache.beam.io.requestresponse.RequestResponseIO;
import org.apache.beam.io.requestresponse.UserCodeExecutionException;
import org.apache.beam.sdk.coders.KvCoder;
import org.apache.beam.sdk.coders.ListCoder;
import org.apache.beam.sdk.coders.SerializableCoder;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;

import java.util.List;

public class QueryPlayerAtEvents extends PTransform<PCollection<Event>, PCollection<PlayerAtEvent>> {
    @Override
    public PCollection<PlayerAtEvent> expand(PCollection<Event> input) {
        return input.apply(RequestResponseIO.of(new PlayerAtEventApiCaller(), KvCoder.of(SerializableCoder.of(Event.class), ListCoder.of(SerializableCoder.of(PlayerAtEvent.class)))))
                .getResponses()
                .apply(ParDo.of(new DoFn<KV<Event, List<PlayerAtEvent>>, PlayerAtEvent>() {
                    @ProcessElement
                    public void processElement(@Element KV<Event, List<PlayerAtEvent>> element, OutputReceiver< PlayerAtEvent> outputReceiver) {
                        for (PlayerAtEvent player : element.getValue()) {
                            if (player.eventId == null) {
                                player.eventId = element.getKey().id;
                            }
                            outputReceiver.output(player);
                        }
                    }
                }));
    }

    public static class PlayerAtEventApiCaller implements Caller<Event, KV<Event, List<PlayerAtEvent>>> {
        @Override
        public KV<Event, List<PlayerAtEvent>> call(Event request) throws @UnknownKeyFor @NonNull @Initialized UserCodeExecutionException {
            try {
                return KV.of(request, PlayerAtEventApi.INSTANCE.get(request.id));
            } catch (Exception e) {
                throw new UserCodeExecutionException("Unable to retrieve players", e);
            }
        }
    }
}
