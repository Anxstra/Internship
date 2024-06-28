package com.anxstra.entities.enums;

public enum Currency {
    BR("Br"),
    EUR("Eur"),
    USD("Usd");

    private final String currencyName;

    Currency(String currencyName) {
        this.currencyName = currencyName;
    }

    @Override
    public String toString() {
        return currencyName;
    }
}
