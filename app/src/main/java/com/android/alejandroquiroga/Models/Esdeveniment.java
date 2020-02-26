package com.android.alejandroquiroga.Models;

import java.sql.Date;

public class Esdeveniment {
    public String title;
    public String place;
    public String room;
    public int people;
    public Date date;
    public String email;
    public int price;
    public String description;

    public Esdeveniment(String title, String place, String room, int people, Date date, String email, int price, String description) {
        this.title = title;
        this.place = place;
        this.room = room;
        this.people = people;
        this.date = date;
        this.email = email;
        this.price = price;
        this.description = description;
    }

    public Esdeveniment() {}
}
