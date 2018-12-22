package ru.unn.agile.romannumberconverter.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> logArray = new ArrayList<String>();

    @Override
    public void log(final String message) {
        logArray.add(message);
    }

    @Override
    public List<String> getLog() {
        return logArray;
    }
}
