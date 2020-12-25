package com.example.app.data.repository;

import com.example.app.data.Meal;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface IMealLocalDataStore {

    Observable<List<Meal>> getAllMeals(long startDate, long endDate);

    Completable createMeal(long date);
}
