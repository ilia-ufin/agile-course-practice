package ru.unn.agile.calculator.viewmodel;

final class DummyLogger implements ILogger {

    private final StringBuilder log = new StringBuilder();

    @Override
    public void log(final String message) {
        log.append(message);
        log.append('\n');
    }

    @Override
    public String getLog() {
        return log.toString();
    }
}
