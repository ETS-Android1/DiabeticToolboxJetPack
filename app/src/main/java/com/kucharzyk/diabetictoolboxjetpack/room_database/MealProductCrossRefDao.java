package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface MealProductCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (MealProductCrossRef mealProductCrossRef);

}
