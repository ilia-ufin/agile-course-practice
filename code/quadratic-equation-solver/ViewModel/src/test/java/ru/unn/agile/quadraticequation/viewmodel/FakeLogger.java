package ru.unn.agile.quadraticequation.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> logList = new ArrayList<String>();

    @Override
    public List<String> getLog() {
        return logList;
    }

    @Override
    public void log(final String s) {
        logList.add(s);
    }
}
