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

    public String bins;

    public String input;

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
        input = autoComplete.getText().toString();
        System.out.println(input);

        if (checkInput(input)){
            RequestParams item = new RequestParams();
            item.put("item",input);
            fetchObject(item,input.replace(" ","+"),0);
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


    public void fetchObject(RequestParams item2, String item, int bin) {
        SpeechActivityClient.get("api/item/check?item=" + item, item2, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {

            }
            public void onSuccess(int statusCode, Header[] headers, JSONArray existingArray) {
                try{
                    JSONObject newObject = existingArray.getJSONObject(0);
                    bins = newObject.getString("bin");
                    System.out.println(newObject);

                    Intent intent = new Intent(TakeFailure.this, TakeSuccess.class);
                    //System.out.println(input);
                    intent.putExtra("object",input);
                    intent.putExtra("bins", bins);
                    startActivity(intent);

                }catch (Exception e){
                    System.out.println("not in server");
                    Intent notHere = new Intent(TakeFailure.this, TakeFailure.class);
                    startActivity(notHere);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject existingObject) {
                try {

                    JSONObject newObject = existingObject.getJSONObject(input);
                    bins = newObject.getString("bin");
                    System.out.println("Works");

                    Intent intent = new Intent(TakeFailure.this, TakeSuccess.class);
                    //System.out.println(input);
                    intent.putExtra("object",input);
                    intent.putExtra("bins", bins);
                    startActivity(intent);
                } catch (Exception e) {
                    System.out.println("no" + input);
                    Intent notHere = new Intent(TakeFailure.this, TakeFailure.class);
                    startActivity(notHere);
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

