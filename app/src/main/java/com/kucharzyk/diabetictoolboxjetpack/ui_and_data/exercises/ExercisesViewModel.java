package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.exercises;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.Exercise;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.ExerciseRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.UserRepository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExercisesViewModel extends AndroidViewModel {
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;

    private List<Exercise> listOfExercises;
    private MutableLiveData<List<Exercise>> trainingSummary;
    private final LiveData<List<Exercise>> allExercises;
    private final LiveData<User> currentUser;


    public ExercisesViewModel(@NonNull @NotNull Application application) {
        super(application);

        exerciseRepository = new ExerciseRepository(application);
        userRepository = new UserRepository(application);

        allExercises = exerciseRepository.getAllExercises();
        currentUser = userRepository.getCurrentUser();
    }

    public LiveData<List<Exercise>> getAllExercises() {
        return allExercises;
    }

    public LiveData<User> getCurrentUser() {return  currentUser; }

    public MutableLiveData<List<Exercise>> getTrainingSummary(){
        if (trainingSummary == null) {
            trainingSummary = new MutableLiveData<>();
        }
        return trainingSummary;
    }

    public List<Exercise> getListOfExercises() {
        if (listOfExercises == null) {
            listOfExercises = new ArrayList<>();
        }
        return listOfExercises;
    }
}