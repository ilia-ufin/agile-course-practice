package ru.unn.agile.calculator.viewmodel;

import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;

public interface ILogger {
    void log(final String message);

    ObservableStringValue getLog();
}
