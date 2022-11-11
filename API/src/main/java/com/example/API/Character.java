package com.example.API;

public class Character {

    private final Integer id;
    private String vorname, nachname;

    public Character(Integer id, String vorname, String nachname) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getVorname() {
        return vorname;
    }
}