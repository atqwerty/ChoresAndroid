package com.example.chores.ui.boards;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chores.classes.Board;
import com.example.chores.classes.Notification;
import com.example.chores.classes.User;

import java.util.ArrayList;

public class BoardsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ArrayList<Board> boards;
    private User currentUser;

    public BoardsViewModel() {
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void sendData(ArrayList<Board> incomingBoards, User incomingCurrentUser) {
        boards = incomingBoards;
        currentUser = incomingCurrentUser;
    }

    public ArrayList<Board> getBoards() {
        return boards;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}