package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Exercises")
@Keep public class Exercise {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int exerciseId;
    private final String exerciseName;
    private final Double averageCalorieConsumption;
    private final Double defaultExerciseDuration;

    public Exercise(String exerciseName, Double averageCalorieConsumption, Double defaultExerciseDuration) {
        this.exerciseName = exerciseName;
        this.averageCalorieConsumption = averageCalorieConsumption;
        this.defaultExerciseDuration = defaultExerciseDuration;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public Double getAverageCalorieConsumption() {
        return averageCalorieConsumption;
    }

    public Double getDefaultExerciseDuration() {
        return defaultExerciseDuration;
    }
}
