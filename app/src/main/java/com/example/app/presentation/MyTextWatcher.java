package com.example.app.presentation;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyTextWatcher implements TextWatcher {

    private EditText currentEditText;
    private TextView calcTextView;
    private TextView totalProductTextView;
    private ITotalMealListener iTotalMealListener;

    public MyTextWatcher(EditText currentEditText, TextView calcTextView, TextView totalTextView, ITotalMealListener iTotalMealListener) {
        this.currentEditText = currentEditText;
        this.calcTextView = calcTextView;
        this.totalProductTextView = totalTextView;
        this.iTotalMealListener = iTotalMealListener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // проверка на 0 в начале цифр
        String text = currentEditText.getText().toString();
        if (!text.isEmpty() && "0".equals(String.valueOf(text.charAt(0)))) {
            // Not allowed
            Toast.makeText(currentEditText.getContext(), "not allowed", Toast.LENGTH_LONG).show();
            currentEditText.setText(text.substring(1));
            currentEditText.setSelection(text.length() - 1);
        }
        totalProductTextView.setText(calculateProductTotal());
        iTotalMealListener.onTotalChanged();
    }

    @Override
    public void afterTextChanged(Editable s) {}

    private String calculateProductTotal() {
        return Integer.toString(Math.round(parseInt(currentEditText) * parseInt(calcTextView) / 100f));
    }

    private int parseInt(TextView textView) {
        String string = textView.getText().toString();
        return Integer.parseInt(string.isEmpty() ? "0" : string);
    }
}
