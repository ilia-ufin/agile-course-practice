package ru.unn.agile.modifideStack.infrastructure;

import ru.unn.agile.modifideStack.viewmodel.ILogger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TxtLogger implements ILogger {
    private static final String DATE_TIME_FORMAT_LOG = "yyyy-MM-dd HH:mm:ss";

    private final String fileName;
    private final BufferedWriter writer;

    public TxtLogger(final String fileName) {
        this.fileName = fileName;

        BufferedWriter newLogWriterTxt = null;
        try {
            newLogWriterTxt = new BufferedWriter(new FileWriter(fileName));
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        this.writer = newLogWriterTxt;
    }

    @Override
    public void log(final String message) {
        try {
            String lineLogs = getNowTime() + " > " + message + "\n";
            writer.write(lineLogs);
            writer.flush();
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        ArrayList<String> log = new ArrayList<String>();
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(fileName));
            String newLine = bReader.readLine();
            while (newLine != null) {
                log.add(newLine);
                newLine = bReader.readLine();
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        return log;
    }

    private static String getNowTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_LOG));
    }

}
