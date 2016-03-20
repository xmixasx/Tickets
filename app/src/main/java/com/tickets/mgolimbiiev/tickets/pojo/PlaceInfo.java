package com.tickets.mgolimbiiev.tickets.pojo;

/**
 * Created by Миха on 19.03.2016.
 */
public class PlaceInfo {
    String title;
    String letter;
    int places;

    public PlaceInfo(String title, String letter, int places) {
        this.title = title;
        this.letter = letter;
        this.places = places;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }
}
