package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class DiaryEntryWithGlycemia {

    @Embedded
    private DiaryEntry diaryEntry;
    @Relation(
            parentColumn = "diaryEntryDate",
            entityColumn = "diaryEntryDateId"
    )
    private List<Glycemia> glycemiaMeasurements;

    public void setDiaryEntry(DiaryEntry diaryEntry) {
        this.diaryEntry = diaryEntry;
    }

    public void setGlycemiaMeasurements(List<Glycemia> glycemiaMeasurements) {
        this.glycemiaMeasurements = glycemiaMeasurements;
    }

    public DiaryEntry getDiaryEntry() {
        return diaryEntry;
    }

    public List<Glycemia> getGlycemiaMeasurements() {
        return glycemiaMeasurements;
    }
}
