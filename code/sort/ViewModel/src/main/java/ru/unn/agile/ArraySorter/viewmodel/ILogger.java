package ru.unn.agile.ArraySorter.viewmodel;

import java.util.List;

public interface ILogger {
    void log(String messageToLog);

    List<String> getLog();
}
