package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealWithProducts;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealWithProductsDao;

import java.time.LocalDate;
import java.util.List;

public class MealWithProductsRepository {

    private final MealWithProductsDao mealWithProductsDao;

    public MealWithProductsRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        mealWithProductsDao = db.mealWithProductsDao();
    }

    public LiveData<MealWithProducts> getMealWithProducts(long mealID) {

        return mealWithProductsDao.getMealWithProducts(mealID);
    }

    public LiveData<List<MealWithProducts>> getMealWithProductsFromDate(LocalDate localDate) {
        return mealWithProductsDao.getMealWithProductsFromDate(localDate);
    }

}
