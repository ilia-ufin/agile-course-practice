package ru.unn.agile.ConwayGame.viewmodel;

import java.util.List;

public interface ILogger {
    void log(String msg);

    List<String> getLog();
}
