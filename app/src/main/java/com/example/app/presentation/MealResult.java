package com.example.app.presentation;

import android.os.Parcel;
import android.os.Parcelable;

public class MealResult implements Parcelable {

    public int mealNumber;
    public int mealKcal;

    public MealResult(int mealNumber, int mealKcal) {
        this.mealNumber = mealNumber;
        this.mealKcal = mealKcal;
    }

    public MealResult(Parcel in) {
        mealNumber = in.readInt();
        mealKcal = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mealNumber);
        dest.writeInt(mealKcal);
    }

    //переменная CREATOR указываем на статический анонимный класс
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public MealResult createFromParcel(Parcel in) {
            return new MealResult(in);
        }

        public MealResult[] newArray(int size) {
            return new MealResult[size];
        }
    };
}