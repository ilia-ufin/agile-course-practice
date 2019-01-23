package ru.unn.agile.romannumberconverter.infrastructure;

import ru.unn.agile.romannumberconverter.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TxtLogger implements ILogger {
    private static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";
    private final String fileName;
    private final BufferedWriter bufferWriter;

    private static String getCurrentDateTime() {
        return new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date());
    }

    public TxtLogger(final String fileName) {
        this.fileName = fileName;
        BufferedWriter logBufferWriter = null;
        try {
            logBufferWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferWriter = logBufferWriter;
    }

    @Override
    public void log(final String message) {
        try {
            bufferWriter.write(getCurrentDateTime() + " -> " + message);
            bufferWriter.newLine();
            bufferWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufferReader;
        List<String> logList = new ArrayList<String>();
        try {
            bufferReader = new BufferedReader(new FileReader(fileName));
            String line = bufferReader.readLine();
            while (line != null) {
                logList.add(line);
                line = bufferReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logList;
    }
}
