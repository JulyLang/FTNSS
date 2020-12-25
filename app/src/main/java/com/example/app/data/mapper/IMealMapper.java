package com.example.app.data.mapper;

import com.example.app.data.Meal;
import com.example.app.domain.model.MealModel;

import java.util.List;

public interface IMealMapper {

    MealModel toMealModel(Meal meal);

    List<MealModel> toMealModel(List<Meal> meals);
}
