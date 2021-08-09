package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

public class FoodProduct extends Product {
    private String mProductName;
    private Double mCarbohydrates;
    private Double mFat;
    private Double mProteins;
    private Double mServingSize = 100.0;

    public FoodProduct(String productName, Double carbohydrates, Double fat, Double proteins,
                       Double servingSize){
        super(productName, carbohydrates, fat, proteins, servingSize);
        mProductName = productName;
        mCarbohydrates = carbohydrates;
        mFat = fat;
        mProteins = proteins;
        mServingSize = servingSize;
    }

    public void addMeal(String mealName){
        mProductName = mealName;
    }


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

    public Double getServingSize() {
        return mServingSize;
    }
}
