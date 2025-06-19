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

    @Column(precision = 10, scale = 3)
    private BigDecimal calcium;

    @Column(precision = 10, scale = 3)
    private BigDecimal iron;

    @Column(precision = 10, scale = 3)
    private BigDecimal potassium;

    @Column(precision = 10, scale = 3)
    private BigDecimal polyunsaturatedFat;

    @Column(precision = 10, scale = 3)
    private BigDecimal monounsaturatedFat;

    @Column(precision = 10, scale = 3)
    private BigDecimal vitaminA;

    @Column(precision = 10, scale = 3)
    private BigDecimal vitaminC;

    @Column(precision = 10, scale = 3)
    private BigDecimal vitaminD;

    public FoodLog() {
    }

    // convenience constructor if you like:
    public FoodLog(Long userId, int fdcId, String mealType,
                   BigDecimal calories, BigDecimal fat, BigDecimal saturatedFat, BigDecimal transFat,
                   BigDecimal carbs, BigDecimal fiber, BigDecimal protein, BigDecimal cholesterol,
                   BigDecimal sodium, BigDecimal sugar, BigDecimal servingSize, BigDecimal servings, String brandName,
                   BigDecimal calcium, BigDecimal iron, BigDecimal potassium, BigDecimal polyunsaturatedFat, BigDecimal monounsaturatedFat,
                   BigDecimal vitaminA, BigDecimal vitaminC, BigDecimal vitaminD) {
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
        this.calcium = calcium;
        this.iron = iron;
        this.potassium = potassium;
        this.polyunsaturatedFat = polyunsaturatedFat;
        this.monounsaturatedFat = monounsaturatedFat;
        this.vitaminA = vitaminA;
        this.vitaminC = vitaminC;
        this.vitaminD = vitaminD;
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

    public BigDecimal getCalcium() {
        return calcium;
    }

    public void setCalcium(BigDecimal calcium) {
        this.calcium = calcium;
    }

    public BigDecimal getIron() {
        return iron;
    }

    public void setIron(BigDecimal iron) {
        this.iron = iron;
    }

    public BigDecimal getPolyunsaturatedFat() {
        return polyunsaturatedFat;
    }

    public void setPolyunsaturatedFat(BigDecimal polyunsaturatedFat) {
        this.polyunsaturatedFat = polyunsaturatedFat;
    }

    public BigDecimal getPotassium() {
        return potassium;
    }

    public void setPotassium(BigDecimal potassium) {
        this.potassium = potassium;
    }

    public BigDecimal getMonounsaturatedFat() {
        return monounsaturatedFat;
    }

    public void setMonounsaturatedFat(BigDecimal monounsaturatedFat) {
        this.monounsaturatedFat = monounsaturatedFat;
    }

    public BigDecimal getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(BigDecimal vitaminA) {
        this.vitaminA = vitaminA;
    }

    public BigDecimal getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(BigDecimal vitaminC) {
        this.vitaminC = vitaminC;
    }

    public BigDecimal getVitaminD() {
        return vitaminD;
    }

    public void setVitaminD(BigDecimal vitaminD) {
        this.vitaminD = vitaminD;
    }
}
