package com.github.johnjcasey.data;

import com.github.johnjcasey.data.StructuredArmyData.StructuredArmyData;
import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@DefaultSchema(JavaFieldSchema.class)
public class StructuredArmyList implements Serializable {

    public StructuredArmyList(){}

    public StructuredArmyList(String playerId, String faction, @Nullable String subFaction, @Nullable String detachmentList) {
        this.playerId = playerId;
        this.faction = faction;
        this.subFaction = subFaction;
        this.detachmentList = detachmentList;
    }

    public void addUnit(Unit unit) {
        this.units.add(unit);
    }

    public String playerId;

    public String faction;
    public @Nullable String subFaction;

    public @Nullable String detachmentList;

    public List<Unit> units = new ArrayList<>();

    public static class Unit implements Serializable {
        public String name;
        public @Nullable String enhancement;

        public Unit(){}

        public Unit(String name, @Nullable String enhancement) {
            this.name = name;
            this.enhancement = enhancement;
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

    @Override
    public String toString() {
        return "StructuredArmyList{" +
                "faction=" + faction +
                ", subFaction=" + subFaction +
                ", detachment=" + detachmentList +
                ", units=" + units +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StructuredArmyList that = (StructuredArmyList) o;
        return faction == that.faction && subFaction == that.subFaction && Objects.equals(detachmentList, that.detachmentList) && Objects.equals(units, that.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(faction, subFaction, detachmentList, units);
    }
}
