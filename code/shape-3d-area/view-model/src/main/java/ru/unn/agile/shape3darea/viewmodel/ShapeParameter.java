package ru.unn.agile.shape3darea.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ShapeParameter {
    public static final String INITIAL_VALUE = "0";

    private final Class<?> type;
    private final String name;
    private final StringProperty value = new SimpleStringProperty(INITIAL_VALUE);

    public ShapeParameter(final Class<?> type, final String name) {
        this.type = type;
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public StringProperty valueProperty() {
        return value;
    }

}
