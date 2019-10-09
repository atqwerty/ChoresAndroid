package com.example.chores.ui.feed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chores.classes.Board;
import com.example.chores.classes.Notification;
import com.example.chores.classes.Task;
import com.example.chores.classes.User;

import java.util.ArrayList;

public class FeedViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ArrayList<Notification> notifs;
    private User hostUser;

    public FeedViewModel() {
        mText = new MutableLiveData<>();
        hostUser = new User("Denis", "Markitanov", "dmarkitanoc@gmail.com", "1234");
        User alice = new User("Alice", "Dude", "aliceD@gmail.com", "1234");
        Board board = new Board("Chores", alice);
        Task task= new Task("Buy Bread", alice, "not done", board);

        notifs = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            notifs.add(new Notification(alice, board, Notification.Type.BOARD_CREATED));
        }
    }

    public LiveData<String> getText() {
        return mText;
    }

    public ArrayList<Notification> getNotifications() {
        return notifs;
    }
}