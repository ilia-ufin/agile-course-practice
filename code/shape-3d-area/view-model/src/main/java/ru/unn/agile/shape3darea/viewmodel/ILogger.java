package ru.unn.agile.shape3darea.viewmodel;

import java.util.List;

public interface ILogger {
    void log(String message);
    List<String> getLog();
}
