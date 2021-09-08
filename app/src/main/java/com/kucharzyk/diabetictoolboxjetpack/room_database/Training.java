package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "Trainings")
public class Training {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int trainingId;
    private final String trainingName;
    private final LocalDate trainingDate;

    public Training(String trainingName, LocalDate trainingDate) {
        this.trainingName = trainingName;
        this.trainingDate = trainingDate;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public LocalDate getTrainingDate() {
        return trainingDate;
    }
}
