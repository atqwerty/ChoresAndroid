package com.example.chores.ativities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.chores.R;
import com.example.chores.classes.Board;

public class BoardActivity extends AppCompatActivity {

    private TextView boardName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        boardName = this.findViewById(R.id.boardName);

        Board a = (Board) getIntent().getSerializableExtra("targetBoard");
        boardName.setText(a.getName());
    }
}
