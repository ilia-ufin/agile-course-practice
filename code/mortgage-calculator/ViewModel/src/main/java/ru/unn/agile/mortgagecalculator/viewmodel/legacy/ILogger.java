package ru.unn.agile.mortgagecalculator.viewmodel.legacy;

import java.util.List;

public interface ILogger {
    void log(String s);

    List<String> getLog();
}
