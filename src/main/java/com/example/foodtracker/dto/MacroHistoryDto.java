package com.example.foodtracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MacroHistoryDto {
    private LocalDate day;
    private BigDecimal protein;
    private BigDecimal carbs;
    private BigDecimal fat;

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public BigDecimal getProtein() {
        return protein;
    }

    public void setProtein(BigDecimal protein) {
        this.protein = protein;
    }

    public BigDecimal getCarbs() {
        return carbs;
    }

    public void setCarbs(BigDecimal carbs) {
        this.carbs = carbs;
    }

    public BigDecimal getFat() {
        return fat;
    }

    public void setFat(BigDecimal fat) {
        this.fat = fat;
    }
}