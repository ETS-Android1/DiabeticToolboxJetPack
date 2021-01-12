package com.example.diabetictoolboxjetpack;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserProfileViewModel extends AndroidViewModel{

    private UserRepository userRepository;
    private final LiveData<List<User>> allUsers;

    public UserProfileViewModel(Application application){
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    public void insert(User user){
        userRepository.insert(user);
    }

    //private String userID = TODO();
    //private User user = TODO();
}
