package ru.unn.agile.BookStore.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final ArrayList<String> fakeLog = new ArrayList<>();

    @Override
    public void log(final String s) {
        fakeLog.add(s);
    }

    @Override
    public List<String> getLog() {
        return fakeLog;
    }
}
