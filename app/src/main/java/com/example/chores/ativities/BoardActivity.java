package com.example.chores.ativities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.chores.R;
import com.example.chores.TaskRecyclerViewAdapter;
import com.example.chores.classes.Board;
import com.example.chores.classes.Task;

public class BoardActivity extends AppCompatActivity {

    private TextView boardName;
    private RecyclerView recyclerView;
    private TaskRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        boardName = this.findViewById(R.id.boardName);

        Board a = (Board) getIntent().getSerializableExtra("targetBoard");
        boardName.setText("Board: " + a.getName());

        recyclerView = this.findViewById(R.id.recyclerViewTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(BoardActivity.this));

        Log.d("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "onCreate: " + a.getTasks());

        adapter = new TaskRecyclerViewAdapter(a.getTasks(), itemClickListener);

        recyclerView.setAdapter(adapter);
    }

    private TaskRecyclerViewAdapter.ItemClickListener itemClickListener = new TaskRecyclerViewAdapter.ItemClickListener() {
        @Override
        public void onItemClick(Task task, int position) {
            Intent intent = new Intent(BoardActivity.this, TaskActivity.class);
            intent.putExtra("targetTask", task);
            startActivity(intent);
        }
    };
}
