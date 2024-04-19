package com.github.johnjcasey.data.bcp;

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.checkerframework.checker.units.qual.N;
import org.joda.time.Instant;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@DefaultSchema(JavaFieldSchema.class)
public class PlayerAtEvent implements Serializable {
    public @Nullable String name;

    public @Nullable String userId;

    public @Nullable String playerId;

    public @Nullable String eventId;

    public @Nullable Team team;

    public @Nullable Army army;

    public @Nullable Integer placing;

    public @Nullable String armyListObjectId;
    public @Nullable List<Game> total_games;

    public @Nullable Map<@org.checkerframework.checker.nullness.qual.Nullable String, @org.checkerframework.checker.nullness.qual.Nullable String> opponentIds;

    public @Nullable Double numWins;

    public @Nullable Instant queryDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerAtEvent player = (PlayerAtEvent) o;
        return Objects.equals(name, player.name) && Objects.equals(userId, player.userId) && Objects.equals(playerId, player.playerId) && Objects.equals(eventId, player.eventId) && Objects.equals(team, player.team) && Objects.equals(army, player.army) && Objects.equals(placing, player.placing) && Objects.equals(armyListObjectId, player.armyListObjectId) && Objects.equals(total_games, player.total_games) && Objects.equals(opponentIds, player.opponentIds) && Objects.equals(numWins, player.numWins) && Objects.equals(queryDate, player.queryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userId, playerId, eventId, team, army, placing, armyListObjectId, total_games, opponentIds, numWins, queryDate);
    }

    @Override
    public String toString() {
        return "PlayerAtEvent{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", playerId='" + playerId + '\'' +
                ", eventId='" + eventId + '\'' +
                ", team=" + team +
                ", army=" + army +
                ", placing=" + placing +
                ", armyListObjectId='" + armyListObjectId + '\'' +
                ", total_games=" + total_games +
                ", opponentIds=" + opponentIds +
                ", numWins=" + numWins +
                ", queryDate=" + queryDate +
                '}';
    }

    public PlayerAtEvent clone(){
        PlayerAtEvent playerAtEvent = new PlayerAtEvent();
        playerAtEvent.name = name;
        playerAtEvent.userId = userId;
        playerAtEvent.playerId = playerId;
        playerAtEvent.eventId = eventId;
        playerAtEvent.team = team;
        playerAtEvent.army = army;
        playerAtEvent.armyListObjectId = armyListObjectId;
        playerAtEvent.total_games = total_games;
        playerAtEvent.opponentIds = opponentIds;
        playerAtEvent.queryDate = queryDate;
        playerAtEvent.placing = placing;
        playerAtEvent.numWins = numWins;
        return playerAtEvent;
    }

    public static class Team implements Serializable {
        public @Nullable String name;
        public @Nullable String id;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Team team = (Team) o;
            return Objects.equals(name, team.name) && Objects.equals(id, team.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, id);
        }
    }

    public static class Army implements Serializable {
        public @Nullable String name;
        public @Nullable String id;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Army army = (Army) o;
            return Objects.equals(name, army.name) && Objects.equals(id, army.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, id);
        }
    }

    public static class Game implements Serializable {
        public int gameNum;
        public int gameResult;
        public int gamePoints;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Game game = (Game) o;
            return gameNum == game.gameNum && gameResult == game.gameResult && gamePoints == game.gamePoints;
        }

        @Override
        public int hashCode() {
            return Objects.hash(gameNum, gameResult, gamePoints);
        }

    }
}
