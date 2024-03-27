package com.github.johnjcasey.data;

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.joda.time.Instant;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Objects;

@DefaultSchema(JavaFieldSchema.class)
public class ArmyList implements Serializable {
    public @Nullable String userId;

    public @Nullable String playerId;

    public @Nullable String armyListText;

    public @Nullable EventMetadata event;

    public @Nullable User user;

    public @Nullable String id;

    public @Nullable String link;

    public @Nullable Instant queryDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArmyList armyList = (ArmyList) o;
        return Objects.equals(userId, armyList.userId) && Objects.equals(playerId, armyList.playerId) && Objects.equals(armyListText, armyList.armyListText) && Objects.equals(event, armyList.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, playerId, armyListText, event);
    }

    public static class EventMetadata implements Serializable {
        public @Nullable String id;

        public @Nullable String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EventMetadata that = (EventMetadata) o;
            return Objects.equals(id, that.id) && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class User {
        public @Nullable String id;

        public @Nullable String firstName;

        public @Nullable String lastName;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, firstName, lastName);
        }


    }

}
