package com.example.app.data.repository;

import com.example.app.data.Meal;
import com.example.app.data.MealDao;
import com.example.app.data.MealDatabase;
import com.example.app.presentation.App;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class MealLocalDataStore implements IMealLocalDataStore {

    private final MealDao mealDao = MealDatabase.getInstance(App.getAppContext()).mealDao();

    @Override
    public Observable<List<Meal>> getAllMeals(long startDate, long endDate) {
        return mealDao.getAllMeals(startDate, endDate);
    }

    @Override
    public Completable createMeal(long date) {
        return mealDao.createMeal(new Meal(0, date, 0));
    }
}
