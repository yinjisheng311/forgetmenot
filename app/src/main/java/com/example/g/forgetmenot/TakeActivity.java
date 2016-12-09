package com.example.g.forgetmenot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class TakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take);

        //components
        final ImageView imgRect = (ImageView)findViewById(R.id.imageView);
        Button btnRect = (Button) findViewById(R.id.button2);
        btnRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set paint
                Paint cyan = new Paint();
                cyan.setColor(Color.CYAN);
                cyan.setStyle(Paint.Style.FILL);

                //create bitmap
                Bitmap bmp = Bitmap.createBitmap(500,500, Bitmap.Config.ARGB_8888);

                //canvas
                Canvas canvas = new Canvas(bmp);
                canvas.drawRect(50,50,430,150,cyan);

                canvas.drawRect(50,200,430,300,cyan);


                canvas.drawRect(50,350,430,450,cyan);

                //output rect
                imgRect.setImageBitmap(bmp);
            }
        });

    }

}