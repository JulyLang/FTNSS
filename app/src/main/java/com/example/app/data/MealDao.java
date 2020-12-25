package com.example.app.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface MealDao {

    @Query("SELECT * FROM meal WHERE date BETWEEN :startDate AND :endDate")
    Observable<List<Meal>> getAllMeals(long startDate, long endDate);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable createMeal(Meal meal);

    @Query("SELECT * FROM meal WHERE id IN (:id)")
    Observable<Meal> getMeal(long id);

    @Query("UPDATE meal SET kcal =:newMealKcal WHERE id IN (:id)")
    void updateMeal(int id, int newMealKcal);

    @Query("DELETE from meal WHERE id IN (:id)")
    void deleteMeal(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createProduct(Product product);
}