package com.example.chores;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;

public class AppController {

    public static final String TAG = AppController.class.getName();

    private RequestQueue requestQueue;
    private static AppController mInstance;
    private static Context ctx;

    public AppController(Context context) {
        ctx = context;
    }

    public static synchronized AppController getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AppController(context);
        }

        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx);
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public void canclePendingReqest(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }
}
