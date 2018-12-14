package ru.unn.agile.shape3darea.viewmodel;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

public class FakeLogger implements ILogger {
    private final ObservableList<String> log = observableArrayList();

    @Override
    public void log(final String message) {
        log.add(message);
    }

    @Override
    public ObservableList<String> logProperty() {
        return log;
    }

    @Override
    public List<String> getLog() {
        return new ArrayList<>(log);
    }
}

