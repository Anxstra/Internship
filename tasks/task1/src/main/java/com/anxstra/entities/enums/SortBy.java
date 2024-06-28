package com.anxstra.entities.enums;

public enum SortBy {
    NAME("Name"),
    DEBT("Debt"),
    AGE("Age");

    private final String sortByName;

    SortBy(String sortByName) {
        this.sortByName = sortByName;
    }

    @Override
    public String toString() {
        return sortByName;
    }
}
