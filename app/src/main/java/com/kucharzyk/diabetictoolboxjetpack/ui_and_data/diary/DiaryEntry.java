package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.MealWithProducts;

import java.time.LocalDate;
import java.util.List;

public class DiaryEntry {

    private DiaryEntryViewModel diaryEntryViewModel;

    private LocalDate diaryEntryDate;
    private LiveData<List<MealWithProducts>> mealWithProductsFromDate;
}
