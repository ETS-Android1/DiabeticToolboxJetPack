package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Exercise;
import com.kucharzyk.diabetictoolboxjetpack.room_database.ExerciseDao;

import java.util.List;

public class ExerciseRepository {

    private static final String TAG = "ExerciseRepository";
    private final ExerciseDao exerciseDao;
    private final LiveData<List<Exercise>> allExercises;

    public ExerciseRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        exerciseDao = db.exerciseDao();
        allExercises = exerciseDao.getAllExercises();
    }

    public void insert(Exercise exercise) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            exerciseDao.insert(exercise);
        });
    }

    public LiveData<List<Exercise>> getAllExercises() {
        return allExercises;
    }
}
