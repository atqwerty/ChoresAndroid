package com.example.chores.classes;

import java.io.Serializable;

public class Notification implements Serializable {
    public enum Type {
        TASK_CREATED,
        TASK_EDITED,
        TASK_CHANGED_STATUS,
        BOARD_CREATED,
        USER_ASSIGNED
    }

    private User host;
    private Board board;
    private Task task;
    private Type type;

    public Notification(User host, Board board, Task task, Type type) {
        this.host = host;
        this.board = board;
        this.task = task;
        this.type = type;
    }

    public Notification(User host, Board board, Type type) {
        this.host = host;
        this.board = board;
        this.type = type;
    }

    public Board getBoard() {
        return this.board;
    }

    public Task getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        switch(this.type) {
            case TASK_CREATED:
                return "Task `" + this.task.getName() + "` was created on board `" + this.board.getName() +
                        "` by " + this.host.getName() + " " + this.host.getSurname().charAt(0);
            case TASK_EDITED:
                return "Task `" + this.task.getName() + "` has been edited on board `" + this.board.getName() +
                        "` by " + this.host.getName() + " " + this.host.getSurname().charAt(0);
            case TASK_CHANGED_STATUS:
                return "Task `" + this.task.getName() + "` is now `" + this.task.getStatus() +
                        "` on board `" + this.getBoard().getName() + "` changed by " + this.host.getName() + " " +
                        this.host.getSurname().charAt(0);
            case BOARD_CREATED:
                return "User " + this.host.getName() + " " + this.host.getSurname().charAt(0) + " has created board `" +
                        this.board.getName() + "`";
            case USER_ASSIGNED:
                return "User " + this.host.getName() + " " + this.host.getSurname().charAt(0) +
                        " has assigned you to `" + this.task.getName() + "` on board `" + this.board.getName() + "`";
        }
        return null;
    }
}
