package com.example.chores.ativities.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.chores.ativities.ui.main.PageViewModel;
import com.example.chores.classes.Board;
import com.example.chores.classes.Status;
import com.example.chores.classes.Task;
import com.example.chores.ui.forms.NewTaskFormActivity;
import com.example.chores.ui.tasks.TasksViewModel;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private Button createTaskButton;
    private TasksViewModel tasksViewModel;
    private RecyclerView recyclerView;
    private TaskRecyclerViewAdapter adapter;
    private Status status;
    private static Board currentBoard;
    private static int counter = 0;

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index, Board board) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        if (board.getStatuses().isEmpty()) {
            bundle.putSerializable("status", null);
        } else {
            bundle.putSerializable("status", board.getStatuses().get(counter));
            counter++;
        }
        fragment.setArguments(bundle);
        currentBoard = board;
        return fragment;
    }

    private TaskRecyclerViewAdapter.ItemClickListener itemClickListener = new TaskRecyclerViewAdapter.ItemClickListener() {
        @Override
        public void onItemClick(Task task, int position) {
            Intent intent = new Intent(getActivity(), TaskActivity.class);
            intent.putExtra("targetTask", task);
            counter = 0;
            startActivity(intent);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            status = (Status) getArguments().getSerializable("status");
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);

        tasksViewModel = ViewModelProviders.of(this).get(TasksViewModel.class);

        createTaskButton = root.findViewById(R.id.new_task_button);
        recyclerView = root.findViewById(R.id.recyclerViewTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Task> tasks = sortTasks();

        adapter = new TaskRecyclerViewAdapter(tasks, itemClickListener);
        recyclerView.setAdapter(adapter);

        createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;
                Intent intent = new Intent(getContext(), NewTaskFormActivity.class);
                intent.putExtra("board", currentBoard);
                intent.putExtra("status", status);
                startActivity(intent);
            }
        });

        return root;
    }

    private ArrayList<Task> sortTasks() {
        ArrayList<Task> returningList = new ArrayList<>();

        for (int i = 0; i < currentBoard.getTasks().size(); i++) {
            if (status.getStatusString().equals(currentBoard.getTasks().get(i).getStatus())) {
                Log.d("adsf", "sortTasks: " + status.getStatusString());
                returningList.add(currentBoard.getTasks().get(i));
            }
        }

        return returningList;
    }

    public static void setCounter(int incomingValue) {
        counter = incomingValue;
    }
}