package ru.unn.agile.segment2d.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final List<String> log = new ArrayList<>();

    @Override
    public void log(final String msg) {
        log.add(msg);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
