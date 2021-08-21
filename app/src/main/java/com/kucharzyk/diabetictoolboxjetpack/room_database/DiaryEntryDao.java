package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

@Dao
public interface DiaryEntryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (DiaryEntry diaryEntry);

    @Update
    void update (DiaryEntry diaryEntry);
}
