package com.example.g.forgetmenot;

/**
 * Created by user on 06/12/2016.
 */
import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class SpeechActivityClient {

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
    public static void checkBox(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(BASE_URL+url,params, responseHandler);
    }public static void led(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.post(BASE_URL+url, params, responseHandler);
    }
    public static void checkWeight(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(BASE_URL+url, params, responseHandler);
    }

}
