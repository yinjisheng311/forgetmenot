package com.example.g.forgetmenot;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;

public class SpeechPutIsIt extends AppCompatActivity {
    private TextView txtSpeechInput;
    Typeface tf1;
    String sentObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_put_is_it);
        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        tf1 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");
        txtSpeechInput.setTypeface(tf1);

        Intent i = getIntent();
        ArrayList<String>list = i.getStringArrayListExtra("result");
        txtSpeechInput.setText("Is the item '" + list.get(0) + "'?");
        sentObject = list.get(0);

    }




    public void YesItem(View view){
        Intent intent = new Intent(this, YesActivity.class);
        intent.putExtra("object", sentObject);

        startActivity(intent);
    }
    public void NoItem(View view){
        Intent intent = new Intent(this, NoActivity.class);
        startActivity(intent);
    }


}
