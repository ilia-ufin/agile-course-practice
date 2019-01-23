package ru.unn.agile.fibonacciHeap.viewModel;

import java.util.List;

public interface ILogger {
    void log(String s);

    List<String> getLog();
}
