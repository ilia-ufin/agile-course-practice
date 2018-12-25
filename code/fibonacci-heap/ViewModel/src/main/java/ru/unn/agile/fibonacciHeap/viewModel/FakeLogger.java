package ru.unn.agile.fibonacciHeap.viewModel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private List<String> cache = new ArrayList<String>();

    @Override
    public void log(final String message) {
        cache.add(message);
    }

    @Override
    public List<String> getLog() {
        return cache;
    }
}
