package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface MealDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert (Meal meal);

    @Query("SELECT * FROM meal_table")
    LiveData<List<Meal>> getAllMeals();

    @Query("SELECT DISTINCT mealDate FROM meal_table")
    LiveData<List<LocalDate>> getAllMealDates();
}
