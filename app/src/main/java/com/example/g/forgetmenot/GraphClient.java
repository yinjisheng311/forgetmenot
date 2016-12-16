package com.example.g.forgetmenot;

/**
 * Created by G on 16/12/16.
 */

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class GraphClient {
    private static final String BASE_URL = "https://forgetme-not.herokuapp.com/";

    private static AsyncHttpClient client = new AsyncHttpClient();
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(BASE_URL+url,params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.addHeader("Content-Type","application/json");
        client.post(BASE_URL+url, params, responseHandler);
    }
}
