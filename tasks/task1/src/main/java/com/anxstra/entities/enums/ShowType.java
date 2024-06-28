package com.anxstra.entities.enums;

public enum ShowType {
    ID("Id"),
    NAME("Name");

    private final String showTypeName;

    ShowType(String showTypeName) {
        this.showTypeName = showTypeName;
    }

    @Override
    public String toString() {
        return showTypeName;
    }
}
