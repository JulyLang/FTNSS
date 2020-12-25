package com.example.app.presentation.main;

import com.example.app.domain.model.MealModel;
import com.example.app.presentation.click.ClickItem;
import com.example.app.presentation.error.ErrorCode;
import com.example.app.presentation.mvp.IBasePresenter;
import com.example.app.presentation.mvp.IBaseView;

import java.util.List;

import io.reactivex.Observable;

public interface MainContract {

    interface Presenter extends IBasePresenter<View> {}

    interface View extends IBaseView<Presenter> {

        void showMeals(List<MealModel> meals);

        void showNewMeal(MealModel mealModel);

        void updateTotalKCal(String totalKCal);

        void showCurrentDate(String date);

        Observable<ClickItem> onMealClick();

        Observable<Object> onAddMealClick();

        void navigateToMealDetails(int mealId);
    }
}
