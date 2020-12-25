package com.example.app.domain.repository;

import com.example.app.domain.model.MealModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface IMealRepository {

    Observable<List<MealModel>> getAllMeals(long startDate, long endDate);

    Completable createMeal(long date);
}
