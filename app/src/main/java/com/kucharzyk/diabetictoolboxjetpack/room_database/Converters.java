package com.kucharzyk.diabetictoolboxjetpack.room_database;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Converters {

    @TypeConverter
    public static LocalDate fromTimestampToLocalDate(Long localDateValue) {
        return localDateValue == null ? null : LocalDate.ofEpochDay(localDateValue);
    }

    @TypeConverter
    public static Long localDateToTimestamp(LocalDate localDate) {
        return localDate == null ? null : localDate.toEpochDay();
    }

    @TypeConverter
    public static LocalDateTime fromTimestampToLocalDateTime(Long localDateTimeValue) {
        return localDateTimeValue == null ? null : LocalDateTime.ofEpochSecond(localDateTimeValue, 0, ZoneOffset.UTC);
    }

    @TypeConverter
    public static Long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.toEpochSecond(ZoneOffset.UTC);
    }
}
