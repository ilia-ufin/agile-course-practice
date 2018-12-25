package ru.unn.agile.shapevolume.viewmodel;

import java.util.List;

public interface ILogger {
    void log(String s);
    List<String> getLog();
}
