package com.example.g.forgetmenot;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by G on 12/12/16.
 */

public class DrawerCheckClass {
    private static final String BASE_URL = "https://forgetme-not.herokuapp.com/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(BASE_URL+url,params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.addHeader("Content-Type","application/json");
        client.post(BASE_URL+url, params, responseHandler);
    }
    public static void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.post(BASE_URL+url, params, responseHandler);
    }

}
