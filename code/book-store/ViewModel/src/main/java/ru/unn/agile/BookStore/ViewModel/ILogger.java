package ru.unn.agile.BookStore.ViewModel;

import java.util.List;

public interface ILogger {
    void log(String s);

    List<String> getLog();
}
