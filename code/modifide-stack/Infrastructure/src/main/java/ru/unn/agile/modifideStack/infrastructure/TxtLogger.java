package ru.unn.agile.modifideStack.infrastructure;

import ru.unn.agile.modifideStack.viewmodel.ILogger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TxtLogger implements ILogger {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final String filename;
    private final BufferedWriter writer;

    public TxtLogger(final String filename) {
        this.filename = filename;

        BufferedWriter newLogWriter = null;
        try {
            newLogWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        this.writer = newLogWriter;
    }

    @Override
    public void log(final String message) {
        try {
            String lineLog = getNowTime() + " > " + message + "\n";
            writer.write(lineLog);
            writer.flush();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        ArrayList<String> log = new ArrayList<String>();
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(filename));
            String newLine = bReader.readLine();
            while (newLine != null) {
                log.add(newLine);
                newLine = bReader.readLine();
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        return log;
    }

    private static String getNowTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

}
