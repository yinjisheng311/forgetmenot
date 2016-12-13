package com.example.g.forgetmenot;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class HomePage extends AppCompatActivity {

    Button takeButton1, putButton1, inventoryButton;
    ImageView whiteshelfHere;
    Typeface tf1;
    String valueOfWeight;

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

        /*
        inventoryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goToList(v);

            }
        });
        */


        tf1 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");

        takeButton1.setTypeface(tf1);
        putButton1.setTypeface(tf1);
        inventoryButton.setTypeface(tf1);

        //ViewDialog alert = new ViewDialog();
        //alert.showDialog(HomePage.class, "Error de conexión al servidor");

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
    /*
    public class ViewDialog {

        public void showDialog(Activity activity, String msg){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.nice_dialog);

            TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
            text.setText(msg);

            Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        }
    }
    */





    public class ViewDialog {

        public void showDialog(Activity activity, String msg){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.nice_dialog);

            TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
            tf1 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");
            text.setTypeface(tf1);



            text.setText(msg);

            Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        }



        }
    public class ShowAddPromptViewDialog {

        public void showDialog(Activity activity, String msg) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.nice_dialog);

            TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
            tf1 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");
            text.setTypeface(tf1);


            text.setText(msg);

            Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    System.out.println("that");
                    PutIntent(v);
                }
            });

            Button dialogButton2 = (Button) dialog.findViewById(R.id.btn_dialog2);
            dialogButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });

            dialog.show();

        }

    }
    public class ShowRemovePromptViewDialog {

        public void showDialog(Activity activity, String msg) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.nice_dialog);

            TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
            tf1 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");
            text.setTypeface(tf1);


            text.setText(msg);

            Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    System.out.println("this");
                    TakeIntent(v);
                }
            });
            Button dialogButton2 = (Button) dialog.findViewById(R.id.btn_dialog2);
            dialogButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        }

    }
    public void checkWeightValue(RequestParams item2, int bin){
        SpeechActivityClient.checkWeight("api/system/active", item2, new JsonHttpResponseHandler(){
            @Override
            public void onStart(){

            }
            public void onSuccess(int statusCode, Header[] headers, JSONArray existingArray){
                try{
                    JSONObject newObject = existingArray.getJSONObject(0);
                    String valueOfWeight= newObject.getString("change");
                    String whatItem = newObject.getString("item");
                    System.out.println("JSON " +valueOfWeight);
                    if (valueOfWeight.equals("-1")){ //weight -1 && item is there -> item is taken out and know which one is taken out, ask them to confirm
                        ShowRemovePromptViewDialog alert = new ShowRemovePromptViewDialog();
                        alert.showDialog(HomePage.this, "You took out something, what is it?");
                    }else if (valueOfWeight.equals("1")){
                        ShowAddPromptViewDialog addPromptAlert = new ShowAddPromptViewDialog();
                        addPromptAlert.showDialog(HomePage.this, "You added something, please tell us what it is");
                    }else{
                        System.out.println("nothing happened");
                    }
                } catch (Exception e) {
                    System.out.println("cant detect weight change");
                }
            }
            public void onSuccess(int statusCode, Header[] headers, JSONObject existingObject){
                try{
                    System.out.println("shouldnt be here");
                }catch (Exception e){
                    System.out.println("nope cannot at all");
                }
            }
            public void onRetry(int retryNo){

            }
            public void onFailure(int x, Header[] y, String z, Throwable l){
                System.out.println(z);
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();

        RequestParams weight = new RequestParams();
        weight.put("change",0);
        checkWeightValue(weight,0);

        //should read the server and update the 'valueOfWeight' by now
        /*
        if (valueOfWeight.equals("-1")){ //weight -1 && item is there -> item is taken out and know which one is taken out, ask them to confirm
            ViewDialog alert = new ViewDialog();
            alert.showDialog(this, "You took out something, is it ...?");
        }else if (valueOfWeight.equals("1")){
            ShowAddPromptViewDialog addPromptAlert = new ShowAddPromptViewDialog();
            addPromptAlert.showDialog(this, "You added something, please tell us what it is");
        }else{
            System.out.println("nothing happened");
        }
        */

        //weight +1 &&item is none -> new item is added, prompt user to enter item name
        //ShowAddPromptViewDialog addPromptAlert = new ShowAddPromptViewDialog();
        //addPromptAlert.showDialog(this, "You added something, please tell us what it is");


        //weight -1 && item is unidentified -> item is taken out and we dont know what it is, prompt user to enter item name
        //ShowRemovePromptViewDialog removePromptAlert = new ShowRemovePromptViewDialog();
        //removePromptAlert.showDialog(this, "You took out something, please tell us what it is");

        // else
        //nothing happens
    }



}
