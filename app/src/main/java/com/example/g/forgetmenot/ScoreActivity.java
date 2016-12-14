package com.example.g.forgetmenot;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Random;

public class ScoreActivity extends AppCompatActivity {
    private ArrayList<Double> tograph ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                TextView score = (TextView) findViewById(R.id.displayscore);
                TextView message = (TextView) findViewById(R.id.message);
                int result = (int) (100.0*Math.random());
                tograph.add((double) result); //store result
                score.setText(Integer.toString(result));
                if (result>= 0 && result<=50){
                    message.setText("Try harder to remember where you had placed your items!");

                }
                else if(result>50 && result<80){
                    message.setText("You've improved! We hope your score will go up higher as you remember better");
                }
                else{
                    message.setText("Congratulations! Your memory is amazing now.");
                }
            }
        });

    }


}
