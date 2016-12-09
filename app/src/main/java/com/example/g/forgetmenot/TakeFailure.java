package com.example.g.forgetmenot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class TakeFailure extends AppCompatActivity {
    private AutoCompleteTextView autoComplete;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_failure);
        String[] locations = getResources().getStringArray(R.array.ItemName);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,locations);
        autoComplete = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoComplete.setAdapter(adapter);
        autoComplete.setThreshold(1);
    }
}
