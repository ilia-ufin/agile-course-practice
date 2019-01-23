package ru.unn.agile.vectordistance.viewmodel;

import java.util.List;

public interface ILogger {
    void log(String message);

    List<String> getLog();
}
