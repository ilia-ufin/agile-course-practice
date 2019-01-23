package ru.unn.agile.polynomial.viewmodel;

import java.util.List;

public interface ILogger {
    List<String> getListLog();
    void log(String s);
}

