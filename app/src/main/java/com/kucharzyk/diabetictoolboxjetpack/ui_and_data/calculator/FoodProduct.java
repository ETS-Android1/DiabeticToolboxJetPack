package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

public class FoodProduct {
    private String mProductName;
    private Double mCarbohydrates;
    private Double mFat;
    private Double mProteins;
    private Double mWeight = 100.0;

    public FoodProduct(String productName, Double carbohydrates, Double fat, Double proteins){
        mProductName = productName;
        mCarbohydrates = carbohydrates;
        mFat = fat;
        mProteins = proteins;
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

    public Double getWeight() {
        return mWeight;
    }
}
