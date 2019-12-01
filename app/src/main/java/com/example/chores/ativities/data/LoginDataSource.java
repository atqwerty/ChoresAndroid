package com.example.chores.ativities.data;

import android.content.Context;
import android.util.Log;

import androidx.navigation.ui.AppBarConfiguration;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.chores.AppController;
import com.example.chores.ativities.data.model.LoggedInUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(final String username, String password, Context context) {
        String url = "https://chores-backend-atqwerty.herokuapp.com/login";

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser userAssigned = null;

            JSONObject user = new JSONObject();
            user.put("email", username);
            user.put("password", password);
            Log.d("adsf", "login: " + user);

            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, user,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("adsf", "onResponse: " + response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("adsf", "onErrorResponse: " + error.toString());
                        }
                    }
            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };

            AppController ac = new AppController();
            ac.addToRequestQueue(req, "", context);
            return new Result.Success<>(assignUser(username));

        } catch (Exception e) {
            Log.d("adsf", "login: " + e.getMessage().toString());
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    private LoggedInUser assignUser(String username) {
        LoggedInUser user = new LoggedInUser(
                java.util.UUID.randomUUID().toString(),
                username);

        return user;
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
