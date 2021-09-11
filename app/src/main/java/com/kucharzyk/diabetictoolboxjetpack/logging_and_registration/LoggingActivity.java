package com.kucharzyk.diabetictoolboxjetpack.logging_and_registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.MainActivity;


public class LoggingActivity extends AppCompatActivity {
    private static final String TAG = "LoggingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);
    }

    /** Called when the user taps the Send button */
    public void changeActivity(View view) {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }


}