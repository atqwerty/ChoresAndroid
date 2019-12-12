package com.example.chores;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chores.ativities.LoginActivity;
import com.example.chores.classes.Board;
import com.example.chores.classes.Notification;
import com.example.chores.classes.Task;
import com.example.chores.classes.User;
import com.example.chores.ui.boards.BoardsViewModel;
import com.example.chores.ui.feed.FeedViewModel;
import com.example.chores.ui.tasks.TasksViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.webkit.CookieManager;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String userFromIntent = intent.getStringExtra("user");
        JSONObject userJSON;

        try {
            userJSON = new JSONObject(userFromIntent);
            Log.d("adsf", "onCreate: " + userJSON.toString());
            currentUser = new User(userJSON.getString("name"), userJSON.getString("surname"),
                    userJSON.getString("email"), userJSON.getString("password"));
            Log.d("adsf", "onCreate: " + currentUser.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View header = navigationView.getHeaderView(0);

        TextView username = header.findViewById(R.id.email);
        TextView email = header.findViewById(R.id.email);
        username.setText(currentUser.getName() + " " + currentUser.getSurname().charAt(0));
        email.setText(currentUser.getEmail());

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_feed, R.id.nav_boards, R.id.nav_tasks,
                R.id.nav_profile, R.id.logout)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        FeedViewModel fvm = ViewModelProviders.of(this).get(FeedViewModel.class);
        BoardsViewModel bvm = ViewModelProviders.of(this).get(BoardsViewModel.class);
        TasksViewModel tvm = ViewModelProviders.of(this).get(TasksViewModel.class);

        fetchData(bvm, tvm);

        fvm.sendData(currentUser);
        tvm.sendData(currentUser.getTasks());

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        WriteToFile(currentUser, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void WriteToFile(User user, Context context) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(context.getFilesDir().getAbsolutePath() + "/user.txt"), false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            Log.e("CUSTOM ERROR", "WriteToFile: " + e.getMessage());
        }
    }

    private void fetchData (final BoardsViewModel bvm, TasksViewModel tvm) {
        String url = "https://chores-backend-atqwerty.herokuapp.com/board/all";
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("adsf", "onResponse: " + response.toString());
                        bvm.sendData(prettifyData(response), currentUser);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("adsf", "onErrorResponse: " + error.getMessage());

                    }
                }
        ){
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Authorization", "Bearer " + currentUser.getToken());
                return headers;
            }
        };

        AppController.getInstance(this).addToRequestQueue(req, "getAllUserBoards");
    }

    private ArrayList<Board> prettifyData(JSONArray response) {
        ArrayList<Board> prettifiedData = new ArrayList<>();

        for (int i = 0; i < response.length(); i++) {
            try {
                prettifiedData.add(new Board(response.getJSONObject(i).getInt("ID"),
                        response.getJSONObject(i).getString("title"),
                        response.getJSONObject(i).getString("description"), currentUser, this));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return prettifiedData;
    }

    public void smth(MenuItem item) {
        File dir = new File(this.getFilesDir().getAbsolutePath() + "/user.txt");
        dir.delete();
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

}
