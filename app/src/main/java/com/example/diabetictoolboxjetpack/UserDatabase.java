package com.example.diabetictoolboxjetpack;

import androidx.room.RoomDatabase;

public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();


}
