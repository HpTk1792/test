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
//TODO Rename elements

@Dao
public interface ExampleElementDao {

    @Query("SELECT * FROM ExampleElement")
    List<ExampleElement> getExampleElements();

    @Query("SELECT * FROM ExampleElement WHERE id LIKE :uuid")
    ExampleElement getExampleElement(String uuid);

    @Insert
    void addExampleElement(ExampleElement exampleElement);

    @Delete
    void deleteExampleElement(ExampleElement exampleElement);

    @Update
    void updateExampleElement(ExampleElement exampleElement);
}
