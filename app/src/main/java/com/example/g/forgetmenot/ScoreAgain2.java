package com.example.g.forgetmenot;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.*;
import java.util.regex.Pattern;


import android.app.Activity;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.TextView;


import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
public class ScoreAgain2 extends AppCompatActivity {
    private ArrayList<String> arrays = new ArrayList<>();
    private ArrayList<String> arraysTime = new ArrayList<>();
    Typeface tf1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_again2);
        tf1 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");
        //getting the information
        addTime();
        readGraph();
        JodaTimeAndroid.init(this);
//        TextView one = (TextView) findViewById(R.id.textView2);
//        TextView two = (TextView) findViewById(R.id.textView3);
//        TextView three = (TextView) findViewById(R.id.textView4);
//        TextView four = (TextView) findViewById(R.id.textView5);
//        TextView five = (TextView) findViewById(R.id.textView6);
//        TextView six = (TextView) findViewById(R.id.textView7);
//        TextView seven = (TextView) findViewById(R.id.textView8);
//        TextView eight = (TextView) findViewById(R.id.textView9);
//
//        TextView nine = (TextView) findViewById(R.id.textView10);
//        TextView ten = (TextView) findViewById(R.id.textView11);
//        one.setTypeface(tf1);
//        two.setTypeface(tf1);
//        three.setTypeface(tf1);
//        four.setTypeface(tf1);
//        five.setTypeface(tf1);
//        six.setTypeface(tf1);
//        seven.setTypeface(tf1);
//        eight.setTypeface(tf1);
//        nine.setTypeface(tf1);
//        ten.setTypeface(tf1);
//        String tryy = arrays.get(0);
//        System.out.println(tryy);
    }
    public void addTime() {
        RequestParams requestParams = new RequestParams();
        DateTime now = DateTime.now(DateTimeZone.UTC);
        String pattern = "yyyy-MM-dd+HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);

        String formatted = formatter.print(now);
        DateTime nowTime = DateTime.parse(formatted, formatter);
        System.out.println(formatted + "WTF");
        GraphClient.post("api/ana/add?date="+formatted, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {

            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray existingArray) {
                //ArrayList<String> arrays = new ArrayList<>();
                try{
                    System.out.println("it's posted");
                    /*for (String s: arrays){
                        String pattern = "yyyy-MM-ddHH:mm:ss";
                        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
                        DateTime indiArrayTime = DateTime.parse(s, formatter);
//                        indiArrayTime.get
                        System.out.println(indiArrayTime);
                    }*/


                }catch (Exception e){
                    System.out.println(e.getMessage());
                    System.out.println("not in server");

                }

            }

            //@Override
            /*/public void onSuccess(int statusCode, Header[] headers, JSONObject existingObject) {
                try {

                    //JSONObject newObject = existingObject.getJSONObject("something");
                    //System.out.println(newObject);
                    System.out.println("Works");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("no");
                    //Intent notHere = new Intent(SpeechTakeActivity.this, TakeFailure.class);
                    //startActivity(notHere);
                }

            }/*/

            public void onRetry(int retryNo) {

            }

            public void onFailure(int x, Header[] y, String z, Throwable l) {
                System.out.println("time:" + z);
            }

        });

    }
    public void readGraph() {
        RequestParams requestParams = new RequestParams();
        GraphClient.get("api/ana/get", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray existingArray) {
                //ArrayList<String> arrays = new ArrayList<>();
                int j = 0;
                try{
                    for (int i = 0; i<existingArray.length();i++) {
                        JSONObject a_json_object = existingArray.getJSONObject(i);
                        arrays.add(a_json_object.getString("counter"));
                        arraysTime.add(a_json_object.getString("date"));
                    }

                    System.out.println("the array"+arraysTime.get(0));
                    TextView time = (TextView) findViewById(R.id.textView12);
                    TextView count = (TextView) findViewById(R.id.textView);
                    TextView message = (TextView) findViewById(R.id.message);
//                    TextView one = (TextView) findViewById(R.id.textView);



                    count.setText(arrays.get(0));
                    time.setText(arraysTime.get(0));

                    int counter = Integer.parseInt(arrays.get(0));
                    if ((counter >=0) && (counter <= 5)){
                        message.setText("You can do better!");
                    }
                    else if ((counter >=6) && (counter <= 8)){
                        message.setText("You're getting there! Thanks for using Forgetmenot.");
                    }
                    else{
                        message.setText("Excellent!");
                    }


                }catch (Exception e){
                    System.out.println(e.getMessage());
                    System.out.println("not in server");

                }

            }

            @Override
            /*/public void onSuccess(int statusCode, Header[] headers, JSONObject existingObject) {
                try {

                    //JSONObject newObject = existingObject.getJSONObject("something");
                    //System.out.println(newObject);
                    System.out.println("Works");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("no");
                    //Intent notHere = new Intent(SpeechTakeActivity.this, TakeFailure.class);
                    //startActivity(notHere);
                }

            }/*/

            public void onRetry(int retryNo) {

            }

            public void onFailure(int x, Header[] y, String z, Throwable l) {
                System.out.println(z);
            }

        });

    }


}
