package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import com.kucharzyk.diabetictoolboxjetpack.room_database.Glycemia;

import java.time.LocalDate;
import java.util.List;

public class DiaryMeasurementEntrySummary {

    private final List<Glycemia> glycemiaMeasurementList;
    private final LocalDate diaryEntryDate;

    public DiaryMeasurementEntrySummary(List<Glycemia> measurements, LocalDate entryDate) {
        glycemiaMeasurementList = measurements;
        diaryEntryDate = entryDate;
    }

    public Integer getMeasurementsCount() {

        return glycemiaMeasurementList.size();
    }

    public Integer getStandardMeasurementsCount() {
        int standardMeasurements = 0;
        for (Glycemia measurement:glycemiaMeasurementList
             ) {
            if (measurement.getMeasurementValue() >= 70 && measurement.getMeasurementValue() < 100 ) {
                standardMeasurements += 1;
            }
        }
        return standardMeasurements;
    }

    public Integer getHyperglycemiaMeasurementsCount() {
        int hypoglycemiaMeasurements = 0;
        for (Glycemia measurement:glycemiaMeasurementList
        ) {
            if (measurement.getMeasurementValue() >= 100) {
                hypoglycemiaMeasurements += 1;
            }
        }
        return hypoglycemiaMeasurements;
    }

    public Integer getHypoglycemiaMeasurementsCount() {
        int hyperglycemiaMeasurements = 0;
        for (Glycemia measurement:glycemiaMeasurementList
        ) {
            if (measurement.getMeasurementValue() < 70) {
                hyperglycemiaMeasurements += 1;
            }
        }
        return hyperglycemiaMeasurements;
    }

    public LocalDate getDiaryEntryDate() {
        return diaryEntryDate;
    }

}
