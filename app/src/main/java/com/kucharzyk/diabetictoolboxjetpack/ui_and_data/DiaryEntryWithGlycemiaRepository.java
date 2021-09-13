package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithGlycemia;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithGlycemiaDao;

import java.util.List;

public class DiaryEntryWithGlycemiaRepository {

    private final DiaryEntryWithGlycemiaDao diaryEntryWithGlycemiaDao;

    public DiaryEntryWithGlycemiaRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        diaryEntryWithGlycemiaDao = db.diaryEntryWithGlycemiaDao();
    }

    public LiveData<List<DiaryEntryWithGlycemia>> getDiaryEntryWithMeasurements() {

        return diaryEntryWithGlycemiaDao.getDiaryEntryWithGlycemia();
    }
}
