package com.github.johnjcasey.data.bcp;

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

import java.util.List;
import java.util.Objects;

@DefaultSchema(JavaFieldSchema.class)
public class EventWithPlayersAndLists {
    public Event event;

    public List<PlayerAndList> playersWithList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventWithPlayersAndLists that = (EventWithPlayersAndLists) o;
        return Objects.equals(event, that.event) && Objects.equals(playersWithList, that.playersWithList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event, playersWithList);
    }

    @Override
    public String toString() {
        return "EventWithPlayersAndLists{" +
                "event=" + event +
                ", playersWithList=" + playersWithList +
                '}';
    }
}
