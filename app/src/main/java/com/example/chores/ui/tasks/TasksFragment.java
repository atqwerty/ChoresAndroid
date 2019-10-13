package com.example.chores.ui.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chores.R;
import com.example.chores.TaskRecyclerViewAdapter;
import com.example.chores.ativities.TaskActivity;
import com.example.chores.classes.Task;

import java.util.ArrayList;

public class TasksFragment extends Fragment {

    private TasksViewModel tasksViewModel;
    private RecyclerView recyclerView;
    private TaskRecyclerViewAdapter adapter;

    private TaskRecyclerViewAdapter.ItemClickListener itemClickListener = new TaskRecyclerViewAdapter.ItemClickListener() {
        @Override
        public void onItemClick(Task task, int position) {
            Intent intent = new Intent(getActivity(), TaskActivity.class);
            intent.putExtra("targetTask", task);
            startActivity(intent);
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tasksViewModel =
                ViewModelProviders.of(this).get(TasksViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);

        tasksViewModel = ViewModelProviders.of(this).get(TasksViewModel.class);
//        User a = ViewModelProviders.of(getActivity()).get(FeedViewModel.class).getUser();
        ArrayList<Task> a = ViewModelProviders.of(getActivity()).get(TasksViewModel.class).getTasks();

        recyclerView = root.findViewById(R.id.recycledViewTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new TaskRecyclerViewAdapter(a, itemClickListener);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}