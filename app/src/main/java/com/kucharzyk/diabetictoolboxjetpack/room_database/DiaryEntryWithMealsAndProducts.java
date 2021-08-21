package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class DiaryEntryWithMealsAndProducts {

    @Embedded public DiaryEntry diaryEntry;
    @Relation(
            entity = Meal.class,
            parentColumn = "diaryEntryDate",
            entityColumn = "mealDate"
    )
    public List<MealWithProducts> meals;
}
