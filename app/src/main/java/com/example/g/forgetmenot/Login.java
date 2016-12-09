package com.example.g.forgetmenot;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    EditText usernameEditText,passwordEditText;
    TextView titleTextView;
    Typeface tf1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        titleTextView = (TextView) findViewById(R.id.titleTextView);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/JosefinSans-Regular.ttf");
        usernameEditText.setTypeface(tf1);
        passwordEditText.setTypeface(tf1);
        titleTextView.setTypeface(tf1);
    }
    public void LoginIntent(View v){
        Intent loginIntentHere = new Intent(this, HomePage.class);
        startActivity(loginIntentHere);
    }
}
