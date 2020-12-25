package com.example.app.presentation.main;

import android.text.format.DateFormat;
import android.util.Log;

import com.example.app.domain.CreateMealUseCase;
import com.example.app.domain.GetAllMealsUseCase;
import com.example.app.domain.model.MealModel;
import com.example.app.presentation.click.ClickItem;
import com.example.app.presentation.error.ErrorCode;
import com.example.app.presentation.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private final GetAllMealsUseCase getAllMealsUseCase = new GetAllMealsUseCase(
            Schedulers.io(),
            AndroidSchedulers.mainThread()
    );
    private final CreateMealUseCase createMealUseCase = new CreateMealUseCase(
            Schedulers.io(),
            AndroidSchedulers.mainThread()
    );

    private static final int MAX_MEAL_COUNT = 7;
    private List<MealModel> meals = new ArrayList<>();
    private int mealsCounter = 0;
    private int totalKCal = 0;
    private long currentTimestamp = 0L;

    @Override
    protected String logTag() {
        return "MainPresenter";
    }

    @Override
    public void subscribe(MainContract.View view) {
        super.subscribe(view);
        currentTimestamp = System.currentTimeMillis();
        initClicks();
        addDisposable(getAllMealsUseCase.execute(null)
                .subscribe(this::onGetAllMealsNext));
        showCurrentDate(currentTimestamp);
    }

    private void initClicks() {
        if (!isViewBound()) return;
        addDisposable(view.onMealClick().subscribe(this::onMealClickNext));
        addDisposable(view.onAddMealClick().subscribe(this::onAddMealClickNext));
    }

    private void onMealClickNext(ClickItem clickItem) {
        Log.d(logTag(), "onMealClickNext clickItem=" + clickItem);
        if (!isViewBound()) return;
        view.navigateToMealDetails(clickItem.getId());
    }

    private void onAddMealClickNext(Object notification) {
        Log.d(logTag(), "onAddMealClickNext");
        if (mealsCounter >= MAX_MEAL_COUNT) {
            Log.e(logTag(), "Can't create new meal, reach max meals!");
            if (isViewBound()) {
                view.showError(ErrorCode.REACH_MAX_MEALS);
            }
        } else {
            addDisposable(createMealUseCase.execute(new CreateMealUseCase.Params(System.currentTimeMillis()))
                    .subscribe(this::onCreateMealNext));
        }
    }

    private void onGetAllMealsNext(List<MealModel> meals) {
        final int mealsSize = meals.size();
        Log.d(logTag(), "onGetAllMealsNext meals size=" + mealsSize);
        showMeals(meals);
    }

    private void onCreateMealNext() {
        Log.d(logTag(), "onCreateMealNext");
    }

    private void swapMeals(List<MealModel> meals) {
        this.meals = meals;
        mealsCounter = meals.size();
        totalKCal = 0;
        for (MealModel mealModel : meals) {
            totalKCal += mealModel.getKcal();
        }
    }

    private void addMeal(MealModel mealModel) {
        meals.add(mealModel);
        mealsCounter++;
        totalKCal += mealModel.getKcal();
    }

    private void showMeals(List<MealModel> meals) {
        swapMeals(meals);
        if (isViewBound()) {
            view.showMeals(meals);
            view.updateTotalKCal(String.valueOf(totalKCal));
        }
    }

    private void showNewMeal(MealModel mealModel) {
        addMeal(mealModel);
        if (isViewBound()) {
            view.showNewMeal(mealModel);
            view.updateTotalKCal(String.valueOf(totalKCal));
        }
    }

    private void showCurrentDate(long timestamp) {
        if (isViewBound()) {
            view.showCurrentDate(timestampToString(timestamp));
        }
    }

    private String timestampToString(long timestamp) {
        return DateFormat.format("dd-MM-yyyy", timestamp).toString();
    }
}
