package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Meal;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealWithProducts;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealWithProductsDao;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MealWithProductsRepository {

    private final MealWithProductsDao mealWithProductsDao;
    private final MealWithProducts mealWithProducts;
    private final MealWithProducts mealWithProductsFromDate;

    public MealWithProductsRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        mealWithProductsDao = db.mealWithProductsDao();

        Callable<MealWithProducts> mealWithProductsCallable = () -> mealWithProductsDao.getMealWithProducts(1);
        Callable<MealWithProducts> mealWithProductsFromDateCallable = () -> mealWithProductsDao.getMealWithProductsFromDate("16-08-2021");

        Future<MealWithProducts> futureMealWithProducts = AppDatabase.databaseWriteExecutor.submit(mealWithProductsCallable);
        MealWithProducts mwp = new MealWithProducts();
        try {
            //W tym miejscy oczekujemy na drugi wątek co mija się z ideą wielowątkowości
            mwp = futureMealWithProducts.get();
        } catch (InterruptedException | ExecutionException exception) {
            exception.printStackTrace();
        } finally {
            mealWithProducts = mwp;
        }

        Future<MealWithProducts> futureMealWithProductsFromDate = AppDatabase.databaseWriteExecutor.submit(mealWithProductsFromDateCallable);
        MealWithProducts mwpfd = new MealWithProducts();
        try {
            //W tym miejscy oczekujemy na drugi wątek co mija się z ideą wielowątkowości
            mwpfd = futureMealWithProductsFromDate.get();
        } catch (InterruptedException | ExecutionException exception) {
            exception.printStackTrace();
        } finally {
            mealWithProductsFromDate = mwpfd;
        }


    }

    public MealWithProducts getMealWithProducts() {
        return mealWithProducts;
    }

    public MealWithProducts getMealWithProductsFromDate() {
        return mealWithProductsFromDate;
    }

}
