package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Glycemia;
import com.kucharzyk.diabetictoolboxjetpack.room_database.GlycemiaDao;

import java.time.LocalDateTime;
import java.util.List;

public class GlycemiaRepository {

    private final GlycemiaDao glycemiaDao;
    private final LiveData<List<Glycemia>> allMeasurements;
    private final LiveData<List<LocalDateTime>> allMeasurementsDates;

    public GlycemiaRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        glycemiaDao = db.glycemiaDao();
        allMeasurements = glycemiaDao.getAllMeasurements();
        allMeasurementsDates = glycemiaDao.getAllMeasurementsDates();
    }

    public void insert(Glycemia glycemiaMeasurement) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            glycemiaDao.insert(glycemiaMeasurement);
        });
    }

    public void update(Glycemia glycemiaMeasurement){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            glycemiaDao.update(glycemiaMeasurement);
        });
    }

    public void delete(Glycemia glycemiaMeasurement){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            glycemiaDao.delete(glycemiaMeasurement);
        });
    }

    public LiveData<List<Glycemia>> getAllMeasurements() {return allMeasurements;}

    public LiveData<List<LocalDateTime>> getAllMeasurementsDates() {return allMeasurementsDates;}
}
