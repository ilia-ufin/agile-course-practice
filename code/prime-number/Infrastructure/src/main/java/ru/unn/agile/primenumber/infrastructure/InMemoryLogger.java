package ru.unn.agile.primenumber.infrastructure;

import java.util.ArrayList;
import java.util.List;

public class InMemoryLogger implements Logger {

    private final List<String> logs = new ArrayList<>();

    @Override
    public void log(final String s) {
        logs.add(s);
    }

    @Override
    public List<String> getLogs() {
        return logs;
    }
}
