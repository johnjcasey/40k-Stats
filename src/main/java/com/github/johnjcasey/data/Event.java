package com.github.johnjcasey.data;

import java.io.Serializable;
import java.util.Objects;

public class Event implements Serializable {
    public int totalPlayers;
    public String id;

    public boolean started;

    public boolean ended;

    public int numberOfRounds;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return totalPlayers == event.totalPlayers && started == event.started && ended == event.ended && numberOfRounds == event.numberOfRounds && Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPlayers, id, started, ended, numberOfRounds);
    }
}
