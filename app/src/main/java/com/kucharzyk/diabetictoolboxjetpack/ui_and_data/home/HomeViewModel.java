package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.User;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.repositories.UserRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private final UserRepository userRepository;
    private final LiveData<List<User>> allApplicationUsers;
    private MutableLiveData<String> mText;

    public HomeViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository(application);

        allApplicationUsers = userRepository.getAppUsers();

        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<List<User>> getAllApplicationUsers() { return allApplicationUsers; }

    public LiveData<String> getText() {
        return mText;
    }
}
