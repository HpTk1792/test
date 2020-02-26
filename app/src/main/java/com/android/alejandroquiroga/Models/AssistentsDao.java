package com.android.alejandroquiroga.Models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 *  User Class define the features and methods of Users
 */

@Dao
public interface AssistentsDao {

    @Query("SELECT * FROM Assistents")
    List<Assistents> getAssistents();

    @Query("SELECT * FROM Assistents WHERE id LIKE :uuid")
    Assistents getAssistent(String uuid);

    @Insert
    void addAssistent(Assistents exampleElement);

    @Delete
    void deleteAssistent(Assistents exampleElement);

    @Update
    void updateAssistent(Assistents exampleElement);
}
