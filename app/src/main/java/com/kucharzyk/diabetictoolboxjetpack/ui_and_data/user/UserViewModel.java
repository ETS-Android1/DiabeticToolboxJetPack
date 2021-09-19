package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.User;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.repositories.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private static final String TAG = "UserViewModel";

    private final UserRepository userRepository;
    private LiveData<List<User>> appUsers;

    public UserViewModel(@NonNull Application application){
        super(application);

        userRepository = new UserRepository(application);
        appUsers = userRepository.getAppUsers();
    }

    public LiveData<List<User>> getAppUsers() {
        return appUsers;
    }
    public void insertUser(User user) {userRepository.insert(user); }
    public void updateUsername(String newUsername) {userRepository.updateUsername(newUsername); }
    public void updateUserWeight(Double newUserWeight) {userRepository.updateUserWeight(newUserWeight); }
    public void updateUserPassword(String newUserPassword) {userRepository.updateUserPassword(newUserPassword); }
}