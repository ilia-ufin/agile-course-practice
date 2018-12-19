package ru.unn.agile.calculator.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;

final class DummyLogger implements ILogger {

    private final StringProperty log = new SimpleStringProperty();

    @Override
    public void log(String message) {
        log.set(log.get() + message + '\n');
    }

    @Override
    public ObservableStringValue getLog() {
        return log;
    }
}
