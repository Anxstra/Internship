package com.anxstra.entities.enums;

public enum Sex {
    MALE("Male"),
    FEMALE("Female"),
    ANY("Any");

    private final String sexName;

    Sex(String sexName) {
        this.sexName = sexName;
    }

    @Override
    public String toString() {
        return sexName;
    }
}
