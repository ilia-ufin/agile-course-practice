package ru.unn.agile.mortgagecalculator.viewmodel.legacy;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<>();

    @Override
    public void log(final String str) {
        log.add(str);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
