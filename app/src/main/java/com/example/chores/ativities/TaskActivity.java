package com.example.chores.ativities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.chores.R;
import com.example.chores.classes.Task;

public class TaskActivity extends AppCompatActivity {

    private TextView taskName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        taskName = this.findViewById(R.id.taskName);

        Task a = (Task) getIntent().getSerializableExtra("targetTask");
        taskName.setText(a.getName());
    }
}
