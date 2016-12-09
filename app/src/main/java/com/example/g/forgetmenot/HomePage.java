package com.example.g.forgetmenot;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    Button takeButton1, putButton1;
    Typeface tf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        takeButton1 = (Button) findViewById(R.id.takeButton);
        putButton1 = (Button) findViewById(R.id.putButton);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");

        takeButton1.setTypeface(tf1);
        putButton1.setTypeface(tf1);

    }
    public void PutIntent(View v){
        Intent putIntentHere = new Intent(this, SpeechPutActivity.class);
        startActivity(putIntentHere);
    }
    public void TakeIntent(View v){
        Intent takeIntentHere = new Intent(this, SpeechPutActivity.class);
        startActivity(takeIntentHere);
    }
}
