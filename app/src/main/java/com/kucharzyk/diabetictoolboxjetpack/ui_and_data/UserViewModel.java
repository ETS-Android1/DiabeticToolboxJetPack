package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.User;

import java.util.List;

/*
    A ViewModel object provides the data for a specific UI component, such as a fragment or activity,
    and contains data-handling business logic to communicate with the model. For example,
    the ViewModel can call other components to load the data, and it can forward user requests to modify the data.
    The ViewModel doesn't know about UI components, so it isn't affected by configuration changes,
    such as recreating an activity when rotating the device.
 */

public class UserViewModel extends AndroidViewModel{

    private UserRepository userRepository;
    private final LiveData<List<User>> allUsers;

    public UserViewModel(Application application){
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

}
