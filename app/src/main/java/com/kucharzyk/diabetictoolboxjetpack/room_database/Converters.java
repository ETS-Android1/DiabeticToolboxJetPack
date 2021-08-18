package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.TypeConverter;

import java.time.LocalDate;

public class Converters {

    @TypeConverter
    public static LocalDate fromTimestamp(Long localDateValue) {
        return localDateValue == null ? null : LocalDate.ofEpochDay(localDateValue);
    }

    @TypeConverter
    public static Long localDateToTimestamp(LocalDate localDate) {
        return localDate == null ? null : localDate.toEpochDay();
    }
}
