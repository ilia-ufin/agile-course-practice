package ru.unn.agile.MyAbstractSet.infrastructure;

import ru.unn.agile.MyAbstractSet.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TxtLogger implements ILogger {
    private final String filename;
    private final BufferedWriter writer;
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    private static String dateTimeNow() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(calendar.getTime());
    }

    public TxtLogger(final String filename) {
        this.filename = filename;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        writer = logWriter;
    }

    @Override
    public void log(final String s) {
        String message = dateTimeNow() + " > " + s;
        try {
            if (writer != null) {
                writer.write(message);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader reader;
        ArrayList<String> logMessage = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                logMessage.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return logMessage;
    }
}
