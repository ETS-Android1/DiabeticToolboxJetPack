package com.kucharzyk.diabetictoolboxjetpack.logging_and_registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.MainActivity;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.user.UserViewModel;


public class LoggingActivity extends AppCompatActivity {
    private static final String TAG = "LoggingActivity";

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

    }



    /** Called when the user taps the Login button */
    public void login(View view) {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    /** Called when the user taps the Register button */
    public void register(View view){
        //TODO
    }


}