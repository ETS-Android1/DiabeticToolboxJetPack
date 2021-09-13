package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.time.LocalDate;

@Dao
public interface DiaryEntryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (DiaryEntry diaryEntry);

    @Update
    void update (DiaryEntry diaryEntry);

    @Query("SELECT * FROM diary_entry WHERE diaryEntryDate = :date")
    public LiveData<DiaryEntry> getDiaryEntryFromDate(LocalDate date);
}
