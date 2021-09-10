package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Glycemia measurements")
public class Glycemia {

    @PrimaryKey
    @NonNull
    private int measurementId;

}
