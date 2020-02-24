package com.android.alejandroquiroga;

import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.android.alejandroquiroga.Models.Converters;
import com.android.alejandroquiroga.Models.ExampleElement;
import com.android.alejandroquiroga.Models.ExampleElementDao;

//TODO Rename elements and remove unused commented lines

@androidx.room.Database(version = 1, entities = {ExampleElement.class, /*ExampleElement2.class*/ })
@TypeConverters({Converters.class})
public abstract class ExerciseDatabase extends RoomDatabase {
    abstract public ExampleElementDao getExampleElementDao();
//    abstract public ExampleElement2Dao getExampleElement2Dao();
}
