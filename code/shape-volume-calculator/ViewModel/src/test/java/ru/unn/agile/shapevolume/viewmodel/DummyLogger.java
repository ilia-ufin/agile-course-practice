package ru.unn.agile.shapevolume.viewmodel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Maria Pronina.
 */
public class DummyLogger implements ILogger {
   private List<String> log = new LinkedList<>();
    @Override
    public void log(String s) {
    log.add(s);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
