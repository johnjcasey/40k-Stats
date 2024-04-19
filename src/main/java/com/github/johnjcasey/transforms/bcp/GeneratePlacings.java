package com.github.johnjcasey.transforms.bcp;

import com.github.johnjcasey.data.bcp.EventWithPlayersAndLists;
import com.github.johnjcasey.data.bcp.PlayerAndList;
import com.github.johnjcasey.data.unified.statcheck.Placing;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.checkerframework.checker.nullness.qual.NonNull;

public class GeneratePlacings extends PTransform<@NonNull PCollection<EventWithPlayersAndLists>, @NonNull PCollection<Placing>> {
    @Override
    public @NonNull PCollection<Placing> expand(@NonNull PCollection<EventWithPlayersAndLists> input) {
        return input.apply(ParDo.of(new ExtractPlacingsDoFn()));
    }

    public static class ExtractPlacingsDoFn extends DoFn<EventWithPlayersAndLists, Placing> {
        @ProcessElement
        public void processElement(@Element EventWithPlayersAndLists epl, OutputReceiver<Placing> outputReceiver) {
            for (PlayerAndList player : epl.playersWithList) {
                Placing placing = new Placing();
                placing.eventDate = epl.event.eventDate;
                placing.queryDate = epl.event.queryDate;
                placing.eventId = epl.event.id;
                placing.eventName = epl.event.name;
                placing.playerId = player.player.playerId;
                placing.playerName = player.player.name;
                placing.placing = player.player.placing;
                outputReceiver.output(placing);
            }
        }
    }
}
