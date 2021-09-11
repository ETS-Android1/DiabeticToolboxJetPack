package com.kucharzyk.diabetictoolboxjetpack.login_and_registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app_database")
                .build();
    }



    /** Called when the user taps the Send button */
    public void changeActivity(View view) {
        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivity(myIntent);
    }

}