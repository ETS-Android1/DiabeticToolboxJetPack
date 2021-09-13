package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface DiaryEntryWithGlycemiaDao {

    @Transaction
    @Query("SELECT * FROM diary_entry")
    public LiveData<List<DiaryEntryWithGlycemia>> getDiaryEntryWithGlycemia();

}
