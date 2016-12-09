package com.example.g.forgetmenot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class SpeechPutIsIt extends AppCompatActivity {
    private TextView txtSpeechInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_put_is_it);
        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);

        ArrayList<String> result = (ArrayList<String>) getIntent().getSerializableExtra("result");
        txtSpeechInput.setText(result.get(0));
    }

}
