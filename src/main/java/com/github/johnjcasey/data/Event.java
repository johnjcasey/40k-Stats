package com.github.johnjcasey.data;

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.joda.time.Instant;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Objects;

@DefaultSchema(JavaFieldSchema.class)
public class Event implements Serializable {
    public int totalPlayers;

    public @Nullable String name;

    public @Nullable String id;

    public boolean started;

    public boolean ended;

    public int numberOfRounds;

    public @Nullable Instant eventDate;

    public String link;

    public Instant queryDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return totalPlayers == event.totalPlayers && started == event.started && ended == event.ended && numberOfRounds == event.numberOfRounds && Objects.equals(name, event.name) && Objects.equals(id, event.id) && Objects.equals(eventDate, event.eventDate) && Objects.equals(link, event.link) && Objects.equals(queryDate, event.queryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPlayers, name, id, started, ended, numberOfRounds, eventDate, link, queryDate);
    }
}
