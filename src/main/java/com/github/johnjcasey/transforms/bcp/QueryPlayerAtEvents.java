package com.github.johnjcasey.transforms.bcp;

import com.github.johnjcasey.api.bcp.PlayerAtEventApi;
import com.github.johnjcasey.data.bcp.Event;
import com.github.johnjcasey.data.bcp.PlayerAtEvent;
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

import java.util.ArrayList;
import java.util.List;

public class QueryPlayerAtEvents extends PTransform<PCollection<Event>, PCollection<KV<Event, List<PlayerAtEvent>>>> {
    @Override
    public PCollection<KV<Event, List<PlayerAtEvent>>> expand(PCollection<Event> input) {
        return input.apply(RequestResponseIO.of(new PlayerAtEventApiCaller(), KvCoder.of(SerializableCoder.of(Event.class), ListCoder.of(SerializableCoder.of(PlayerAtEvent.class)))))
                .getResponses()
                .apply(ParDo.of(new DoFn<KV<Event, List<PlayerAtEvent>>, KV<Event, List<PlayerAtEvent>>>() {
                    @ProcessElement
                    public void processElement(@Element KV<Event, List<PlayerAtEvent>> element, OutputReceiver<KV<Event, List<PlayerAtEvent>>> outputReceiver) {
                        List<PlayerAtEvent> withEventIds = new ArrayList<>();
                        for (PlayerAtEvent player : element.getValue()) {
                            PlayerAtEvent clone = player.clone();
                            if (clone.eventId == null) {
                                clone.eventId = element.getKey().id;
                            }
                            withEventIds.add(clone);
                        }
                        outputReceiver.output(KV.of(element.getKey(),withEventIds));
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
