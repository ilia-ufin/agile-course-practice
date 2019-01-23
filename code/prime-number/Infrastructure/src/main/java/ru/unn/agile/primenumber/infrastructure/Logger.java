package ru.unn.agile.primenumber.infrastructure;

import java.util.List;

public interface Logger {
    void log(String s);

    List<String> getLogs();
}
