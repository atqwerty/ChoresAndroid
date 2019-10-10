package com.example.chores.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Board implements Parcelable {
    private String name;
    private String description;
    private User host;
    private ArrayList<User> participants;
    private ArrayList<String> statuses;
    private ArrayList<Task> tasks;

    public Board(String name, User host) {
        this.name = name;
        this.host = host;
        this.participants = new ArrayList<>();
        this.statuses= new ArrayList<>();
        this.tasks= new ArrayList<>();
        this.participants.add(host);
    }

    public Board(String name, User host, String description) {
        this.name = name;
        this.host = host;
        this.description = description;
        this.participants = new ArrayList<>();
        this.statuses= new ArrayList<>();
        this.tasks= new ArrayList<>();
    }

    private Board(Parcel in) {
        this.name = in.readString();
        this.host = in.readParcelable(User.class.getClassLoader());
//        this.participants = in.readArrayList(User.class.getClassLoader());
//        this.statuses = in.readArrayList(ArrayList.class.getClassLoader());
//        this.tasks = in.readArrayList(ArrayList.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        parcel.writeParcelable(this.host, 1);
        parcel.writeList(this.participants);
        parcel.writeList(this.statuses);
        parcel.writeList(this.tasks);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getHost() {
        return this.host;
    }

    public void addParticipants(User user) {
        this.participants.add(user);
    }

    public void addParticipants(ArrayList<User> newParticipants) {
        this.participants.addAll(newParticipants);
    }

    public ArrayList<User> getParticipants() {
        return this.participants;
    }

    public void addStatue(String status) {
        this.statuses.add(status);
    }

    public ArrayList<String> getStatuses() {
        return this.statuses;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public static final Parcelable.Creator<Board> CREATOR
            = new Parcelable.Creator<Board>() {

        @Override
        public Board createFromParcel(Parcel in) {
            return new Board(in);
        }

        @Override
        public Board[] newArray(int size) {
            return new Board[size];
        }
    };
}
