package com.example.app.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app.R;
import com.example.app.domain.model.MealModel;
import com.example.app.presentation.MealDetailsFragment;
import com.example.app.presentation.click.ClickItem;
import com.example.app.presentation.mvp.BaseFragment;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class MainFragment extends BaseFragment<MainContract.View, MainContract.Presenter> implements MainContract.View {

    private static final int REQUEST_2_FRAGMENT = 1;//todo rename

    private BehaviorSubject<ClickItem> mealClicksSubject = BehaviorSubject.create();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new MainPresenter());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mealClicksSubject = null;
    }

    //    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        final Button plusButton = getView().findViewById(R.id.plus_button);
//        //todo use RxClicks and create Observables from this View to Presenter
//        plusButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                if (mealsCounter<maxMealCount) {
//                mealsCounter++;
//                Log.d(logTag(), "mealsCounter=" + mealsCounter);
//                addMeal();
//            }
//                else {
//                    Toast.makeText(getContext(), "Max 7 meals", Toast.LENGTH_LONG).show();
//        };
//    }
//    });
//
//        final Button goTo2FragmentButton = getView().findViewById(R.id.meal1);
//        goTo2FragmentButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                goTo2Fragment();
//            }
//        });
//    }

    @Override
    protected String logTag() {
        return "MainFragment";
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_main_activity;
    }

    @Override
    public void showMeals(List<MealModel> meals) {
        final View view = getView();
        if (view != null) {
            final LinearLayout mealsLayout = view.findViewById(R.id.mealsLayout);
            mealsLayout.removeAllViews();
        }
        for (MealModel mealModel : meals) {
            addMeal(mealModel);
        }
    }

    @Override
    public void showNewMeal(MealModel mealModel) {
        addMeal(mealModel);
    }

    @Override
    public void updateTotalKCal(String totalKCal) {
        final View view = getView();
        if (view != null) {
            final TextView totalCkalCountTextView = view.findViewById(R.id.totalCkalCountTextView);
            totalCkalCountTextView.setText(totalKCal);
        }
    }

    @Override
    public void showCurrentDate(String date) {
        final View view = getView();
        if (view != null) {
            final TextView dateTextView = view.findViewById(R.id.dateTextView);
            dateTextView.setText(date);
        }
    }

    @Override
    public Observable<ClickItem> onMealClick() {
        return mealClicksSubject;
    }

    @Override
    public Observable<Object> onAddMealClick() {
        final View view = getView();
        if (view != null) {
            final Button addMealButton = view.findViewById(R.id.plus_button);
            return RxView.clicks(addMealButton);
        }
        return Observable.empty();
    }

    @Override
    public void navigateToMealDetails(int mealId) {
        //todo pass mealId as arg
        Fragment mealDetailsFragment = new MealDetailsFragment();
        mealDetailsFragment.setTargetFragment(this, REQUEST_2_FRAGMENT);
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, mealDetailsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //todo catch result ?

//        MealResult Meal1Result = data.getExtras().getParcelable(MEAL_RESULT_KEY);
//
//    Button meal1KcalButton = getView().findViewById(R.id.meal1);
//    meal1KcalButton.setText
//            ("Meal " + Meal1Result.mealNumber + ": " + Meal1Result.mealKcal + " Kcal");
    }

    private void addMeal(MealModel mealModel) {
        final View view = getView();
        if (view != null) {
            final LinearLayout mealsLayout = view.findViewById(R.id.mealsLayout);
            final Button mealButton = new Button(getContext());
            mealButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            mealButton.setText("Meal id:" + mealModel.getId() + " kcal:" + mealModel.getKcal());//todo use text placeholders
            mealsLayout.addView(mealButton);
            RxView.clicks(mealButton)
                    .map(ignore -> new ClickItem(mealModel.getId()))
                    .subscribe(mealClicksSubject);
        }
    }
}