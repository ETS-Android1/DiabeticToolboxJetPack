package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal_table")
public class Meal {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int mid;
    private String mealName;
    private String mealDate;

    public Meal(String mealName, String mealDate) {
        this.mealName = mealName;
        this.mealDate = mealDate;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getMid() {
        return mid;
    }

    public String getMealName() {
        return mealName;
    }

    public String getMealDate() {
        return mealDate;
    }
}
