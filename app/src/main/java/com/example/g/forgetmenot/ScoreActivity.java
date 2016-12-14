package com.example.g.forgetmenot;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class ScoreActivity extends AppCompatActivity {
    private ArrayList<Double> tograph ;
    private TextView tv1, tv2, tv3, tv4;
    private Button buttonPrompt;
    Typeface tf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tv1 = (TextView)findViewById(R.id.message);
        tv2 = (TextView)findViewById(R.id.displayscore);
        tv3 = (TextView)findViewById(R.id.scoreMessage);
        tv4 = (TextView)findViewById(R.id.howWell);


        tf1 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");

        tv1.setTypeface(tf1);
        tv2.setTypeface(tf1);
        tv3.setTypeface(tf1);
        tv4.setTypeface(tf1);

        Button b = (Button) findViewById(R.id.button);
        tograph = new ArrayList<>();
        GraphView graph = (GraphView) findViewById(R.id.graph);
        final LineGraphSeries<DataPoint> series = new LineGraphSeries<>(createGraph());
        graph.addSeries(series);
        series.setTitle("Your Progress");
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
        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                series.resetData(createGraph()); //to put the points into the graph
                    tograph.clear(); //reset the points

            }
        });


    }
    private DataPoint[] createGraph(){
        DataPoint[] a = new DataPoint[tograph.size()];
        Calendar calendar = Calendar.getInstance();
        Date d1 = calendar.getTime();
        for (int i=0;i<tograph.size();i++){
            double x = d1.getTime();
            double y = tograph.get(i);
            DataPoint v = new DataPoint(x,y);
            calendar.add(Calendar.DATE, 1);
            a[i] = v;
        }
        return a;


    }


}
