package com.github.johnjcasey.transforms.bcp;

import com.github.johnjcasey.data.bcp.EventWithPlayersAndLists;
import com.github.johnjcasey.data.bcp.PlayerAndList;
import com.github.johnjcasey.data.unified.statcheck.Player;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Objects;

public class GeneratePlayers extends PTransform<@NonNull PCollection<EventWithPlayersAndLists>, @NonNull PCollection<Player>> {
    @Override
    public @NonNull PCollection<Player> expand(@NonNull PCollection<EventWithPlayersAndLists> input) {
        return input.apply(ParDo.of(new GeneratePlayersDoFn()));
    }

    public static class GeneratePlayersDoFn extends DoFn<EventWithPlayersAndLists, Player> {
        @ProcessElement
        public void processElement(@Element EventWithPlayersAndLists epl, OutputReceiver<Player> outputReceiver) {
            for (PlayerAndList pal : epl.playersWithList) {
                Player player = new Player();
                player.eventDate = epl.event.eventDate;
                player.queryDate = epl.event.queryDate;
                player.name = pal.player.name;
                player.id = pal.player.userId;
                player.eventName = epl.event.name;
                player.eventId = epl.event.id;
                if (null != pal.parsedList) {
                    player.parsedFaction = pal.parsedList.parsedFaction;
                    player.declaredFaction = pal.parsedList.declaredFaction;
                    player.factionsMatch = Objects.equals(player.parsedFaction, player.declaredFaction);
                    player.detachment = pal.parsedList.detachment;
                    player.allies = pal.parsedList.allies;
                }
                outputReceiver.output(player);
            }
        }
    }
}
