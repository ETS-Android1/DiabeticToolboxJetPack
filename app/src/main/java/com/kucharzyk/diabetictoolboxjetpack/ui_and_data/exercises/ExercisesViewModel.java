package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.exercises;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntry;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithMealsAndProducts;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Exercise;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Meal;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealProductCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.DiaryEntryRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.DiaryEntryWithMealsAndProductsRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.ExerciseRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.MealProductCrossRefRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.MealRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.ProductRepository;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExercisesViewModel extends AndroidViewModel {
    private List<Exercise> listOfExercises;
    private MutableLiveData<List<Exercise>> trainingSummary;
    private final LiveData<List<Exercise>> allExercises;
    private final ExerciseRepository exerciseRepository;

    public ExercisesViewModel(@NonNull @NotNull Application application) {
        super(application);

        exerciseRepository = new ExerciseRepository(application);
        allExercises = exerciseRepository.getAllExercises();
    }

    public LiveData<List<Exercise>> getAllExercises() {
        return allExercises;
    }
}