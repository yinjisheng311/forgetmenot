package com.example.g.forgetmenot;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoActivity extends AppCompatActivity {
    Button correctedButton;
    Typeface tf1;
    EditText correctedItem;
    String sentObject2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no);

        correctedButton = (Button)findViewById(R.id.correctedButtonET);
        correctedItem = (EditText) findViewById(R.id.correctedItemET);



        tf1 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");

        correctedItem.setTypeface(tf1);
        correctedButton.setTypeface(tf1);

    }
    public void FinallyYes(View view){
        sentObject2 = correctedItem.getText().toString();
        System.out.println(sentObject2);
        Intent finallyYes = new Intent(this, FinallyYesActivity.class);
        finallyYes.putExtra("object",sentObject2);
        startActivity(finallyYes);
    }
}
