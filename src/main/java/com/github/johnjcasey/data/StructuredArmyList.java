package com.github.johnjcasey.data;

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.joda.time.Instant;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@DefaultSchema(JavaFieldSchema.class)
public class StructuredArmyList implements Serializable {

    public String playerId;
    public String faction;
    public @Nullable String subFaction;
    public @Nullable String detachment;
    public List<Unit> units = new ArrayList<>();
    public @Nullable Instant queryDate;

    public StructuredArmyList() {
    }

    public StructuredArmyList(String playerId, String faction, @Nullable String subFaction, @Nullable String detachment) {
        this.playerId = playerId;
        this.faction = faction;
        this.subFaction = subFaction;
        this.detachment = detachment;
        queryDate = Instant.now();
    }

    public void addUnit(Unit unit) {
        this.units.add(unit);
    }

    @Override
    public String toString() {
        return "StructuredArmyList{" +
                "playerId='" + playerId + '\'' +
                ", faction='" + faction + '\'' +
                ", subFaction='" + subFaction + '\'' +
                ", detachment='" + detachment + '\'' +
                ", units=" + units +
                ", queryDate=" + queryDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StructuredArmyList armyList = (StructuredArmyList) o;
        return Objects.equals(playerId, armyList.playerId) && Objects.equals(faction, armyList.faction) && Objects.equals(subFaction, armyList.subFaction) && Objects.equals(detachment, armyList.detachment) && Objects.equals(units, armyList.units) && Objects.equals(queryDate, armyList.queryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, faction, subFaction, detachment, units, queryDate);
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
