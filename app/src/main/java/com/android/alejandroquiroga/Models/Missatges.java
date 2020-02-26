package com.android.alejandroquiroga.Models;

public class Missatges {

    private String id;
    private String missatge;

    public Missatges(){}

    public Missatges(String newId, String newMissatge){
        id = newId;
        missatge = newMissatge;
    }

    public String getId() {
        return id;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }
}
