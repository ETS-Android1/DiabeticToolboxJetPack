package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface MealProductCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (MealProductCrossRef mealProductCrossRef);


}
