package com.example.chores.ativities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chores.R;
import com.example.chores.classes.Task;

public class TaskActivity extends AppCompatActivity {

    private TextView taskName;
    private TextView status;
    private TextView description;
    private TextView info;
    private Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        taskName = this.findViewById(R.id.taskName);
        status = this.findViewById(R.id.status);
        description = this.findViewById(R.id.description);
        info = this.findViewById(R.id.info);
        share = this.findViewById(R.id.buttonShare);

        Task a = (Task) getIntent().getSerializableExtra("targetTask");

        taskName.append(a.getName());
        status.append(a.getStatus());

        if (a.getDescription() != null) {
            description.append(a.getDescription());
        } else {
            description.append("No description was provided");
        }

        info.setText("Task was created by " + a.getHost().getName() + " " + a.getHost().getSurname().charAt(0) +
                " on board: " + a.getBoard().getName());
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "body";
                String shareSub = "sub";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "Share using"));
            }
        });

    }

}
