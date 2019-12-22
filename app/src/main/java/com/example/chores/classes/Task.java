package com.example.chores.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Task implements Serializable {
    private static final long serialVersionUID = -1726271017830998326L;

    private int id;
    private String name;
    private User host;
    private String status;
    private Board board;
    private ArrayList<User> usersToDo;
    private String description;

    public Task(String name, User host, String status, Board board) {
        this.name = name;
        this.host = host;
        this.status = status;
        this.board = board;
        this.usersToDo = new ArrayList<>();
    }

    public Task(int id, String name, String status, String description) {
        this.name = name;
        this.status = status;
        this.description = description;
        this.id = id;
    }

    public Task(String name, User host, String status, Board board, ArrayList<User> usersToDo) {
        this.name = name;
        this.host = host;
        this.status = status;
        this.board = board;
        this.usersToDo = new ArrayList<>(usersToDo);
    }

    public Task(String name, User host, String status, Board board, ArrayList<User> usersToDo,
                String description) {
        this.name = name;
        this.host = host;
        this.status = status;
        this.board = board;
        this.usersToDo = new ArrayList<>(usersToDo);
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public Notification setName(String name, User changer) {
        this.name = name;

        return new Notification(changer, this.board, this, Notification.Type.TASK_EDITED);
    }

    public User getHost() {
        return this.host;
    }

    public String getStatus() {
        return this.status;
    }

    public Notification setStatus(String status, User changer) {
        this.status = status;

        return new Notification(changer, this.board, this, Notification.Type.TASK_CHANGED_STATUS);
    }

    public Board getBoard() {
        return this.board;
    }

    public ArrayList<User> getUsersToDo() {
        return this.usersToDo;
    }

    public void appendUsersToDo(ArrayList<User> users, User userCreated) {
        this.usersToDo.addAll(users);

        ArrayList<Notification> notifications = new ArrayList<>();

        for(int i = 0; i < users.size(); i++) {
            users.get(i).addNotification(new Notification(userCreated, this.board, this, Notification.Type.USER_ASSIGNED));
//            notifications.add(new Notification(users.get(i), this.board, this, Notification.Type.USER_ASSIGNED));
        }
    }

    public String getDescription() {
        return this.description;
    }

    public Notification setDescription(String newDescription, User changer) {
        this.description = newDescription;

        return new Notification(changer, this.board, this, Notification.Type.TASK_EDITED);
    }
}
