package com.example.chores.ui.feed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chores.classes.Board;
import com.example.chores.classes.User;

import java.util.ArrayList;

public class FeedViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ArrayList<Board> boards;
    private User hostUser;

    public FeedViewModel() {
        mText = new MutableLiveData<>();
        hostUser = new User("Denis", "Markitanov", "dmarkitanoc@gmail.com", "1234");

        boards = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            boards.add(new Board("chores" + i, hostUser));
        }
    }

    public LiveData<String> getText() {
        return mText;
    }

    public ArrayList<Board> getBoards() {
        return boards;
    }
}