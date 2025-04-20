package com.example.foodtracker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class FoodLogRequestDto {
    @NotNull
    private Long fdcId;

    @NotNull
    @Pattern(regexp = "BREAKFAST|LUNCH|DINNER|SNACK",
            message = "Must be one of BREAKFAST, LUNCH, DINNER or SNACK")
    private String mealType;

    public Long getFdcId() {
        return fdcId;
    }

    public void setFdcId(Long fdcId) {
        this.fdcId = fdcId;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
}

