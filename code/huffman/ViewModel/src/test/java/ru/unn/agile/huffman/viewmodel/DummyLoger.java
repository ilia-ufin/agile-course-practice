package ru.unn.agile.huffman.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class DummyLoger implements ILogger {
    private final ArrayList<String> logList = new ArrayList<>();

    @Override
    public void log(final String s) {
        logList.add(s);
    }

    @Override
    public List<String> getLog() {
        return logList;
    }

}
