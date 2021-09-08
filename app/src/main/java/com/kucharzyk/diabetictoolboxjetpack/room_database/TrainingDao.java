package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface TrainingDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert (Training training);

    @Query("SELECT * FROM Trainings")
    LiveData<List<Training>> getAllTrainings();

    @Query("SELECT DISTINCT trainingDate FROM Trainings")
    LiveData<List<LocalDate>> getAllTrainingsDates();
}
