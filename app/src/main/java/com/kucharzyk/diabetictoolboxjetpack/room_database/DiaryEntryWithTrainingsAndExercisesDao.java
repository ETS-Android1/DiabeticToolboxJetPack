package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface DiaryEntryWithTrainingsAndExercisesDao {

    @Transaction
    @Query("SELECT * FROM diary_entry")
    public LiveData<List<DiaryEntryWithTrainingsAndExercises>> getDiaryEntriesWithTrainingsAndExercises();

    @Transaction
    @Query("SELECT * FROM diary_entry WHERE diaryEntryDate = :date")
    public LiveData<DiaryEntryWithTrainingsAndExercises> getDiaryEntryFromDate(LocalDate date);
}
