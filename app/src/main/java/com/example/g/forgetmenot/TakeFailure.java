package com.example.g.forgetmenot;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class TakeFailure extends AppCompatActivity {
    private AutoCompleteTextView autoComplete;
    private Button findButton;
    private TextView failureMessageTextView;
    private ArrayAdapter<String> adapter;
    private String[] availableObjects;
    Typeface tf1;
    ArrayList<String> arrays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_failure);

        failureMessageTextView = (TextView)findViewById(R.id.failureMessageTV);
        autoComplete = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        findButton = (Button) findViewById(R.id.findButton);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");

        failureMessageTextView.setTypeface(tf1);
        autoComplete.setTypeface(tf1);
        findButton.setTypeface(tf1);


        availableObjects(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrays));

        //String[] objectList = getResources().getStringArray(R.array.objectList);



    }

    public void availableObjects(final ArrayAdapter adapter) {
        RequestParams requestParams = new RequestParams();
        SpeechActivityClient.get("api/item/fetch", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {

            }
            public void onSuccess(int statusCode, Header[] headers, JSONArray existingArray) {
                //ArrayList<String> arrays = new ArrayList<>();
                try{
                    for (int i = 0; i<existingArray.length();i++) {
                        JSONObject a_json_object = existingArray.getJSONObject(i);
                        arrays.add(a_json_object.getString("item"));
                    }

                    System.out.println(arrays);

                    adapter.add(arrays.toArray());
                    autoComplete = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
                    autoComplete.setAdapter(adapter);
                    autoComplete.setThreshold(1);

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
    public void goToTakeSuccess(View view){
        String input = autoComplete.getText().toString();
        System.out.println(input);

        checkInput(input);
        if (checkInput(input)){
            Intent goToTakeSuccessIntent = new Intent(this, TakeSuccess.class);
            startActivity(goToTakeSuccessIntent);
        }
        else {
            Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkInput(String x){
        for (Object o: arrays.toArray()){
            if (o.equals(x)){
                return true;
            }
        }
        return false;
    }
}