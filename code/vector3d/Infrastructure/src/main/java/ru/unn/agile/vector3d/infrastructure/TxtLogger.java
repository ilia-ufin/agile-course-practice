package ru.unn.agile.vector3d.infrastructure;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ru.unn.agile.vector3d.viewmodel.ILogger;

public class TxtLogger implements ILogger {
    private static final String TS_FORMAT = "dd.MM.yyyy HH:mm:ss";

    private final String fileName;
    private FileWriter writer;

    public TxtLogger(final String fileName) {
        this.fileName = fileName;

        try {
            writer = new FileWriter(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(final String s) {
        String timeStamp = new SimpleDateFormat(TS_FORMAT).format(new Date());

        try {
            writer.write(timeStamp + " - " + s + "\n");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader reader;
        List<String> log = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.lines().collect(Collectors.toCollection(() -> log));
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return log;
    }
}
