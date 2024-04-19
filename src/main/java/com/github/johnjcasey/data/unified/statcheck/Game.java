package com.github.johnjcasey.data.unified.statcheck;

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.joda.time.Instant;

import javax.annotation.Nullable;
import java.util.Objects;

@DefaultSchema(JavaFieldSchema.class)
public class Game {
    public String eventName;
    public String eventId;
    public Integer roundNumber;
    public Side player;

    public Side opponent;

    public @Nullable Instant eventDate;

    public Instant queryDate;

    @DefaultSchema(JavaFieldSchema.class)
    public static class Side {
        public @Nullable String name;
        public @Nullable String id;
        public @Nullable Integer score;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Side player = (Side) o;
            return Objects.equals(name, player.name) && Objects.equals(id, player.id) && Objects.equals(score, player.score);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, id, score);
        }

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", score=" + score +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(eventName, game.eventName) && Objects.equals(eventId, game.eventId) && Objects.equals(roundNumber, game.roundNumber) && Objects.equals(player, game.player) && Objects.equals(opponent, game.opponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, eventId, roundNumber, player, opponent);
    }

    @Override
    public String toString() {
        return "Game{" +
                "eventName='" + eventName + '\'' +
                ", eventId='" + eventId + '\'' +
                ", roundNumber=" + roundNumber +
                ", player=" + player +
                ", opponent=" + opponent +
                '}';
    }
}
