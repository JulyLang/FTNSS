package com.example.app.domain.model;

public class MealModel {

    private final int id;
    private long date;
    private int kcal;

    public MealModel(int id, long date, int kcal) {
        this.id = id;
        this.date = date;
        this.kcal = kcal;
    }

    public int getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }
}
