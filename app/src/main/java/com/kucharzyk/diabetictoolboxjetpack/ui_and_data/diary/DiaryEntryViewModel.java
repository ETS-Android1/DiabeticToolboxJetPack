package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithGlycemia;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithMealsAndProducts;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithTrainingsAndExercises;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealProductCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.room_database.TrainingExerciseCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.DiaryEntryWithGlycemiaRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.DiaryEntryWithMealsAndProductsRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.DiaryEntryWithTrainingsAndExercisesRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.MealProductCrossRefRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.TrainingExerciseCrossRefRepository;

import java.util.List;

public class DiaryEntryViewModel extends AndroidViewModel {
    public static final String TAG = "DiaryEntryViewModel";


    private final MealProductCrossRefRepository mealProductCrossRefRepository;
    private final TrainingExerciseCrossRefRepository trainingExerciseCrossRefRepository;
    private final LiveData<List<DiaryEntryWithMealsAndProducts>> allDiaryMealEntries;
    private final LiveData<List<DiaryEntryWithTrainingsAndExercises>> allDiaryTrainingEntries;
    private final LiveData<List<DiaryEntryWithGlycemia>> allDiaryMeasurementEntries;


    public DiaryEntryViewModel(@NonNull Application application) {
        super(application);

        mealProductCrossRefRepository = new MealProductCrossRefRepository(application);
        trainingExerciseCrossRefRepository = new TrainingExerciseCrossRefRepository(application);
        DiaryEntryWithGlycemiaRepository diaryEntryWithGlycemiaRepository = new DiaryEntryWithGlycemiaRepository(application);
        DiaryEntryWithMealsAndProductsRepository diaryEntryWithMealsAndProductsRepository = new DiaryEntryWithMealsAndProductsRepository(application);
        DiaryEntryWithTrainingsAndExercisesRepository diaryEntryWithTrainingsAndExercisesRepository = new DiaryEntryWithTrainingsAndExercisesRepository(application);

        allDiaryMealEntries = diaryEntryWithMealsAndProductsRepository.getAllDiaryEntries();
        allDiaryTrainingEntries = diaryEntryWithTrainingsAndExercisesRepository.getAllDiaryEntries();
        allDiaryMeasurementEntries = diaryEntryWithGlycemiaRepository.getDiaryEntryWithMeasurements();

    }

    public LiveData<List<MealProductCrossRef>> getAllMealCrossRefs() {
        return mealProductCrossRefRepository.getAllMealProductCrossRef();
    }

    public LiveData<List<TrainingExerciseCrossRef>> getAllTrainingCrossRefs() {
        return trainingExerciseCrossRefRepository.getAllTrainingExerciseCrossRef();
    }

    public LiveData<List<DiaryEntryWithMealsAndProducts>> getAllDiaryMealEntries() { return allDiaryMealEntries; }

    public LiveData<List<DiaryEntryWithTrainingsAndExercises>> getAllDiaryTrainingEntries() { return allDiaryTrainingEntries; }

    public LiveData<List<DiaryEntryWithGlycemia>> getAllDiaryMeasurementEntries() {return allDiaryMeasurementEntries; }


}
