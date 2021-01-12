package com.example.diabetictoolboxjetpack;


import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    UserRepository(Application application){
        UserDatabase userDatabase = UserDatabase.getDatabase(application);
        userDao = userDatabase.userDao();
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
        UserDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }


}
