package com.github.johnjcasey.data.unified.statcheck;


import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.joda.time.Instant;

import javax.annotation.Nullable;
import java.util.Objects;

@DefaultSchema(JavaFieldSchema.class)
public class Placing {
    public String eventName;
    public String eventId;
    public String playerName;
    public String playerId;
    public @Nullable Integer placing;

    public @Nullable Instant eventDate;

    public Instant queryDate;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Placing placing1 = (Placing) o;
        return Objects.equals(eventName, placing1.eventName) && Objects.equals(eventId, placing1.eventId) && Objects.equals(playerName, placing1.playerName) && Objects.equals(playerId, placing1.playerId) && Objects.equals(placing, placing1.placing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, eventId, playerName, playerId, placing);
    }

    @Override
    public String toString() {
        return "Placing{" +
                "eventName='" + eventName + '\'' +
                ", eventId='" + eventId + '\'' +
                ", playerName='" + playerName + '\'' +
                ", playerId='" + playerId + '\'' +
                ", placing=" + placing +
                '}';
    }
}
