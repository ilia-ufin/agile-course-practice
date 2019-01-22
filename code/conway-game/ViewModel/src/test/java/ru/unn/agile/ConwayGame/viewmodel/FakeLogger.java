package ru.unn.agile.ConwayGame.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final ArrayList<String> fakeLogs = new ArrayList<>();

    @Override
    public void log(final String msg) {
        fakeLogs.add(msg);
    }

    @Override
    public List<String> getLog() {
        return fakeLogs;
    }
}
