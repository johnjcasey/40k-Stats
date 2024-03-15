package com.github.johnjcasey.data;

import java.io.Serializable;

public class Event implements Serializable {
    public int totalPlayers;
    public String id;

    public boolean started;

    public boolean ended;
}
