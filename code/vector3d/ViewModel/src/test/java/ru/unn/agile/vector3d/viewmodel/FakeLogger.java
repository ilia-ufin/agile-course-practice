package ru.unn.agile.vector3d.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final List<String> log = new ArrayList<>();

    @Override
    public void log(final String s) {
        log.add(s);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
