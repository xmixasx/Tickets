package com.tickets.mgolimbiiev.tickets.pojo;

import java.util.List;

/**
 * Created by Миха on 19.03.2016.
 */
public class SearchResponseObject {
    String num;
    int model;
    int category;
    String travel_time;
    TravelStation from;
    TravelStation till;
    List<PlaceInfo> types;
    String error;
    String data;

    public SearchResponseObject(String num, int model, int category, String travel_time, TravelStation from, TravelStation till, List<PlaceInfo> types, String error, String data) {
        this.num = num;
        this.model = model;
        this.category = category;
        this.travel_time = travel_time;
        this.from = from;
        this.till = till;
        this.types = types;
        this.error = error;
        this.data = data;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTravel_time() {
        return travel_time;
    }

    public void setTravel_time(String travel_time) {
        this.travel_time = travel_time;
    }

    public TravelStation getFrom() {
        return from;
    }

    public void setFrom(TravelStation from) {
        this.from = from;
    }

    public TravelStation getTill() {
        return till;
    }

    public void setTill(TravelStation till) {
        this.till = till;
    }

    public List<PlaceInfo> getTypes() {
        return types;
    }

    public void setTypes(List<PlaceInfo> types) {
        this.types = types;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
