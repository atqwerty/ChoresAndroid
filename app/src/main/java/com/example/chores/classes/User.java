package com.example.chores.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User implements Parcelable {
    private String name;
    private String surname;
    private String email;
    private String password;
    private ArrayList<Task> tasks;
    private ArrayList<Board> boards;
    private ArrayList<Notification> notifications;

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.tasks = new ArrayList<Task>();
        this.boards= new ArrayList<Board>();
        this.notifications = new ArrayList<>();
    }

    private User(Parcel in) {
        this.name = in.readString();
        this.surname = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.tasks = in.readArrayList(null);
        this.boards = in.readArrayList(null);
        this.notifications = in.readArrayList(null);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.name);
        parcel.writeString(this.surname);
        parcel.writeString(this.email);
        parcel.writeString(this.password);
        parcel.writeList(this.tasks);
        parcel.writeList(this.boards);

    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmail() {
        return this.email;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void addBoard(Board board) {
        this.boards.add(board);
    }

    public void addNotification(Notification notification) {
        this.notifications.add(notification);
    }

    public ArrayList<Notification> getNotifications() {
        return this.notifications;
    }

    public Notification getNotification(int position) {
        return this.notifications.get(position);
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
