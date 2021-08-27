package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface MealProductCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (MealProductCrossRef mealProductCrossRef);

    @Transaction
    @Query("SELECT * FROM MealProductCrossRef WHERE productId = :productId")
    public LiveData<MealProductCrossRef> getByProductId(int productId);

    @Transaction
    @Query("SELECT * FROM MealProductCrossRef")
    public LiveData<List<MealProductCrossRef>> getAllCrossRef();

}
