package com.anxstra.entities.enums;

public enum DiscountType {

    ONE("One day"),
    MANY("Many days");

    private final String discountTypeName;

    DiscountType(String discountTypeName) {
        this.discountTypeName = discountTypeName;
    }

    @Override
    public String toString() {
        return discountTypeName;
    }
}
