package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Training;
import com.kucharzyk.diabetictoolboxjetpack.room_database.TrainingDao;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TrainingRepository {

    private final TrainingDao trainingDao;
    private final LiveData<List<LocalDate>> allTrainingDates;
    private final LiveData<List<Training>> allTrainings;

    public TrainingRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        trainingDao = db.trainingDao();
        allTrainingDates = trainingDao.getAllTrainingsDates();
        allTrainings = trainingDao.getAllTrainings();
    }

    public long insert(Training training) {
        Callable<Long> insertCallable = () -> trainingDao.insert(training);
        Future<Long> futureTrainingId = AppDatabase.databaseWriteExecutor.submit(insertCallable);
        try {
            //W tym miejscy oczekujemy na drugi wątek co mija się z ideą wielowątkowości
            return futureTrainingId.get();
        } catch (InterruptedException | ExecutionException exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    public LiveData<List<Training>> getAllTrainings() {
        return allTrainings;
    }

    public LiveData<List<LocalDate>> getAllTrainingDates() {return allTrainingDates; }
}
