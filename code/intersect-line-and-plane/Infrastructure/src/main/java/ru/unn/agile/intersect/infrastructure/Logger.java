package ru.unn.agile.intersect.infrastructure;

import ru.unn.agile.intersect.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Logger implements ILogger {
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    private final BufferedWriter logWriter;
    private final String filename;

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }

    public Logger(final String filename) {
        this.filename = filename;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
        this.logWriter = logWriter;
    }

    @Override
    public void writeLog(final String s) {
        try {
            if (logWriter == null) {
                return;
            }
            logWriter.write(now() + ": " + s);
            logWriter.newLine();
            logWriter.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @Override
    public List<String> readLog() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String s = reader.readLine();

            while (s != null) {
                log.add(s);
                s = reader.readLine();
            }
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }

        return log;
    }
}
