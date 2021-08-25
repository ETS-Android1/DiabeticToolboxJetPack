package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.Entity;

@Entity(primaryKeys = {"mealId", "productId"})
public class MealProductCrossRef {
    private int mealId;
    private int productId;
    private Double servingSize;


    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setServingSize(Double servingSize) {
        this.servingSize = servingSize;
    }

    public int getMealId() {
        return mealId;
    }

    public int getProductId() {
        return productId;
    }

    public Double getServingSize() {
        return servingSize;
    }
}


