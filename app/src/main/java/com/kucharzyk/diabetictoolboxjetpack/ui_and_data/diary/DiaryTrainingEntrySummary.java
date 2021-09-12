package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import com.kucharzyk.diabetictoolboxjetpack.Globals;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Exercise;
import com.kucharzyk.diabetictoolboxjetpack.room_database.TrainingExerciseCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.room_database.TrainingWithExercises;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DiaryTrainingEntrySummary {

    private final List<TrainingWithExercises> trainingWithExercisesList;
    private final List<TrainingExerciseCrossRef> exercisesWithDuration;
    private final LocalDate diaryEntryDate;
    private final User currentUser;

    public DiaryTrainingEntrySummary(List<TrainingWithExercises> trainingWithExercises, List<TrainingExerciseCrossRef> trainingExerciseCrossRefs,
                                     LocalDate entryDate, User user) {
        trainingWithExercisesList = trainingWithExercises;
        exercisesWithDuration = trainingExerciseCrossRefs;
        diaryEntryDate = entryDate;
        currentUser = user;
    }

    public Double getCaloriesBurned() {
        double caloriesBurned = 0;
        for (TrainingWithExercises training:trainingWithExercisesList
        ) {
            for (Exercise exercise:training.getExercises()
            ) {
                Double exerciseDuration = getExerciseDurationFromExerciseId(exercisesWithDuration, exercise);
                caloriesBurned += Globals.calculateCaloriesBurned(exerciseDuration, exercise.getMetabolicEquivalentOfTask(), currentUser.getWeight());
            }
        }
        return caloriesBurned;
    }

    public Double getCarbsExchangerUsed() {
        double carbsExchangerUsed = 0;
        for (TrainingWithExercises training:trainingWithExercisesList
        ) {
            for (Exercise exercise:training.getExercises()
            ) {
                Double exerciseDuration = getExerciseDurationFromExerciseId(exercisesWithDuration, exercise);
                carbsExchangerUsed += getCaloriesBurned() / 4 / 12;
            }
        }
        return carbsExchangerUsed;
    }

    public Double getProteinFatExchangerUsed() {
        double fatProteinExchangerUsed = 0;
        for (TrainingWithExercises training:trainingWithExercisesList
        ) {
            for (Exercise exercise:training.getExercises()
            ) {
                Double exerciseDuration = getExerciseDurationFromExerciseId(exercisesWithDuration, exercise);
                fatProteinExchangerUsed += getCaloriesBurned() / 100;
            }
        }
        return fatProteinExchangerUsed;
    }

    public LocalDate getDiaryEntryDate() {
        return diaryEntryDate;
    }

    private Double getExerciseDurationFromExerciseId(List<TrainingExerciseCrossRef> exercisesWithDuration, Exercise exercise) {
        Optional<TrainingExerciseCrossRef> optionalCrossRef = exercisesWithDuration.stream().
                filter(x -> x.getExerciseId() == exercise.getExerciseId()).findFirst();
        return optionalCrossRef.isPresent() ? optionalCrossRef.get().getDuration(): exercise.getExerciseDuration();
    }
}
