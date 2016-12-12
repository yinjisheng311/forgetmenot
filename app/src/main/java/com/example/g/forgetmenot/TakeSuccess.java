package com.example.g.forgetmenot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class TakeSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_success);
    }
    /*

    public void takeItem(View view){

        RequestParams item = new RequestParams();
        String getItem = getIntent().getStringExtra("item");
        item.put("item", getItem.replace(" ", "+")); // need to parse in the string
        System.out.println(getItem);
        deleteObject(item, getItem.replace(" ", "+"), 0);

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


                    Intent intent = new Intent(TakeSuccess.this, TakenOutSuccess.class);
                    startActivity(intent);

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
    */
    public void takeOutItem(View view){
        Intent takeOutItemIntent = new Intent(this,TakenOutSuccess.class);
        startActivity(takeOutItemIntent);
    }
}