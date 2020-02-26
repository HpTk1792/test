package com.android.alejandroquiroga.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "Assistents")
public class Assistents {

    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String email;
    private String sueñosYesperanzas;

    /**
     * Builders
     */

    /**
     * Basic Builder
     */
    public Assistents(){
        id = UUID.randomUUID().toString();
    }

    public Assistents(String a1, String a2, String a3){
        id = UUID.randomUUID().toString();
        name = a1;
        email = a2;
        sueñosYesperanzas = a3;
    }

    //Getters
    @NonNull
    public String getId(){ return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getSueñosYesperanzas() { return sueñosYesperanzas; }

    //Setters
    public void setId(@NonNull String newId){ id = newId; }
    public void setName(String newAttribute) { name = newAttribute; }
    public void setEmail(String newAttribute) { email = newAttribute; }
    public void setSueñosYesperanzas(String newAttribute) { sueñosYesperanzas = newAttribute; }
}
