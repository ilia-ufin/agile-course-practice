package ru.unn.agile.mathstatistics.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private List<String> log = new ArrayList<String>();

    @Override
    public void log(final String mes) {
        log.add(mes);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
