package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "diary_entry", indices = {@Index(value = {"diaryEntryDate"}, unique = true)})
public class DiaryEntry {

    @PrimaryKey(autoGenerate = true)
    private int diaryEntryId;
    private final LocalDate diaryEntryDate;

    public DiaryEntry(LocalDate diaryEntryDate) {
        this.diaryEntryDate = diaryEntryDate;
    }

    public void setDiaryEntryId(int diaryEntryId) {
        this.diaryEntryId = diaryEntryId;
    }

    public int getDiaryEntryId() {
        return diaryEntryId;
    }

    public LocalDate getDiaryEntryDate() {
        return diaryEntryDate;
    }
}
