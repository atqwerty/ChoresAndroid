package com.example.chores.classes;

public class Status {
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
