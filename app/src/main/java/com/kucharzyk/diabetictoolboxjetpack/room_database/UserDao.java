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
public interface UserDao {

    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getUsers();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (User user);

    @Update
    void update (User user);

    @Delete
    void delete (User user);

    @Query("DELETE FROM user_table")
    void deleteAll ();

    @Query("UPDATE user_table SET username = :newUsername WHERE userID = 1")
    void updateUsername(String newUsername);

    @Query("UPDATE user_table SET weight = :newUserWeight WHERE userID = 1")
    void updateUserWeight(Double newUserWeight);

    @Query("UPDATE user_table SET passwordHash = :newUserPassword WHERE userID = 1")
    void updateUserPassword(String newUserPassword);

}
