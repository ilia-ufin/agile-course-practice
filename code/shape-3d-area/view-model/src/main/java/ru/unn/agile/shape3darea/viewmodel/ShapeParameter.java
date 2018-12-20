package ru.unn.agile.shape3darea.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class ShapeParameter {
    public static final String INITIAL_VALUE = "0";

    private final Class<?> type;
    private final String name;
    private final StringProperty value = new SimpleStringProperty(INITIAL_VALUE);

    private ILogger logger;

    public ShapeParameter(final Class<?> type, final String name) {
        this(type, name, new DummyLogger());
    }

    public ShapeParameter(final Class<?> type, final String name, final ILogger logger) {
        this.type = type;
        this.name = name;
        this.logger = logger;
        value.addListener((observable, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                this.logger.log(String.format(
                        LogMessages.PARAMETER_WAS_CHANGED, name, oldValue, newValue));
            }
        });
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

    public List<String> getLog() {
        return logger.getLog();
    }

    public void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }
}
