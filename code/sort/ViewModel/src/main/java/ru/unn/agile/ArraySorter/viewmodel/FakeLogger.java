package ru.unn.agile.ArraySorter.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private List<String> log = new ArrayList<>();

    @Override
    public void log(final String messageToLog) {
        log.add(messageToLog);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
