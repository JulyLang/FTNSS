package com.example.app.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal")
public class Meal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "date")
    private long date;

    @ColumnInfo(name = "kcal")
    private int kcal;

    public Meal(int id, long date, int kcal) {
        this.id = id;
        this.date = date;
        this.kcal = kcal;
    }

    public int getId() {
        return id;
    }

    public int getKcal() {
        return kcal;
    }

    public long getDate() {
        return date;
    }
}