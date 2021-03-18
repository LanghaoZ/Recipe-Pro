package com.langhao.recipepro.dto;

import java.math.BigDecimal;

public class IngredientDto {

    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureDto unitOfMeasure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UnitOfMeasureDto getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasureDto unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
