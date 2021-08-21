package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface MealWithProductsDao {

    @Transaction
    @Query("SELECT * FROM meal_table WHERE mealId = :mealId")
    public LiveData<MealWithProducts> getMealWithProducts(long mealId);

    @Transaction
    @Query("SELECT * FROM meal_table WHERE mealDate = :date")
    public LiveData<List<MealWithProducts>> getMealWithProductsFromDate(LocalDate date);

}
