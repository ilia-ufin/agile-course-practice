package ru.unn.agile.shape3darea.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.List;

public class DummyLogger implements ILogger {
    @Override
    public void log(final String message) { }

    @Override
    public ObservableList<String> logProperty() {
        return FXCollections.emptyObservableList();
    }

    @Override
    public List<String> getLog() {
        return Collections.emptyList();
    }
}
