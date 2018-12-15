package ru.unn.agile.shape3darea.viewmodel;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

public class DummyLogger implements ILogger {
    @Override
    public void log(final String message) { }

    @Override
    public ObservableList<String> logProperty() {
        return observableArrayList();
    }

    @Override
    public List<String> getLog() {
        return new ArrayList<>();
    }
}
