package com.example.app.presentation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app.R;

public class MealDetailsFragment extends Fragment implements ITotalMealListener {

    public static final String TAG = "MealOneFragment";
    public static final String MEAL_RESULT_KEY = "MEAL_RESULT_KEY";
    int productCounter = 1;



    public MealDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
              // Handle the back button event
                Intent intent = new Intent();

                int kcal = Integer.valueOf(((TextView) getView().
                        findViewById(R.id.mealTotalCount)).getText().toString());

                intent.putExtra(MEAL_RESULT_KEY, new MealResult(1,kcal));
                getTargetFragment().onActivityResult(getTargetRequestCode(),
                        Activity.RESULT_OK, intent);

                if (isEnabled()) {
                    setEnabled(false);
                    requireActivity().onBackPressed();
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        displayProduct1Total();

        EditText product1WeightEditText = getView().findViewById(R.id.product1WeightEditText);
        EditText Product1KcalEditText = getView().findViewById(R.id.product1KcalPer100GEditText);
        TextView total = getView().findViewById(R.id.product1KcalTotalCount);

        product1WeightEditText.addTextChangedListener(new MyTextWatcher(product1WeightEditText, Product1KcalEditText, total, this));
        Product1KcalEditText.addTextChangedListener(new MyTextWatcher(Product1KcalEditText, product1WeightEditText, total, this));

        //Sets listener to adding products to current meal
        final Button plusButton = getView().findViewById(R.id.plus_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (productCounter<7) {
                    productCounter++;
                    Log.d(TAG, "mealsCounter=" + productCounter);
                    addProduct();
                }
                else {
                    Toast.makeText(getContext(), "Max 7 products", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Calculates and displays product total Kcal
        public void displayProduct1Total() {
            TextView product1KcalCount = getView().findViewById(R.id.product1KcalTotalCount);
            product1KcalCount.setText(calculateProduct1Total());
        }

        public String calculateProduct1Total() {
            TextView product1WeightView = getView().findViewById(R.id.product1WeightEditText);
            String product1WeightString = product1WeightView.getText().toString();
            int product1Weight = Integer.parseInt(product1WeightString.isEmpty() ? "0" : product1WeightString);

            TextView product1KcalView = getView().findViewById(R.id.product1KcalPer100GEditText);
            String product1KcalString = product1KcalView.getText().toString();
            int product1Kcal = Integer.parseInt(product1KcalString.isEmpty() ? "0" : product1KcalString);

        int product1Total = Math.round(product1Weight / 100f * product1Kcal);
        return product1Total + "";
        }


        // Adds one row in GridLayout
    public void addProduct(){
        GridLayout mealsLayout = getView().findViewById(R.id.table);

        EditText prod = new EditText(getContext());
        prod.setText("Product");
        prod.setTextSize(16);
        prod.setEms(4);
        mealsLayout.addView(prod);

        EditText weight = new EditText(getContext());
        weight.setText("0");
        weight.setTextSize(16);
        weight.setEms(4);
        weight.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        weight.setInputType(InputType.TYPE_CLASS_NUMBER);
        mealsLayout.addView(weight);

        EditText kcal = new EditText(getContext());
        kcal.setText("0");
        kcal.setTextSize(16);
        kcal.setEms(4);
        kcal.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        kcal.setInputType(InputType.TYPE_CLASS_NUMBER);
        mealsLayout.addView(kcal);

        TextView totalKcal = new TextView(getContext());
        totalKcal.setText("0");
        totalKcal.setTextColor(Color.BLACK);
        totalKcal.setTextSize(16);
        totalKcal.setEms(4);
        mealsLayout.addView(totalKcal);

        MyTextWatcher weightTextWatcher = new MyTextWatcher(weight, kcal, totalKcal, this);
        weight.addTextChangedListener(weightTextWatcher);

        MyTextWatcher kcalTextWatcher = new MyTextWatcher(kcal, weight, totalKcal, this);
        kcal.addTextChangedListener(kcalTextWatcher);
    }

    @Override
    public void onTotalChanged() {
        // Calculates and sets Total Meal Kcal field
        TextView product1KcalTotalCount = getView().findViewById(R.id.product1KcalTotalCount);
        String product1 = product1KcalTotalCount.getText().toString();
        TextView mealTotalCount = getView().findViewById(R.id.mealTotalCount);
        mealTotalCount.setText(product1);
    }
}
