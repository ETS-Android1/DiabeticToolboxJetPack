package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

public class FoodProduct {
    private String mMealName;
    private Double mCarbohydrates;
    private Double mFat;
    private Double mProteins;
    private Double mWeight = 100.0;

    public FoodProduct(String mealName, Double carbohydrates, Double fat, Double proteins){
        mMealName = mealName;
        mCarbohydrates = carbohydrates;
        mFat = fat;
        mProteins = proteins;
    }

    public void addMeal(String mealName){
        mMealName = mealName;
    }


    public String getMealName() {
        return mMealName;
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
