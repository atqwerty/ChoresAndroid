package com.example.chores.classes;

import java.util.ArrayList;

public class Task {
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

    public Task(String name, User host, String status, Board board, String description) {
        this.name = name;
        this.host = host;
        this.status = status;
        this.board = board;
        this.description = description;
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

    public User getHost() {
        return this.host;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Board getBoard() {
        return this.board;
    }

    public ArrayList<User> getUsersToDo() {
        return this.usersToDo;
    }

    public void appendUsersToDo(ArrayList<User> users) {
        this.usersToDo.addAll(users);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }
}
