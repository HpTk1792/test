package com.android.alejandroquiroga.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

//TODO Rename Elements

@Entity(tableName = "ExampleElement")
public class ExampleElement {

    @PrimaryKey
    @NonNull
    private String id;
    private String attribute1;
    private String attribute2;
    private String attribute3;

    /**
     * Builders
     */

    /**
     * Basic Builder
     */
    public ExampleElement(){
        id = UUID.randomUUID().toString();
    }

    public ExampleElement(String a1, String a2, String a3){
        id = UUID.randomUUID().toString();
        attribute1 = a1;
        attribute2 = a2;
        attribute3 = a3;
    }

    //Getters
    @NonNull
    public String getId(){ return id; }
    public String getAttribute1() { return attribute1; }
    public String getAttribute2() { return attribute2; }
    public String getAttribute3() { return attribute3; }

    //Setters
    public void setId(@NonNull String newId){ id = newId; }
    public void setAttribute1(String newAttribute) { attribute1 = newAttribute; }
    public void setAttribute2(String newAttribute) { attribute2 = newAttribute; }
    public void setAttribute3(String newAttribute) { attribute3 = newAttribute; }
}
