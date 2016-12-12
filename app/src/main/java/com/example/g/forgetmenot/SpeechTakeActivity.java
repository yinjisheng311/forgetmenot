package com.example.g.forgetmenot;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Locale;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;



public class SpeechTakeActivity extends AppCompatActivity {
    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    private ArrayList<String> result;
    private static final String TAG_OBJECT = "item";
    JSONArray user = null;
    public String theItem;
    Typeface tf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take);

        txtSpeechInput = (TextView) findViewById(R.id.takeText);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");

        txtSpeechInput.setTypeface(tf1);

        // hide the action bar

        //getActionBar().hide();

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    }
    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported),Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    System.out.println("justprintingarray" +result.get(0));

                    /*

                    Intent changeItem = new Intent(this, TakeSuccess.class);
                    theItem = result.get(0);
                    changeItem.putExtra("item", theItem);
                    */

                    RequestParams item = new RequestParams();
                    item.put("item", result.get(0).replace(" ", "+"));
                    item.put("bin", 0);
                    fetchObject(item, result.get(0).replace(" ", "+"), 0);
                }
                break;
            }
        }
    }


    /**@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
    } **/

    public void fetchObject(RequestParams item2, String item, int bin) {
        SpeechActivityClient.get("api/item/check?item=" + item, item2, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {

            }
            public void onSuccess(int statusCode, Header[] headers, JSONArray existingArray) {
                try{
                    JSONObject newObject = existingArray.getJSONObject(0);
                    System.out.println(newObject);

                    Intent intent = new Intent(SpeechTakeActivity.this, TakeSuccess.class);
                    startActivity(intent);

                }catch (Exception e){
                    System.out.println("not in server");
                    Intent notHere = new Intent(SpeechTakeActivity.this, TakeFailure.class);
                    startActivity(notHere);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject existingObject) {
                try {

                    JSONObject newObject = existingObject.getJSONObject(result.get(0));
                    System.out.println(newObject);
                    System.out.println("Works");
                } catch (Exception e) {
                    System.out.println("no");
                    Intent notHere = new Intent(SpeechTakeActivity.this, TakeFailure.class);
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