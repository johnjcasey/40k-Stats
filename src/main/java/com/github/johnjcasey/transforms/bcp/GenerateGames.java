package com.github.johnjcasey.transforms.bcp;

import com.github.johnjcasey.data.bcp.ArmyList;
import com.github.johnjcasey.data.bcp.EventWithPlayersAndLists;
import com.github.johnjcasey.data.bcp.PlayerAndList;
import com.github.johnjcasey.data.bcp.PlayerAtEvent;
import com.github.johnjcasey.data.unified.statcheck.Game;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.kafka.common.protocol.types.Field;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class GenerateGames extends PTransform<@NonNull PCollection<EventWithPlayersAndLists>, @NonNull PCollection<Game>> {
    @Override
    public @NonNull PCollection<Game> expand(@NonNull PCollection<EventWithPlayersAndLists> input) {
        return input.apply(ParDo.of(new GenerateGameDoFn()));
    }

    public static class GenerateGameDoFn extends DoFn<EventWithPlayersAndLists, Game> {
        @ProcessElement
        public void processElement(@Element EventWithPlayersAndLists epl, OutputReceiver<Game> outputReceiver) {
            Map<String,PlayerAndList> playerMap = new HashMap<>();
            for (PlayerAndList player : epl.playersWithList) {
                playerMap.put(player.player.playerId, player);
            }
            for (PlayerAndList playerAndList : epl.playersWithList) {
                PlayerAtEvent player = playerAndList.player;
                for (int i = 0; i < player.total_games.size(); i++){
                    PlayerAtEvent.Game bcpGame = player.total_games.get(i);
                    String opponentId = player.opponentIds.get(String.valueOf(i+1));
                    Game game = new Game();
                    game.eventDate = epl.event.eventDate;
                    game.queryDate = epl.event.queryDate;
                    game.eventId = epl.event.id;
                    game.eventName = epl.event.name;
                    game.roundNumber = bcpGame.gameNum;
                    game.player = new Game.Side();
                    game.player.id = player.userId;
                    game.player.name = player.name;
                    game.player.score = bcpGame.gamePoints;
                    game.opponent = new Game.Side();
                    PlayerAndList opponentAndList = playerMap.get(opponentId);
                    if (null != opponentAndList) {
                        PlayerAtEvent opponent = opponentAndList.player;
                        game.opponent.id = opponent.userId;
                        game.opponent.name = opponent.name;
                        if (opponent.total_games != null && opponent.total_games.size() > i && opponent.total_games.get(i) != null) {
                            game.opponent.score = opponent.total_games.get(i).gamePoints;
                        }
                    }
                    outputReceiver.output(game);
                }
            }
        }
    }
}
