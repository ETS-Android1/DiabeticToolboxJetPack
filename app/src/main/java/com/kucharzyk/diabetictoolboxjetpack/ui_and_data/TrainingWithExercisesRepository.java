package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.TrainingWithExercises;
import com.kucharzyk.diabetictoolboxjetpack.room_database.TrainingWithExercisesDao;

import java.time.LocalDate;
import java.util.List;

public class TrainingWithExercisesRepository {

    private final TrainingWithExercisesDao trainingWithExercisesDao;

    public TrainingWithExercisesRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        trainingWithExercisesDao = db.trainingWithExercisesDao();
    }

    public LiveData<TrainingWithExercises> getTrainingWithExercises(long trainingId) {

        return trainingWithExercisesDao.getTrainingWithExercises(trainingId);
    }

    public LiveData<List<TrainingWithExercises>> getTrainingWithExercisesFromDate(LocalDate localDate) {
        return trainingWithExercisesDao.getTrainingWithExercisesFromDate(localDate);
    }
}
