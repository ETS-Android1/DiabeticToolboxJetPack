package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.TrainingExerciseCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.room_database.TrainingExerciseCrossRefDao;

import java.util.List;

public class TrainingExerciseCrossRefRepository {

    private final TrainingExerciseCrossRefDao trainingExerciseCrossRefDao;
    //private final LiveData<List<MealProductCrossRef>> allMealsProductCrossRef;

    public TrainingExerciseCrossRefRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        trainingExerciseCrossRefDao = db.trainingExerciseCrossRefDao();
    }

    public void insert(TrainingExerciseCrossRef trainingExerciseCrossRef) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            trainingExerciseCrossRefDao.insert(trainingExerciseCrossRef);
        });
    }

    public LiveData<TrainingExerciseCrossRef> getByExerciseId(int exerciseId) {
        return trainingExerciseCrossRefDao.getByExerciseId(exerciseId);
    }

    public LiveData<List<TrainingExerciseCrossRef>> getAllTrainingExerciseCrossRef() {
        return trainingExerciseCrossRefDao.getAllCrossRef();
    }
}
