package com.example.diabetictoolboxjetpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserDatabase userDatabase = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "User database").build();
    }
}