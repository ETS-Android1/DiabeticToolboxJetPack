package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.glycemia;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntry;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Glycemia;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.DiaryEntryRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.GlycemiaRepository;

import java.time.LocalDate;

public class GlycemiaViewModel extends AndroidViewModel {

    private final GlycemiaRepository glycemiaRepository;
    private final DiaryEntryRepository diaryEntryRepository;

    public GlycemiaViewModel(@NonNull Application application) {
        super(application);

        glycemiaRepository = new GlycemiaRepository(application);
        diaryEntryRepository = new DiaryEntryRepository(application);
    }

    public void insertMeasurement(Glycemia glycemiaMeasurement){
        glycemiaRepository.insert(glycemiaMeasurement);
    }

    public void deleteMeasurement(Glycemia glycemiaMeasurement) {
        glycemiaRepository.delete(glycemiaMeasurement);
    }

    public void updateMeasurement(Glycemia glycemiaMeasurement) {
        glycemiaRepository.update(glycemiaMeasurement);
    }

    public void insertDiaryEntry(DiaryEntry diaryEntry) {
        diaryEntryRepository.insert(diaryEntry);
    }

    public LiveData<DiaryEntry> getDiaryEntryFromDate(LocalDate date) {
        return diaryEntryRepository.getDiaryEntryFromDate(date);
    }
}