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
                logger.log(LogMessages.PARAMETER_WAS_CHANGED + name
                        + ": from " + oldValue + " to " + newValue);
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
}
