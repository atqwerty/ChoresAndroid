package com.example.chores.ui.boards;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chores.classes.Board;
import com.example.chores.classes.Notification;

import java.util.ArrayList;

public class BoardsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ArrayList<Board> boards;

    public BoardsViewModel() {
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void sendData(ArrayList<Board> incomingBoaards) {
        boards = incomingBoaards;
    }

    public ArrayList<Board> getBoards() {
        return boards;
    }
}