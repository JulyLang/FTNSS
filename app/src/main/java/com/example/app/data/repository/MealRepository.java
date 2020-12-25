package com.example.app.data.repository;

import com.example.app.data.mapper.IMealMapper;
import com.example.app.data.mapper.MealMapper;
import com.example.app.domain.model.MealModel;
import com.example.app.domain.repository.IMealRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class MealRepository implements IMealRepository {

    private final IMealMapper mealMapper = new MealMapper();
    private final IMealLocalDataStore mealLocalDataStore = new MealLocalDataStore();

    @Override
    public Observable<List<MealModel>> getAllMeals(long startDate, long endDate) {
        return mealLocalDataStore.getAllMeals(startDate, endDate)
                .map(mealMapper::toMealModel);
    }

    @Override
    public Completable createMeal(long date) {
        return mealLocalDataStore.createMeal(date);
    }

}
