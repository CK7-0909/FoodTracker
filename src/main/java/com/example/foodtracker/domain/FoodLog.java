package com.example.foodtracker.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "userFoodlog")
public class FoodLog {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "fdc_id", nullable = false)
    private Long fdcId;

    @Column(name = "meal_type", nullable = false)
    private String mealType;

    @CreationTimestamp
    @Column(name = "logged_at", nullable = false, updatable = false)
    private LocalDateTime loggedAt;

    @Column(precision = 7, scale = 2)
    private BigDecimal calories;

    @Column(precision = 7, scale = 2)
    private BigDecimal protein;

    @Column(precision = 7, scale = 2)
    private BigDecimal fat;

    @Column(precision = 7, scale = 2)
    private BigDecimal carbs;

    // -- Constructors (no-arg is required by JPA) --

    public FoodLog() {
    }

    // convenience constructor if you like:
    public FoodLog(Long userId, Long fdcId, String mealType,
                   BigDecimal calories, BigDecimal protein,
                   BigDecimal fat, BigDecimal carbs) {
        this.userId = userId;
        this.fdcId = fdcId;
        this.mealType = mealType;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Long getFdcId() {
        return fdcId;
    }

    public void setFdcId(Long fdcId) {
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
}
