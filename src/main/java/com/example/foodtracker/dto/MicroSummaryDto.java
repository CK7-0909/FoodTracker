package com.example.foodtracker.dto;

import java.math.BigDecimal;

public class MicroSummaryDto {
    private BigDecimal fiber;
    private BigDecimal cholesterol;
    private BigDecimal sodium;
    private BigDecimal sugar;
    private BigDecimal calcium;
    private BigDecimal potassium;
    private BigDecimal iron;

    public BigDecimal getFiber() {
        return fiber;
    }

    public void setFiber(BigDecimal fiber) {
        this.fiber = fiber;
    }

    public BigDecimal getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(BigDecimal cholesterol) {
        this.cholesterol = cholesterol;
    }

    public BigDecimal getSodium() {
        return sodium;
    }

    public void setSodium(BigDecimal sodium) {
        this.sodium = sodium;
    }

    public BigDecimal getSugar() {
        return sugar;
    }

    public void setSugar(BigDecimal sugar) {
        this.sugar = sugar;
    }

    public BigDecimal getCalcium() {
        return calcium;
    }

    public void setCalcium(BigDecimal calcium) {
        this.calcium = calcium;
    }

    public BigDecimal getPotassium() {
        return potassium;
    }

    public void setPotassium(BigDecimal potassium) {
        this.potassium = potassium;
    }

    public BigDecimal getIron() {
        return iron;
    }

    public void setIron(BigDecimal iron) {
        this.iron = iron;
    }
}