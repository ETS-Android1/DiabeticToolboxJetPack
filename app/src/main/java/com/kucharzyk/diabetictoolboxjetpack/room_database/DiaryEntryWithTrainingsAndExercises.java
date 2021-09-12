package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class DiaryEntryWithTrainingsAndExercises {

    @Embedded public DiaryEntry diaryEntry;
    @Relation(
            entity = Training.class,
            parentColumn = "diaryEntryDate",
            entityColumn = "trainingDate"
    )
    public List<TrainingWithExercises> trainings;
}
