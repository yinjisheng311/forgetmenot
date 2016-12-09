package com.example.g.forgetmenot;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class SpeechPutIsIt extends AppCompatActivity {
    private TextView txtSpeechInput;
    Typeface tf1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_put_is_it);
        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        tf1 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");
        txtSpeechInput.setTypeface(tf1);

        Intent i = getIntent();
        ArrayList<String> list = i.getStringArrayListExtra("result");
        txtSpeechInput.setText("Is the item '" + list.get(0) + "'?");
    }
    public void YesItem(View view){
        Intent intent = new Intent(this, SpeechPutIsIt.class);
        startActivity(intent);
    }
    public void NoItem(View view){
        Intent intent = new Intent(this, SpeechPutIsIt.class);
        startActivity(intent);
    }


}
