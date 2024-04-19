package com.github.johnjcasey.data.unified.statcheck;

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.joda.time.Instant;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

@DefaultSchema(JavaFieldSchema.class)
public class Player {
    public @Nullable String name;
    public @Nullable String id;
    public @Nullable String eventName;
    public @Nullable String eventId;
    public @Nullable String faction;
    public @Nullable String detachment;
    public @Nullable List<String> allies;
    public @Nullable Instant eventDate;

    public Instant queryDate;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(id, player.id) && Objects.equals(eventName, player.eventName) && Objects.equals(eventId, player.eventId) && Objects.equals(faction, player.faction) && Objects.equals(detachment, player.detachment) && Objects.equals(allies, player.allies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, eventName, eventId, faction, detachment, allies);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventId='" + eventId + '\'' +
                ", faction='" + faction + '\'' +
                ", detachment='" + detachment + '\'' +
                ", allies=" + allies +
                '}';
    }
}
