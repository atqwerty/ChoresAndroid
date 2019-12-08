package com.example.chores.classes;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private static final long serialVersionUID = 3113715454440337302L;

    private String name;
    private String surname;
    private String email;
    private String password;
    private ArrayList<Task> tasks;
    private ArrayList<Board> boards;
    private ArrayList<Notification> notifications;
    private String token;

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.tasks = new ArrayList<>();
        this.boards= new ArrayList<>();
        this.notifications = new ArrayList<>();
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

    public String getToken() {
        return this.token;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addBoard(Board board) {
        this.boards.add(board);
    }

    public ArrayList<Board> getBoards() {
        return this.boards;
    }

    public void addNotification(Notification notification) {
        this.notifications.add(0, notification);
    }

    public void addNotifications(ArrayList<Notification> notifications) {
        this.notifications.addAll(0, notifications);
    }

    public ArrayList<Notification> getNotifications() {
        return this.notifications;
    }

    public Notification getNotification(int position) {
        return this.notifications.get(position);
    }
}
