package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Meal;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealDao;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MealRepository {

    private final MealDao mealDao;
    private final LiveData<List<LocalDate>> allMealDates;
    private final LiveData<List<Meal>> allMeals;

    public MealRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mealDao = db.mealDao();
        allMealDates = mealDao.getAllMealDates();
        allMeals = mealDao.getAllMeals();
    }

    public long insert(Meal meal) {
        Callable<Long> insertCallable = () -> mealDao.insert(meal);
        Future<Long> futureMealId = AppDatabase.databaseWriteExecutor.submit(insertCallable);
        try {
            //W tym miejscy oczekujemy na drugi wątek co mija się z ideą wielowątkowości
            return futureMealId.get();
        } catch (InterruptedException | ExecutionException exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    public LiveData<List<Meal>> getAllMeals() {
        return allMeals;
    }

    public LiveData<List<LocalDate>> getAllMealDates() {return allMealDates; }
}
