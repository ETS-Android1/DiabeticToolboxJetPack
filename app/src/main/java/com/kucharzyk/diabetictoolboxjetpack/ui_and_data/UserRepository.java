package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;


import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;
import com.kucharzyk.diabetictoolboxjetpack.room_database.UserDao;

import java.util.List;

class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    UserRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        userDao = appDatabase.userDao();
        allUsers = userDao.getAllUsers();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }


}
