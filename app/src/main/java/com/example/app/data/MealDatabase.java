package com.example.app.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Meal.class, Product.class}, version = 1)
public abstract class MealDatabase extends RoomDatabase {

    private static volatile MealDatabase INSTANCE;
    private static final String DB_NAME = "meal.db";

    public abstract MealDao mealDao();

    public static MealDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MealDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MealDatabase.class, DB_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}