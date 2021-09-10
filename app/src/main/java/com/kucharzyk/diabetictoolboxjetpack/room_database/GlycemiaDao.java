package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.time.LocalDateTime;
import java.util.List;

@Dao
public interface GlycemiaDao {

    @Insert
    void insert(Glycemia glycemiaMeasurement);

    @Delete
    void delete(Glycemia glycemiaMeasurement);

    @Update
    void update(Glycemia glycemiaMEasurement);

    @Query("SELECT * FROM `Glycemia measurements`")
    LiveData<List<Glycemia>> getAllMeasurements();

    @Query("SELECT DISTINCT measurementDateTime FROM `glycemia measurements`")
    LiveData<List<LocalDateTime>> getAllMeasurementsDates();
}
