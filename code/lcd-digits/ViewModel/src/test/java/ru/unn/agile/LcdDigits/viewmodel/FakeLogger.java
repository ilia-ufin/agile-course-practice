package ru.unn.agile.LcdDigits.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final ArrayList<String> fakeLog = new ArrayList<>();

    @Override
    public void log(final String str) {
        fakeLog.add(str);
    }

    @Override
    public List<String> getLog() {
        return fakeLog;
    }
}
