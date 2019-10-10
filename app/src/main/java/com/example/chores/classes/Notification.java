package com.example.chores.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Notification{
    public enum Type implements Parcelable {
        TASK_CREATED("TASK_CREATED"),
        TASK_EDITED("TASK_EDITED"),
        TASK_CHANGED_STATUS("TASK_CHANGED_STATUS"),
        BOARD_CREATED("BOARD_CREATED"),
        USER_ASSIGNED("USER_ASSIGNED");

        private String option;

        Type(String option){
            this.option = option;
        }

        public String getName(){
            return option;
        }

        private void setOption(String option){
            this.option = option;
        }

        public static final Parcelable.Creator<Type> CREATOR = new Parcelable.Creator<Type>() {

            public Type createFromParcel(Parcel in) {
                Type option = Type.values()[in.readInt()];
                option.setOption(in.readString());
                return option;
            }

            public Type[] newArray(int size) {
                return new Type[size];
            }

        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(ordinal());
            out.writeString(option);
        }
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

//    private Notification(Parcel in) {
//
//        this.host = in.readParcelable(User.class.getClassLoader());
//        this.board= in.readParcelable(Board.class.getClassLoader());
//        this.task = in.readParcelable(Task.class.getClassLoader());
//        this.type = in.readParcelable(Type.class.getClassLoader());
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int flags) {
//        parcel.writeParcelable(this.host, 1);
//        parcel.writeParcelable(this.board, 1);
//
//        if (!this.task.equals(null)) {
//            parcel.writeParcelable(this.task, 1);
//        }
//
//
//        parcel.writeParcelable(this.type, 1);
//
//    }


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
                return "Task " + this.task.getName() + " was created on boards " + this.board.getName() +
                        " by " + this.host.getName() + " " + this.host.getSurname().charAt(0);
            case TASK_EDITED:
                return "Task " + this.task.getName() + " has been edited on boards " + this.board.getName() +
                        " by " + this.host.getName() + " " + this.host.getSurname().charAt(0);
            case TASK_CHANGED_STATUS:
                return "Task " + this.task.getName() + " is now " + this.task.getStatus() +
                        " on boards " + this.getBoard().getName() + " changed by " + this.host.getName() + " " +
                        this.host.getSurname().charAt(0);
            case BOARD_CREATED:
                return "User " + this.host.getName() + " " + this.host.getSurname().charAt(0) + " has created boards " +
                        this.board.getName();
            case USER_ASSIGNED:
                return "User " + this.host.getName() + " " + this.host.getSurname().charAt(0) +
                        " has assigned you to " + this.task.getName() + " on boards " + this.board.getName();
        }
        return null;
    }

//    public static final Parcelable.Creator<Notification> CREATOR
//            = new Parcelable.Creator<Notification>() {
//
//        @Override
//        public Notification createFromParcel(Parcel in) {
//            return new Notification(in);
//        }
//
//        @Override
//        public Notification[] newArray(int size) {
//            return new Notification[size];
//        }
//    };
}
