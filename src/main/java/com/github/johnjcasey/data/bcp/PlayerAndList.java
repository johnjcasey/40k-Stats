package com.github.johnjcasey.data.bcp;

import com.github.johnjcasey.data.StructuredArmyList;
import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

import javax.annotation.Nullable;
import java.util.Objects;

@DefaultSchema(JavaFieldSchema.class)
public class PlayerAndList {
    public PlayerAtEvent player;
    public ArmyList list;
    public @Nullable StructuredArmyList parsedList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerAndList that = (PlayerAndList) o;
        return Objects.equals(player, that.player) && Objects.equals(list, that.list) && Objects.equals(parsedList, that.parsedList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, list, parsedList);
    }

    @Override
    public String toString() {
        return "PlayerAndList{" +
                "player=" + player +
                ", list=" + list +
                ", parsedList=" + parsedList +
                '}';
    }
}
