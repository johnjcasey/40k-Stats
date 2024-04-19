package com.github.johnjcasey.data.unified.statcheck;

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.joda.time.Instant;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Set;

@DefaultSchema(JavaFieldSchema.class)
public class Player {
    public @Nullable String name;
    public @Nullable String id;
    public @Nullable String eventName;
    public @Nullable String eventId;
    public @Nullable String declaredFaction;
    public @Nullable String parsedFaction;
    public Boolean factionsMatch = true;
    public @Nullable String detachment;
    public @Nullable Set<String> allies;
    public @Nullable Instant eventDate;

    public Instant queryDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(id, player.id) && Objects.equals(eventName, player.eventName) && Objects.equals(eventId, player.eventId) && Objects.equals(declaredFaction, player.declaredFaction) && Objects.equals(parsedFaction, player.parsedFaction) && Objects.equals(factionsMatch, player.factionsMatch) && Objects.equals(detachment, player.detachment) && Objects.equals(allies, player.allies) && Objects.equals(eventDate, player.eventDate) && Objects.equals(queryDate, player.queryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, eventName, eventId, declaredFaction, parsedFaction, factionsMatch, detachment, allies, eventDate, queryDate);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventId='" + eventId + '\'' +
                ", declaredFaction='" + declaredFaction + '\'' +
                ", parsedFaction='" + parsedFaction + '\'' +
                ", factionsMatch=" + factionsMatch +
                ", detachment='" + detachment + '\'' +
                ", allies=" + allies +
                ", eventDate=" + eventDate +
                ", queryDate=" + queryDate +
                '}';
    }
}
