package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntry;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryDao;

public class DiaryEntryRepository {

    private static final String TAG = "DiaryEntryRepository";
    private final DiaryEntryDao diaryEntryDao;

    public DiaryEntryRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        diaryEntryDao = db.diaryEntryDao();
    }

    public void insert(DiaryEntry diaryEntry) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            diaryEntryDao.insert(diaryEntry);
        });
    }

    public void update(DiaryEntry diaryEntry){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            diaryEntryDao.update(diaryEntry);
        });
    }
}