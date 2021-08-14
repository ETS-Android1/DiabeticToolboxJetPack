package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Meal;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealDao;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealProductCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealProductCrossRefDao;

import java.util.List;

public class MealProductCrossRefRepository {
    private final MealProductCrossRefDao mealProductCrossRefDao;
    //private final LiveData<List<MealProductCrossRef>> allMealsProductCrossRef;

    public MealProductCrossRefRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mealProductCrossRefDao = db.mealProductCrossRefDao();
        //allMealsProductCrossRef = mealProductCrossRefDao.getAllMeals();
    }

    public void insert(MealProductCrossRef mealProductCrossRef) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mealProductCrossRefDao.insert(mealProductCrossRef);
        });
    }

/*    public LiveData<List<Meal>> getAllMeals() {
        return allMeals;
    }*/
}
