package ru.unn.agile.modifideStack.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private List<String> logList = new ArrayList<String>();

    @Override
    public void log(final String logMessage) {
        logList.add(logMessage);
    }

    @Override
    public List<String> getLog() {
        return logList;
    }
}
