package ru.unn.agile.intersect.infrastructure;

import java.util.List;

public interface ILogger {
    void log(String s);

    List<String> getLog();
}
