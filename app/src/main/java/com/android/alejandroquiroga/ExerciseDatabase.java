package com.android.alejandroquiroga;

import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.android.alejandroquiroga.Models.Converters;
import com.android.alejandroquiroga.Models.Assistents;
import com.android.alejandroquiroga.Models.AssistentsDao;

@androidx.room.Database(version = 1, entities = {Assistents.class })
@TypeConverters({Converters.class})
public abstract class ExerciseDatabase extends RoomDatabase {
    abstract public AssistentsDao getAssistentsDao();
}
