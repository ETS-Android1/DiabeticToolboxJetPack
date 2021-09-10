package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "Glycemia measurements")
public class Glycemia {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int measurementId;
    //private final String measurementName;
    private final LocalDateTime measurementDateTime;


    public Glycemia(LocalDateTime measurementDateTime) {
        //this.measurementName = measurementName;
        this.measurementDateTime = measurementDateTime;
    }

    public void setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
    }

    public int getMeasurementId() {
        return measurementId;
    }

/*    public String getMeasurementName() {
        return measurementName;
    }*/

    public LocalDateTime getMeasurementDateTime() {
        return measurementDateTime;
    }
}


