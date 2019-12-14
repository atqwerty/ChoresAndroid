package com.example.chores.classes;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chores.AppController;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable {

    private int id;
    private String name;
    private String description;
    private User host;
    private ArrayList<User> participants;
    private ArrayList<String> statuses;
    private ArrayList<Task> tasks;

    public Board(int id, String name, User host, Context context) {
        this.id = id;
        this.name = name;
        this.host = host;
        this.participants = new ArrayList<>();
        this.statuses= new ArrayList<>();
        this.tasks= new ArrayList<>();
        this.participants.add(host);

        fetchTasks(this.id, context);
    }

    public Board(int id, String name,  String description, User host, Context context) {
        this.id = id;
        this.name = name;
        this.host = host;
        this.description = description;
        this.participants = new ArrayList<>();
        this.statuses= new ArrayList<>();
        this.tasks= new ArrayList<>();

        fetchTasks(this.id, context);
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getHost() {
        return this.host;
    }

    public void addParticipants(User user) {
        this.participants.add(user);
    }

    public void addParticipants(ArrayList<User> newParticipants) {
        this.participants.addAll(newParticipants);
    }

    public ArrayList<User> getParticipants() {
        return this.participants;
    }

    public void addStatue(String status) {
        this.statuses.add(status);
    }

    public ArrayList<String> getStatuses() {
        return this.statuses;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void addTasks(ArrayList<Task> tasks) {
        this.tasks.addAll(0, tasks);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    private void fetchTasks(int id, Context context) {
        String url = "https://chores-backend-atqwerty.herokuapp.com/board/" + id;

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            tasks.add(new Task(response.getJSONObject(i).getInt("ID"),
                                    response.getJSONObject(i).getString("title"),
                                    response.getJSONObject(i).getString("status"),
                                    response.getJSONObject(i).getString("description")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.d("adsf", "onResponse: " + tasks);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("adsf", "onErrorResponse: " + error.getMessage());

                }
        });

        AppController.getInstance(context).addToRequestQueue(req, "getBoardTasks");
    }

    private fetchStatuses(int id, Context context) {
        String url = "https://chores-backend-atqwerty.herokuapp.com/board/" + id + "getStatuses";

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("adsf", "onErrorResponse: " + error.getMessage());
                    }
                });
    }
}
