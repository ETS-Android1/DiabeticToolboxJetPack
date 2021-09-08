package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class TrainingWithExercises {
    @Embedded
    private Training training;
    @Relation(
            parentColumn = "trainingId",
            entityColumn = "exerciseId",
            associateBy = @Junction(TrainingExerciseCrossRef.class)
    )
    private List<Exercise> exercises;

    public void setTraining(Training training) {
        this.training = training;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Training getTraining() {
        return training;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
