package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class Product {

    @PrimaryKey(autoGenerate = true)
    private Integer mPid;
    private String mProductName;
    private Double mCarbohydrates;
    private Double mFat;
    private Double mProteins;

    public Product(String productName, Double carbohydrates, Double fat, Double proteins) {
        this.mProductName = productName;
        this.mCarbohydrates = carbohydrates;
        this.mFat = fat;
        this.mProteins = proteins;
    }

    public void setPid(Integer mPid) { this.mPid = mPid; }

    public void setName(String name) {
        this.mProductName = name;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.mCarbohydrates = carbohydrates;
    }

    public void setFat(Double fat) {
        this.mFat = fat;
    }

    public void setProteins(Double proteins) {
        this.mProteins = proteins;
    }

    public Integer getPid() { return mPid; }

    public String getName() {
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
