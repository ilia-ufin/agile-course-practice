package ru.unn.agile.triangle.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLog implements ILogger {
    private final List<String> logger = new ArrayList<>();

    @Override
    public void log(final String s) {
        logger.add(s);
    }

    @Override
    public List<String> getLogger() {
        return logger;
    }
}


