package ru.unn.agile.salarycalculator.viewmodel.legacy;

import java.util.ArrayList;
import java.util.List;

public class MyFakeLogger implements ILogger {
    private ArrayList<String> logList = new ArrayList<>();

    @Override
    public void log(final String logText) {
        logList.add(logText);
    }

    @Override
    public List<String> getLog() {
        return logList;
    }
}
