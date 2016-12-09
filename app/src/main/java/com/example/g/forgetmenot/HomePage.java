package com.example.g.forgetmenot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }
    public void PutIntent(View v){
        Intent putIntentHere = new Intent(this, MainActivity.class);
        startActivity(putIntentHere);
    }
    public void TakeIntent(View v){
        Intent takeIntentHere = new Intent(this, SpeechPutActivity.class);
        startActivity(takeIntentHere);
    }
}
