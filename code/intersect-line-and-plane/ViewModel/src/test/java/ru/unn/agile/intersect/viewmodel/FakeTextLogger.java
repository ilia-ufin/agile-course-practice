package ru.unn.agile.intersect.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeTextLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<String>();

    @Override
    public void writeLog(final String s) {
        log.add(s);
    }

    @Override
    public List<String> readLog() {
        return log;
    }
}
