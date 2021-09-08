package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface TrainingWithExercisesDao {

    @Transaction
    @Query("SELECT * FROM Trainings WHERE trainingId = :trainingId")
    public LiveData<TrainingWithExercises> getTrainingWithExercises(long trainingId);

    @Transaction
    @Query("SELECT * FROM Trainings WHERE trainingDate = :date")
    public LiveData<List<TrainingWithExercises>> getTrainingWithExercisesFromDate(LocalDate date);
}
