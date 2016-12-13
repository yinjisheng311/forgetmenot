package com.example.g.forgetmenot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TakenOutSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_out_success);
    }

    public void GoHomeFinallyYes(View view){
        Intent goHomeIntent = new Intent(this,HomePage.class);
        startActivity(goHomeIntent);
    }
}
