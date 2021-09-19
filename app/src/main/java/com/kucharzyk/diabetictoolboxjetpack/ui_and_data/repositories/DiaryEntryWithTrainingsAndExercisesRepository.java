package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithTrainingsAndExercises;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithTrainingsAndExercisesDao;

import java.time.LocalDate;
import java.util.List;

public class DiaryEntryWithTrainingsAndExercisesRepository {

    private final DiaryEntryWithTrainingsAndExercisesDao diaryEntryWithTrainingsAndExercisesDao;
    private final LiveData<List<DiaryEntryWithTrainingsAndExercises>> diaryEntriesWithTrainingsAndExercises;
    private LiveData<DiaryEntryWithTrainingsAndExercises> diaryEntryFromDate;

    public DiaryEntryWithTrainingsAndExercisesRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        diaryEntryWithTrainingsAndExercisesDao = db.diaryEntryWithTrainingsAndExercisesDao();
        diaryEntriesWithTrainingsAndExercises = diaryEntryWithTrainingsAndExercisesDao.
                getDiaryEntriesWithTrainingsAndExercises();
    }

    public LiveData<List<DiaryEntryWithTrainingsAndExercises>> getAllDiaryEntries() {
        return diaryEntriesWithTrainingsAndExercises;
    }

    public LiveData<DiaryEntryWithTrainingsAndExercises> getDiaryEntryFromDate(LocalDate date) {
        diaryEntryFromDate = diaryEntryWithTrainingsAndExercisesDao.getDiaryEntryFromDate(date);
        return diaryEntryFromDate;
    }
}
