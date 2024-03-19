package com.github.johnjcasey.data;

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

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

    public @Nullable String armyListObjectId;
    public List<Game> total_games;

    public Map<@org.checkerframework.checker.nullness.qual.Nullable String,@org.checkerframework.checker.nullness.qual.Nullable String> opponentIds;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerAtEvent that = (PlayerAtEvent) o;
        return Objects.equals(name, that.name) && Objects.equals(userId, that.userId) && Objects.equals(playerId, that.playerId) && Objects.equals(team, that.team) && Objects.equals(army, that.army) && Objects.equals(total_games, that.total_games) && Objects.equals(opponentIds, that.opponentIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userId, playerId, team, army, total_games, opponentIds);
    }

    public static class Team implements Serializable{
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

    public static class Army implements Serializable{
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

    public static class Game implements Serializable{
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
