package com.example.chores.ativities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.chores.MainActivity;
import com.example.chores.R;
import com.example.chores.ativities.LoginActivity;
import com.example.chores.classes.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class MainEmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent activityIntent;

        User currentUser = (User) readFromFile(this);

        if (currentUser != null) {
            JSONObject userJSON = new JSONObject();
            try {
                userJSON.put("email", currentUser.getEmail());
                userJSON.put("name", currentUser.getName());
                userJSON.put("surname", currentUser.getSurname());
                userJSON.put("password", "");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            activityIntent = new Intent(this, MainActivity.class);
            activityIntent.putExtra("user", userJSON.toString());
        } else {
            activityIntent = new Intent(this, LoginActivity.class);
        }

        startActivity(activityIntent);
        setContentView(R.layout.activity_main_empty);
    }

    private Object readFromFile(Context context) {

        Object ret = null;

        try {
            FileInputStream inputStream = new FileInputStream(new File(context.getFilesDir().getAbsolutePath() + "/user.txt"));
            Log.d("adsf", "readFromFile: " + context.getFilesDir().getAbsolutePath());

            if ( inputStream != null ) {
                ObjectInputStream ois = new ObjectInputStream(inputStream);

                ret = ois.readObject();
            }
        }
        catch (Exception e) {
        }



        return ret;
    }
}
