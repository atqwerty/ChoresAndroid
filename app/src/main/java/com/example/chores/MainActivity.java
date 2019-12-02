package com.example.chores;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String userFromIntent = intent.getStringExtra("user");
        JSONObject userJSON = null;

        try {
            userJSON = new JSONObject(userFromIntent);
            currentUser = new User(userJSON.getString("name"), userJSON.getString("surname"),
                    userJSON.getString("email"), userJSON.getString("password"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // currentUser = (User) readFromFile(this);

        if (currentUser == null) {
            currentUser = generateUser();
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View header = navigationView.getHeaderView(0);

        TextView username = header.findViewById(R.id.email);
        TextView email = header.findViewById(R.id.email);
        username.setText(currentUser.getName() + " " + currentUser.getSurname().charAt(0));
        email.setText(currentUser.getEmail());

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_feed, R.id.nav_boards, R.id.nav_tasks,
                R.id.nav_profile)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        FeedViewModel fvm = ViewModelProviders.of(this).get(FeedViewModel.class);
        BoardsViewModel bvm = ViewModelProviders.of(this).get(BoardsViewModel.class);
        TasksViewModel tvm = ViewModelProviders.of(this).get(TasksViewModel.class);

        fvm.sendData(currentUser);
        bvm.sendData(currentUser.getBoards());
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
            FileOutputStream fileOutputStream = new FileOutputStream(new File(context.getFilesDir().getAbsolutePath() + "/filesmyfile.txt"), false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            Log.e("CUSTOM ERROR", "WriteToFile: " + e.getMessage());
        }
    }

    private Object readFromFile(Context context) {

        Object ret = null;

        try {
            FileInputStream inputStream = new FileInputStream(new File(context.getFilesDir().getAbsolutePath() + "/filesmyfile.txt"));

            if ( inputStream != null ) {
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                ObjectInputStream ois = new ObjectInputStream(inputStream);

                ret = ois.readObject();
            }
        }
        catch (Exception e) {
            Log.e("FILE RELATED", e.getMessage());
        }



        return ret;
    }

    private User generateUser() {
        User user = new User("Default", "User", "generated@gmail.com", "1234");

        User otherUser = new User("Alice", "Alice", "Alice@gmail.com", "1234");

        Board board = new Board("Chores", otherUser);
        board.addParticipants(user);
        user.addBoard(board);
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Notification> notifications= new ArrayList<>();

        notifications.add(new Notification(otherUser, board, Notification.Type.BOARD_CREATED));

        for (int i = 0; i < 100; i++) {
            Task task = new Task("Buy bread", otherUser, "not done", board);
            tasks.add(task);
            notifications.add(new Notification(otherUser, board, task, Notification.Type.TASK_CREATED));
        }

        board.addTasks(tasks);

        user.addTask(tasks.get(0));
        notifications.add(new Notification(user, board, tasks.get(0), Notification.Type.USER_ASSIGNED));
        user.addNotifications(notifications);

        return user;
    }
}
