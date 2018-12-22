package ru.unn.agile.numbersinwords.viewmodel;

import java.util.ArrayList;
        import java.util.List;

public class FakeLogger implements ILogger {
    private List<String> log = new ArrayList<>();

    @Override
    public void log(final String s) {
        log.add(s);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
