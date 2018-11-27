package com.capitalistlepton.xange.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Network {

    public static final String BASE_URL = "https://xange-api.herokuapp.com";

    private static Network mInstance;
    private static Context mCtx;
    private RequestQueue mRequestQueue;


    public Network(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


    public static synchronized Network getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Network(context);
        }
        return mInstance;
    }
}
