package ru.unn.agile.intersect.viewmodel;

import java.util.List;

public interface ILogger {
    void writeLog(String s);

    List<String> readLog();
}
