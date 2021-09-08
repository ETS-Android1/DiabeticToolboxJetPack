package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface TrainingExerciseCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (TrainingExerciseCrossRef trainingExerciseCrossRef);

    @Transaction
    @Query("SELECT * FROM TrainingExerciseCrossRef WHERE exerciseId = :exerciseId")
    public LiveData<TrainingExerciseCrossRef> getByExerciseId(int exerciseId);

    @Transaction
    @Query("SELECT * FROM TrainingExerciseCrossRef")
    public LiveData<List<TrainingExerciseCrossRef>> getAllCrossRef();
}
