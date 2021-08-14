package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface MealWithProductsDao {

    @Transaction
    @Query("SELECT * FROM meal_table WHERE mid = :mid")
    public MealWithProducts getMealWithProducts(long mid);

}
