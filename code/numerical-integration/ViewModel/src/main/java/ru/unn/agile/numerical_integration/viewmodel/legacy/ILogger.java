package ru.unn.agile.numerical_integration.viewmodel.legacy;

import java.util.List;

public interface ILogger extends AutoCloseable {
    void log(String message);
    List<String> getLog();
}
