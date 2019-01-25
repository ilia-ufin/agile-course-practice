package ru.unn.agile.modifideStack.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private List<String> logListFake = new ArrayList<String>();

    @Override
    public void log(final String logMessageFake) {
        logListFake.add(logMessageFake);
    }

    @Override
    public List<String> getLog() {
        return logListFake;
    }
}
