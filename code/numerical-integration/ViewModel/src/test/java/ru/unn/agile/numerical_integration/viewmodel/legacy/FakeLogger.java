package ru.unn.agile.numerical_integration.viewmodel.legacy;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<>();

    @Override
    public void log(final String message) {
        log.add(message);
    }

    @Override
    public List<String> getLog() {
        return log;
    }

    @Override
    public void close() {
        log.clear();
    }
}
