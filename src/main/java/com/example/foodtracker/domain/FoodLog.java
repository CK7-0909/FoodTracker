package com.example.foodtracker.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "foodlog")
public class FoodLog {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "fdc_id", nullable = false)
    private int fdcId;

    @Column(name = "meal_type", nullable = false)
    private String mealType;

    @CreationTimestamp
    @Column(name = "logged_at", nullable = false, updatable = false)
    private LocalDateTime loggedAt;

    @Column(name = "serving_size", nullable = false, updatable = false, precision = 10, scale = 3)
    private BigDecimal servingSize;

    @Column(name = "brand_name", nullable = false, updatable = false)
    private String brandName;

    @Column(precision = 10, scale = 3)
    private BigDecimal servings;

    @Column(precision = 10, scale = 3)
    private BigDecimal calories;

    @Column(precision = 10, scale = 3)
    private BigDecimal fat;

    @Column(precision = 10, scale = 3)
    private BigDecimal saturatedFat;

    @Column(precision = 10, scale = 3)
    private BigDecimal transFat;

    @Column(precision = 10, scale = 3)
    private BigDecimal carbs;

    @Column(precision = 10, scale = 3)
    private BigDecimal fiber;

    @Column(precision = 10, scale = 3)
    private BigDecimal protein;

    @Column(precision = 10, scale = 3)
    private BigDecimal cholesterol;

    @Column(precision = 10, scale = 3)
    private BigDecimal sodium;

    @Column(precision = 10, scale = 3)
    private BigDecimal sugar;

    public FoodLog() {
    }

    // convenience constructor if you like:
    public FoodLog(Long userId, int fdcId, String mealType,
                   BigDecimal calories, BigDecimal fat, BigDecimal saturatedFat, BigDecimal transFat,
                   BigDecimal carbs, BigDecimal fiber, BigDecimal protein, BigDecimal cholesterol,
                   BigDecimal sodium, BigDecimal sugar, BigDecimal servingSize, BigDecimal servings, String brandName) {
        this.userId = userId;
        this.fdcId = fdcId;
        this.mealType = mealType;
        this.calories = calories;
        this.fat = fat;
        this.saturatedFat = saturatedFat;
        this.transFat = transFat;
        this.carbs = carbs;
        this.fiber = fiber;
        this.protein = protein;
        this.cholesterol = cholesterol;
        this.sodium = sodium;
        this.sugar = sugar;
        this.servingSize = servingSize;
        this.servings = servings;
        this.brandName = brandName;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public int getFdcId() {
        return fdcId;
    }

    public void setFdcId(int fdcId) {
        this.fdcId = fdcId;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }

    public BigDecimal getCalories() {
        return calories;
    }

    public void setCalories(BigDecimal calories) {
        this.calories = calories;
    }

    public BigDecimal getProtein() {
        return protein;
    }

    public void setProtein(BigDecimal protein) {
        this.protein = protein;
    }

    public BigDecimal getFat() {
        return fat;
    }

    public void setFat(BigDecimal fat) {
        this.fat = fat;
    }

    public BigDecimal getCarbs() {
        return carbs;
    }

    public void setCarbs(BigDecimal carbs) {
        this.carbs = carbs;
    }

    public BigDecimal getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(BigDecimal saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public BigDecimal getTransFat() {
        return transFat;
    }

    public void setTransFat(BigDecimal transFat) {
        this.transFat = transFat;
    }

    public BigDecimal getFiber() {
        return fiber;
    }

    public void setFiber(BigDecimal fiber) {
        this.fiber = fiber;
    }

    public BigDecimal getSodium() {
        return sodium;
    }

    public void setSodium(BigDecimal sodium) {
        this.sodium = sodium;
    }

    public BigDecimal getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(BigDecimal cholesterol) {
        this.cholesterol = cholesterol;
    }

    public BigDecimal getSugar() {
        return sugar;
    }

    public void setSugar(BigDecimal sugar) {
        this.sugar = sugar;
    }

    public BigDecimal getServingSize() {
        return servingSize;
    }

    public void setServingSize(BigDecimal servingSize) {
        this.servingSize = servingSize;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public BigDecimal getServings() {
        return servings;
    }

    public void setServings(BigDecimal servings) {
        this.servings = servings;
    }
}
