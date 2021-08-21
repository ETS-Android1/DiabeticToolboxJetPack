package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class MealWithProducts {
    @Embedded private Meal meal;
    @Relation(
            parentColumn = "mealId",
            entityColumn = "productId",
            associateBy = @Junction(MealProductCrossRef.class)
    )
    private List<Product> products;

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Meal getMeal() {
        return meal;
    }

    public List<Product> getProducts() {
        return products;
    }
}
