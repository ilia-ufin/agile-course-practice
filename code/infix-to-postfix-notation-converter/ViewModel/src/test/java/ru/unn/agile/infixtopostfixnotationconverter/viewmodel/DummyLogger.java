package ru.unn.agile.infixtopostfixnotationconverter.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class DummyLogger implements ILogger {
    private List<String> logLines = new ArrayList<>();

    public void log(final String message) {
        logLines.add(message);
    }

    public List<String> getMessages() {
        return logLines;
    }

    public void close() {
        logLines.clear();
    }
}
