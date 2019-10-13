package com.example.chores.ui.tasks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chores.classes.Task;

import java.util.ArrayList;

public class TasksViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ArrayList<Task> tasks;

    public TasksViewModel() {
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void sendData(ArrayList<Task> incomingTasks) {
        tasks = incomingTasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}