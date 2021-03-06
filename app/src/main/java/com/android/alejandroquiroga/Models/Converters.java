package com.android.alejandroquiroga.Models;

import java.util.Date;
import androidx.room.TypeConverter;

//TODO Remove if unused

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
