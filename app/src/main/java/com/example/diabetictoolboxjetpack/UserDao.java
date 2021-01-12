package com.example.diabetictoolboxjetpack;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface UserDao {

    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (User user);

    @Update
    void update (User user);

    @Delete
    void delete (User user);

}
