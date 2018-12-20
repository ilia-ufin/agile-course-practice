package ru.unn.agile.polynomial.viewmodel;

import java.util.List;
import java.util.ArrayList;

public class FakeLogger  implements ILogger {
    private final List<String> log = new ArrayList<>();

    @Override
    public List<String> getListLog() {
        return log;
    }

    @Override
    public void log(final String s) {
        log.add(s);
    }
}
