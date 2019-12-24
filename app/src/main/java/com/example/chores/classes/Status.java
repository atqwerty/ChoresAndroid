package com.example.chores.classes;

import java.io.Serializable;

public class Status implements Serializable{
    private int id;
    private String statusString;

    public Status(int id, String statusString) {
        this.id = id;
        this.statusString = statusString;
    }

    public int getId() {
        return id;
    }

    public String getStatusString() {
        return statusString;
    }
}
