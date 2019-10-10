package com.example.chores.ui.feed;

import android.util.Log;

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
    private User currentUser;

    public FeedViewModel() {
    }

    public void sendData(User incomingUser) {
        currentUser = incomingUser;
    }

    public User getUser()
    {
        return currentUser;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public ArrayList<Notification> getNotifications() {
        return this.currentUser.getNotifications();
    }
}