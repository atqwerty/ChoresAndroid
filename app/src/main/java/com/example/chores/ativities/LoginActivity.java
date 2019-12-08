package com.example.chores.ativities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chores.AppController;
import com.example.chores.MainActivity;
import com.example.chores.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText email;
    EditText password;
    Button loginButton;
    Button gotoRegister;
    String url = "https://chores-backend-atqwerty.herokuapp.com/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);

        email = findViewById(R.id.email_feild);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        gotoRegister = findViewById(R.id.register);

        gotoRegister.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                Log.d("adsf", "onClick: " + "here");
                login();
                break;
            case R.id.register:
                gotoRegisterOnClick();
                break;
        }

    }

    private void login() {
        JSONObject userJSON = new JSONObject();
        try {
            userJSON.put("email", email.getText());
            userJSON.put("password", password.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, userJSON,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("adsf", "onResponse: " + "adsf");
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
                }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        CookieManager.getInstance().setAcceptCookie(true);
        AppController.getInstance(getApplicationContext()).addToRequestQueue(req, "loginUser");
    }

    private void gotoRegisterOnClick() {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
