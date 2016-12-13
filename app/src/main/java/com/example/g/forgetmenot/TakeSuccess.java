package com.example.g.forgetmenot;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class TakeSuccess extends AppCompatActivity {
    private ImageView drawer1, drawer2, drawer3;


    private Button takeOutButton;
    String getItem;
    String bins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_success);

        drawer1 = (ImageView)findViewById(R.id.drawerView1);
        drawer2 = (ImageView)findViewById(R.id.drawerView2);
        drawer3 = (ImageView)findViewById(R.id.drawerView3);

        takeOutButton = (Button) findViewById(R.id.takeOutButton);
        takeOutButton.setClickable(false);
        mHandlerTask.run();

        bins = getIntent().getStringExtra("bins");

        //get Location
        //getItem = getIntent().getStringExtra("object");
        //RequestParams where = new RequestParams();
        //where.put("item", getItem.replace(" ", "+"));
        //getLocation(where, getItem.replace(" ", "+"),1);

        System.out.println(bins+"wtf");


        if (bins.equals("0")){
            drawer1.setImageResource(R.drawable.drawer1green);
            drawer2.setImageResource(R.drawable.drawer2red);
            drawer3.setImageResource(R.drawable.drawer3red);
        } else if (bins.equals("1")){
            drawer1.setImageResource(R.drawable.drawer1red);
            drawer2.setImageResource(R.drawable.drawer2green);
            drawer3.setImageResource(R.drawable.drawer3red);
        } else {
            drawer1.setImageResource(R.drawable.drawer1red);
            drawer2.setImageResource(R.drawable.drawer2red);
            drawer3.setImageResource(R.drawable.drawer3green);
        }
        RequestParams led = new RequestParams();
        led.put("status",1);
        onLED(1);
        System.out.println("printsthLED");



    }
    public void onLED(int status){
        RequestParams requestParams = new RequestParams();
        SpeechActivityClient.led("api/led/change?led="+ status, requestParams, new JsonHttpResponseHandler(){
            @Override
            public void onStart() {

            }
            public void onSuccess(int statusCode, Header[] headers, JSONArray existingArray) {
                //ArrayList<String> arrays = new ArrayList<>();
                try{
                    System.out.println("this is changed to on");

                }catch (Exception e){
                    System.out.println(e.getMessage());
                    System.out.println("cannot turn this on");

                }

            }
            public void onRetry(int retryNo) {

            }

            public void onFailure(int x, Header[] y, String z, Throwable l) {
                System.out.println(z);
            }
        });
    }

    private final static int INTERVAL = 1000 * 5; //1 minute
    Handler mHandler = new Handler();

    Runnable mHandlerTask = new Runnable()
    {
        @Override
        public void run() {
            RequestParams compartment = new RequestParams();
            checkBoxDelete(compartment);
            mHandler.postDelayed(mHandlerTask, INTERVAL);
        }
    };

    void startRepeatingTask()
    {
        mHandlerTask.run();
    }

    void stopRepeatingTask()
    {
        mHandler.removeCallbacks(mHandlerTask);
    }

    public void checkIfBoxIsOpened(String input){
        if (input.equals("0")){

            Toast.makeText(getApplicationContext(), "Please open the drawer", Toast.LENGTH_LONG).show();
        } else {
            takeOutButton.setClickable(true);
            Toast.makeText(getApplicationContext(), "Clear to proceed", Toast.LENGTH_LONG).show();
                    stopRepeatingTask();
        }
        // if the box is opened
        // takeOutButton.setClickable(true);
        //else
        // do nothing
        //0 is closed, 1 is open
    }
    public void checkBoxDelete(RequestParams items2){
        SpeechActivityClient.checkBox("api/door/get", items2, new JsonHttpResponseHandler(){
            @Override
            public void onStart(){

            }
            public void onSuccess(int statusCode, Header[] headers, JSONArray existingArray){
                try{
                    JSONObject a_json_object = existingArray.getJSONObject(0);
                    String serverData = a_json_object.getString("status");
                    checkIfBoxIsOpened(serverData);
                    System.out.println(a_json_object.getString("status"));
                    System.out.println(a_json_object.getString("status").getClass());
                }catch (Exception E){
                    System.out.println("whatever");
                }
            }
            public void onRetry(int retryNo) {

            }

            public void onFailure(int x, Header[] y, String z, Throwable l) {
                System.out.println(z);
            }
        });
    }
    public void takeItem(View view){

        getItem = getIntent().getStringExtra("object");
        System.out.println(getItem);
        RequestParams item = new RequestParams();
        item.put("item", getItem.replace(" ", "+")); // need to parse in the string

        deleteObject(item, getItem.replace(" ", "+"), 0);

        RequestParams led = new RequestParams();
        led.put("led",0);
        onLED(0);

        Intent takeOutItemIntent = new Intent(this,TakenOutSuccess.class);
        startActivity(takeOutItemIntent);

    }
    public void deleteObject(RequestParams item2, String item, int bin) {
        SpeechActivityClient.delete("api/item/delete?item=" + item, item2, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {

            }
            public void onSuccess(int statusCode, Header[] headers, JSONArray existingArray) {
                try{
                    JSONObject newObject = existingArray.getJSONObject(0);
                    System.out.println(newObject);


                    //Intent intent = new Intent(TakeSuccess.this, TakenOutSuccess.class);
                    //startActivity(intent);

                }catch (Exception e){
                    System.out.println("Not Deleted");

                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject existingObject) {
                try {
                    //JSONObject newObject = existingObject.getJSONObject(0);
                    System.out.println("Deleted");

                    Intent intent = new Intent(TakeSuccess.this, TakenOutSuccess.class);
                    startActivity(intent);
                } catch (Exception e) {
                    System.out.println("Failed to be deleted");
                }

            }

            public void onRetry(int retryNo) {

            }

            public void onFailure(int x, Header[] y, String z, Throwable l) {
                System.out.println(z);
            }

        });

    }
    @Override
    public void onPause(){
        super.onPause();
        stopRepeatingTask();
    }

    public void getLocation(RequestParams item2, String item, int bin) {
        SpeechActivityClient.get("api/item/check?item=" + item, item2, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {

            }
            public void onSuccess(int statusCode, Header[] headers, JSONArray existingArray) {
                try{
                    JSONObject newObject = existingArray.getJSONObject(0);
                    bins = newObject.getString("bin");
                    System.out.println("this is an array");


                    //Intent intent = new Intent(TakeSuccess.this, TakenOutSuccess.class);
                    //startActivity(intent);

                }catch (Exception e){
                    System.out.
                            println("Not Deleted");

                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject existingObject) {
                try {
                    //JSONObject newObject = existingObject.getJSONObject(0);
                    JSONObject newObject = existingObject.getJSONObject(getIntent().getStringExtra("object"));
                    bins = newObject.getString("bin");
                    System.out.println(newObject.getString("bin"));


                    Intent intent = new Intent(TakeSuccess.this, TakenOutSuccess.class);
                    startActivity(intent);
                } catch (Exception e) {
                    System.out.println("Failed to be read");
                }

            }

            public void onRetry(int retryNo) {

            }

            public void onFailure(int x, Header[] y, String z, Throwable l) {
                System.out.println(z);
            }

        });


    }
}

