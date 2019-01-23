package ru.unn.agile.shapevolume.viewmodel;

public enum Shape {
    UNKNOWN("Выберите фигуру"),
    CUBE("Куб"),
    REGULAR_POLYGON_PRISM("Правильная призма");

    private final String name;
    Shape(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
