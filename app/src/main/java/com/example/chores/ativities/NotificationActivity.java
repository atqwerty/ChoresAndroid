package com.example.chores.ativities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.chores.R;
import com.example.chores.classes.Notification;

public class NotificationActivity extends AppCompatActivity {

    private TextView notifName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        Intent intent = getIntent();

        notifName = this.findViewById(R.id.notifName);
        notifName.setText(intent.getStringExtra("notif"));
    }
}
