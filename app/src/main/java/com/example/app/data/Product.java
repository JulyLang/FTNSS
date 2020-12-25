package com.example.app.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.app.data.Meal;

@Entity(
        tableName = "product",
        foreignKeys = @ForeignKey(
                entity = Meal.class,
                parentColumns = "id",
                childColumns = "mealId"))
public class Product {
    @PrimaryKey(autoGenerate = true)
    private final int productId;

    @ColumnInfo(name = "productName")
    private String productName;

    @ColumnInfo(name = "weight")
    private int weight;

    @ColumnInfo(name = "kcalPer100g")
    private int kcalPer100g;

    @ColumnInfo(name = "mealId")
    private final int mealId;

    public Product(int mealId, int productId, String productName, int weight, int kcalPer100g) {
        this.mealId = mealId;
        this.productId = productId;
        this.productName = productName;
        this.weight = weight;
        this.kcalPer100g = kcalPer100g;
    }

    public int getMealId() {
        return mealId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getWeight() {
        return weight;
    }

    public int getKcalPer100g() {
        return kcalPer100g;
    }
}