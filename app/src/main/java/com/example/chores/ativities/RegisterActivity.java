package com.example.chores.ativities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.chores.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button gotoSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        gotoSignIn = findViewById(R.id.goto_login);

        gotoSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                break;
            case R.id.goto_login:
                gotoSignInOnClick();
                break;
        }
    }

    private void gotoSignInOnClick() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }
}
