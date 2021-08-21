package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithMealsAndProducts;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithMealsAndProductsDao;

import java.time.LocalDate;
import java.util.List;

public class DiaryEntryWithMealsAndProductsRepository {

    private final DiaryEntryWithMealsAndProductsDao diaryEntryWithMealsAndProductsDao;
    private final LiveData<List<DiaryEntryWithMealsAndProducts>> diaryEntriesWithMealsAndProducts;
    private LiveData<DiaryEntryWithMealsAndProducts> diaryEntryFromDate;

    public DiaryEntryWithMealsAndProductsRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        diaryEntryWithMealsAndProductsDao = db.diaryEntryWithMealsAndProductsDao();
        diaryEntriesWithMealsAndProducts = diaryEntryWithMealsAndProductsDao.
                getDiaryEntriesWithMealsAndProducts();
    }

    public LiveData<List<DiaryEntryWithMealsAndProducts>> getAllDiaryEntries() {
        return diaryEntriesWithMealsAndProducts;
    }

    public LiveData<DiaryEntryWithMealsAndProducts> getDiaryEntryFromDate(LocalDate date) {
        diaryEntryFromDate = diaryEntryWithMealsAndProductsDao.getDiaryEntryFromDate(date);
        return diaryEntryFromDate;
    }
}
