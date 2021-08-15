package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Meal;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealWithProducts;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealWithProductsDao;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MealWithProductsRepository {

    private final MealWithProductsDao mealWithProductsDao;
    private final LiveData<MealWithProducts> mealWithProducts;
    private final LiveData<MealWithProducts> mealWithProductsFromDate;

    public MealWithProductsRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        mealWithProductsDao = db.mealWithProductsDao();
        mealWithProducts = mealWithProductsDao.getMealWithProducts(1);
        mealWithProductsFromDate = mealWithProductsDao.getMealWithProductsFromDate("15-08-2021");
    }

    public LiveData<MealWithProducts> getMealWithProducts() {
        return mealWithProducts;
    }

    public LiveData<MealWithProducts> getMealWithProductsFromDate() {
        return mealWithProductsFromDate;
    }

}
