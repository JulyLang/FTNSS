package com.example.app.data.mapper;

import com.example.app.data.Meal;
import com.example.app.domain.model.MealModel;

import java.util.ArrayList;
import java.util.List;

public class MealMapper implements IMealMapper {

    @Override
    public MealModel toMealModel(Meal meal) {
        return new MealModel(
                meal.getId(),
                meal.getDate(),
                meal.getKcal()
        );
    }

    @Override
    public List<MealModel> toMealModel(List<Meal> meals) {
        final List<MealModel> result = new ArrayList<>(meals.size());
        for (Meal meal : meals) {
            result.add(toMealModel(meal));
        }
        return result;
    }
}
