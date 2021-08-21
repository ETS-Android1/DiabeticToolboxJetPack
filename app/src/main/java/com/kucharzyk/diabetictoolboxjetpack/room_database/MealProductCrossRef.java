package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.Entity;

@Entity(primaryKeys = {"mealId", "productId"})
public class MealProductCrossRef {
    private int mealId;
    private int productId;

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMealId() {
        return mealId;
    }

    public int getProductId() {
        return productId;
    }
}


