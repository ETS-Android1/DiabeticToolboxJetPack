package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;
import com.kucharzyk.diabetictoolboxjetpack.room_database.UserDao;

public class UserRepository {

    private static final String TAG = "UserRepository";
    private final UserDao userDao;
    private final LiveData<User> currentUser;

    public UserRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.userDao();
        currentUser = userDao.getUser();
    }

    public void insert(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }

    public void update(User user){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userDao.update(user);
        });
    }

    public void delete(User user){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userDao.delete(user);
        });
    }


    public LiveData<User> getCurrentUser() {
        return currentUser;
    }
}
