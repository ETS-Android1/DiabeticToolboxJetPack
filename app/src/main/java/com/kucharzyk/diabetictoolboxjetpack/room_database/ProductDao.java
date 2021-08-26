package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (Product product);

    @Update
    void update (Product product);

    @Delete
    void delete (Product product);

    @Query("DELETE FROM Products")
    void deleteAllProducts ();

    @Query("SELECT * FROM Products")
    LiveData<List<Product>> getAllProducts();

}
