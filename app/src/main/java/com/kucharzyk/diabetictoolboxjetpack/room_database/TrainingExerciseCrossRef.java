package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.Entity;

@Entity(primaryKeys = {"trainingId", "exerciseId"})
public class TrainingExerciseCrossRef {
    private int trainingId;
    private int exerciseId;
    private Double duration;


    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public Double getDuration() {
        return duration;
    }
}
