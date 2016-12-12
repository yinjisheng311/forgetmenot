package com.example.g.forgetmenot;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    Button takeButton1, putButton1, inventoryButton;
    ImageView whiteshelfHere;
    Typeface tf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        final Animation animZoomOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        takeButton1 = (Button) findViewById(R.id.takeButton);
        putButton1 = (Button) findViewById(R.id.putButton);
        whiteshelfHere = (ImageView)findViewById(R.id.whiteshelfIV);
        inventoryButton = (Button) findViewById(R.id.itemB);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        inventoryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goToList(v);

            }
        });


        tf1 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");

        takeButton1.setTypeface(tf1);
        putButton1.setTypeface(tf1);
        inventoryButton.setTypeface(tf1);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_image was selected
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT)
                        .show();
                break;

            //action with ID action_settings_two was selected
            case R.id.action_logout:
                Toast.makeText(this, "Log Out", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }

        return true;
    }

    public void goToList(View view){
        Intent goToList = new Intent(this, InventoryList.class);
        startActivity(goToList);
    }

    public void PutIntent(View v){
        Intent putIntentHere = new Intent(this, SpeechPutActivity.class);
        startActivity(putIntentHere);
    }
    public void TakeIntent(View v){
        Intent takeIntentHere = new Intent(this, SpeechTakeActivity.class);
        startActivity(takeIntentHere);
    }
}
