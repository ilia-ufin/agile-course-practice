package ru.unn.agile.depositconverter.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<>();

    @Override
    public void log(final String strValue) {
        log.add(strValue);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
