package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class Product {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int mPid;
    private String mProductName;
    private Double mCarbohydrates;
    private Double mFat;
    private Double mProteins;

    public Product(@NonNull String productName, Double carbohydrates, Double fat, Double proteins) {
        this.mProductName = productName;
        this.mCarbohydrates = carbohydrates;
        this.mFat = fat;
        this.mProteins = proteins;
    }

    public void setPid(int mPid) { this.mPid = mPid; }


    public int getPid() { return mPid; }

    public String getProductName() {
        return mProductName;
    }

    public Double getCarbohydrates() {
        return mCarbohydrates;
    }

    public Double getFat() {
        return mFat;
    }

    public Double getProteins() {
        return mProteins;
    }
}
