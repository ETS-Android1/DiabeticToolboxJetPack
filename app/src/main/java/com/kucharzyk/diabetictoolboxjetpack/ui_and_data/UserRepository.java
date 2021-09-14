package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;
import com.kucharzyk.diabetictoolboxjetpack.room_database.UserDao;

import java.util.List;

public class UserRepository {

    private static final String TAG = "UserRepository";
    private final UserDao userDao;
    private final LiveData<List<User>> appUsers;

    public UserRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.userDao();
        appUsers = userDao.getUsers();
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

    public void updateUsername(String newUsername) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userDao.updateUsername(newUsername);
        });
    }

    public void updateUserWeight(Double newUserWeight) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userDao.updateUserWeight(newUserWeight);
        });
    }

    public void updateUserPassword(String newUserPassword) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userDao.updateUserPassword(newUserPassword);
        });
    }


    public LiveData<List<User>> getAppUsers() {
        return appUsers;
    }
}
