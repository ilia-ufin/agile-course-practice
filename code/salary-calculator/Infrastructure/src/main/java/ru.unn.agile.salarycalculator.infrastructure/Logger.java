package ru.unn.agile.salarycalculator.infrastructure;

import ru.unn.agile.salarycalculator.viewmodel.legacy.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Logger implements ILogger {
    private static final String DATE_FORMAT_NOW = "dd-MM-yyyy HH:mm:ss";
    private final BufferedWriter bufferWriter;
    private final String filename;

    public Logger(final String filename) {
        this.filename = filename;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferWriter = logWriter;
    }

    public String currentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return dateFormat.format(calendar.getTime());
    }


    @Override
    public void log(final String logText) {
        try {
            bufferWriter.write(currentTime() + " --- " + logText);
            bufferWriter.newLine();
            bufferWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufferReader;
        ArrayList<String> logList = new ArrayList<>();
        try {
            bufferReader = new BufferedReader(new FileReader(filename));
            String lineOfLog = bufferReader.readLine();

            while (lineOfLog != null) {
                logList.add(lineOfLog);
                lineOfLog = bufferReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return logList;
    }
}
