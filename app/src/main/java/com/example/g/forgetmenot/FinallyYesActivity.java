package com.example.g.forgetmenot;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class FinallyYesActivity extends AppCompatActivity {

    Button goHomeBtn;
    Typeface tf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finally_yes);

        goHomeBtn = (Button) findViewById(R.id.goHomeButton2);
        tf1 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");
        goHomeBtn.setTypeface(tf1);

        Intent intent = getIntent();
        String object = intent.getExtras().getString("object");
        System.out.println(object);

        RequestParams item = new RequestParams();
        item.put("item", object.replace(" ", "+"));
        item.put("bin", 0);
        postNewObject(item, object.replace(" ", "+"), 0);

    }
    public void postNewObject(RequestParams item2, String item, int bin) {
        SpeechActivityClient.post("api/item/add?item=" + item + "&bin=" + bin, item2, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject existingObject) {
                try {
                    //JSONObject newObject = existingObject.getJSONObject(0);
                    System.out.println("Works");
                } catch (Exception e) {
                    System.out.println("no");
                }

            }

            public void onRetry(int retryNo) {

            }

            public void onFailure(int x, Header[] y, String z, Throwable l) {
                System.out.println(z);
            }

        });

    }
    public void GoHomeFinallyYes(View view){
        Intent goHomeFinally = new Intent(this, HomePage.class);
        startActivity(goHomeFinally);
    }
}
