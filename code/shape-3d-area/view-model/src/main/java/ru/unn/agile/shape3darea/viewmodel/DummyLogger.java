package ru.unn.agile.shape3darea.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class DummyLogger implements ILogger {
    private static final List<String> DUMMY_LOG = new ArrayList<>();

    public void log(final String message) {

    }

    public List<String> getLog() {
        return DUMMY_LOG;
    }
}
