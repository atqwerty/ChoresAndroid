package com.example.chores.ativities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.chores.R;
import com.example.chores.classes.Status;
import com.example.chores.classes.Task;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {

    private TextView taskName;
    private TextView status;
    private TextView description;
    private TextView info;
    private Button share;
    private Spinner spinner;
    private static String[] options = {"asdf", "asdhfskadv"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        taskName = this.findViewById(R.id.taskName);
        status = this.findViewById(R.id.status);
        description = this.findViewById(R.id.description);
        info = this.findViewById(R.id.info);
        share = this.findViewById(R.id.buttonShare);
        spinner = this.findViewById(R.id.spinner);

        Task a = (Task) getIntent().getSerializableExtra("targetTask");
        ArrayList<Status> statuses = (ArrayList<Status>) getIntent().getSerializableExtra("statuses");
        ArrayList<String> statusNames = new ArrayList<>();

        for (int i = 0; i < statuses.size(); i++) {
            statusNames.add(statuses.get(i).getStatusString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(TaskActivity.this, android.R.layout.simple_spinner_dropdown_item, statusNames);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        for (int i = 0; i < statuses.size(); i++) {
            if (statuses.get(i).getStatusString().equals(a.getStatus())) {
                spinner.setSelection(i);
            }
        }

        taskName.append(a.getName());

        if (a.getDescription() != null) {
            description.append(a.getDescription());
        } else {
            description.append("No description was provided");
        }


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
