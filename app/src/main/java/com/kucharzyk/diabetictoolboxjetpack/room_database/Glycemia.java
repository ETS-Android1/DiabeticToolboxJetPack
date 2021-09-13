package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(tableName = "Glycemia measurements")
public class Glycemia {

    @PrimaryKey(autoGenerate = true)
    private int measurementId;
    private LocalDate diaryEntryDateId;
    private final LocalDateTime measurementDateTime;
    private final int measurementValue;


    public Glycemia(LocalDateTime measurementDateTime, int measurementValue) {
        this.measurementDateTime = measurementDateTime;
        this.measurementValue = measurementValue;
        this.diaryEntryDateId = measurementDateTime.toLocalDate();
    }

    public void setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
    }

    public void setDiaryEntryDateId(LocalDate diaryEntryDateId) {
        this.diaryEntryDateId = diaryEntryDateId;
    }

    public int getMeasurementId() {
        return measurementId;
    }

    public LocalDate getDiaryEntryDateId() {
        return diaryEntryDateId;
    }

    public LocalDateTime getMeasurementDateTime() {
        return measurementDateTime;
    }

    public int getMeasurementValue() {
        return measurementValue;
    }
}


