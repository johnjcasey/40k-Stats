package com.github.johnjcasey.data;

import com.github.johnjcasey.data.StructuredArmyData.StructuredArmyData;
import com.github.johnjcasey.data.bcp.ArmyList;
import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.joda.time.Instant;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.*;

@DefaultSchema(JavaFieldSchema.class)
public class StructuredArmyList implements Serializable {

    public @Nullable String userId;
    public @Nullable String playerId;
    public @Nullable ArmyList.EventMetadata event;

    public @Nullable ArmyList.User user;

    public @Nullable String listId;

    public @Nullable String declaredFaction;
    public @Nullable String parsedFaction;
    public @Nullable String subFaction;
    public @Nullable String detachment;

    public @NonNull Set<String> allies = new HashSet<>();
    public @NonNull List<Unit> units = new ArrayList<>();
    public @Nullable Instant queryDate;

    public StructuredArmyList() {
    }

    public StructuredArmyList(@Nullable String userId, @Nullable String playerId, @Nullable ArmyList.EventMetadata event, @Nullable ArmyList.User user, @Nullable String listId, @Nullable String declaredFaction, @Nullable String parsedFaction, @Nullable String subFaction, @Nullable String detachment) {
        this.userId = userId;
        this.playerId = playerId;
        this.event = event;
        this.user = user;
        this.listId = listId;
        this.declaredFaction = declaredFaction;
        this.parsedFaction = parsedFaction;
        this.subFaction = subFaction;
        this.detachment = detachment;
        queryDate = Instant.now();

    }

    public void addUnit(Unit unit) {
        this.units.add(unit);
    }

    public void addAlly(StructuredArmyData.Faction faction){
        allies.add(faction.name());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StructuredArmyList armyList = (StructuredArmyList) o;
        return Objects.equals(userId, armyList.userId) && Objects.equals(playerId, armyList.playerId) && Objects.equals(event, armyList.event) && Objects.equals(user, armyList.user) && Objects.equals(listId, armyList.listId) && Objects.equals(declaredFaction, armyList.declaredFaction) && Objects.equals(parsedFaction, armyList.parsedFaction) && Objects.equals(subFaction, armyList.subFaction) && Objects.equals(detachment, armyList.detachment) && Objects.equals(allies, armyList.allies) && Objects.equals(units, armyList.units) && Objects.equals(queryDate, armyList.queryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, playerId, event, user, listId, declaredFaction, parsedFaction, subFaction, detachment, allies, units, queryDate);
    }

    @Override
    public String toString() {
        return "StructuredArmyList{" +
                "userId='" + userId + '\'' +
                ", playerId='" + playerId + '\'' +
                ", event=" + event +
                ", user=" + user +
                ", listId='" + listId + '\'' +
                ", declaredFaction='" + declaredFaction + '\'' +
                ", parsedFaction='" + parsedFaction + '\'' +
                ", subFaction='" + subFaction + '\'' +
                ", detachment='" + detachment + '\'' +
                ", allies=" + allies +
                ", units=" + units +
                ", queryDate=" + queryDate +
                '}';
    }

    public static class Unit implements Serializable {
        public String name;
        public @Nullable String enhancement;

        public @Nullable String unitText;

        public Unit() {
        }

        public Unit(String name, @Nullable String enhancement, @Nullable String unitText) {
            this.name = name;
            this.enhancement = enhancement;
            this.unitText = unitText;
        }

        @Override
        public String toString() {
            return "Unit{" +
                    "name='" + name + '\'' +
                    ", enhancement='" + enhancement + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Unit unit = (Unit) o;
            return Objects.equals(name, unit.name) && Objects.equals(enhancement, unit.enhancement);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, enhancement);
        }
    }
}
