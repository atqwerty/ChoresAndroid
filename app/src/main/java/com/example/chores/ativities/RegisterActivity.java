package com.example.chores.ativities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chores.AppController;
import com.example.chores.MainActivity;
import com.example.chores.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email;
    EditText name;
    EditText surname;
    EditText password;
    Button registerButton;
    Button gotoSignIn;
    String url = "https://chores-backend-atqwerty.herokuapp.com/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        password = findViewById(R.id.password);
        registerButton = findViewById(R.id.register);
        gotoSignIn = findViewById(R.id.goto_login);

        registerButton.setOnClickListener(this);
        gotoSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                register();
                break;
            case R.id.goto_login:
                gotoSignInOnClick();
                break;
        }
    }

    private void register() {
        JSONObject userJSON = new JSONObject();
        try {
            userJSON.put("email", email.getText());
            userJSON.put("name", name.getText());
            userJSON.put("surname", surname.getText());
            userJSON.put("password", surname.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, userJSON,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Intent activityIntent = new Intent(getApplicationContext(), MainActivity.class);
                        activityIntent.putExtra("user", response.toString());
                        startActivity(activityIntent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("adsf", "onErrorResponse: " + error.getMessage());
                    }
                }
        );

        AppController.getInstance(getApplicationContext()).addToRequestQueue(req, "registerUser");

    }

    private void gotoSignInOnClick() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }
}
