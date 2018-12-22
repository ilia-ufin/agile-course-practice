package ru.unn.agile.shape3darea.viewmodel;

import javafx.collections.ObservableList;
import java.util.List;

public interface ILogger {
    void log(String message);
    ObservableList<String> logProperty();
    List<String> getLog();
}
