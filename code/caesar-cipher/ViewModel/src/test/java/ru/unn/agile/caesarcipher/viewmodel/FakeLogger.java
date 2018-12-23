package ru.unn.agile.caesarcipher.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> logger = new ArrayList<String>();

    @Override
    public void log(final String s) {
        logger.add(s);
    }

    @Override
    public List<String> getLog() {
        return logger;
    }
}
