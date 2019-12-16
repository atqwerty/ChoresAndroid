package com.example.chores.ativities.ui.main;

import android.content.Intent;
import android.os.Bundle;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.chores.R;
import com.example.chores.TaskRecyclerViewAdapter;
import com.example.chores.classes.Board;
import com.example.chores.ui.forms.NewTaskFormActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private TextView statusName;
    private RecyclerView recyclerView;
    private TaskRecyclerViewAdapter adapter;
    private Button createTaskButton;
    private static Board currentBoard;
    private static int counter = 0;

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index, Board board) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        currentBoard = board;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);

        statusName = root.findViewById(R.id.boardName);
        createTaskButton = root.findViewById(R.id.new_task_button);

        statusName.setText(currentBoard.getStatuses().get(counter).getStatusString());

        counter++;

        createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewTaskFormActivity.class);
                intent.putExtra("board", currentBoard);
                startActivity(intent);
            }
        });

        return root;
    }
}