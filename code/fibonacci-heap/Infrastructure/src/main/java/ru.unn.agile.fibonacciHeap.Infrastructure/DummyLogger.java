package ru.unn.agile.fibonacciHeap.Infrastructure;

import ru.unn.agile.fibonacciHeap.viewModel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DummyLogger implements ILogger {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter writer;
    private final String fileName;

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return dateFormat.format(cal.getTime());
    }

    public DummyLogger(final String fileName) {
        this.fileName = fileName;

        BufferedWriter logWriter = null;

        try {
            logWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        writer = logWriter;
    }

    @Override
    public List<String> getLog() {
        BufferedReader reader;
        ArrayList<String> logList = new ArrayList<String>();

        try {
            FileReader r = new FileReader(fileName);
            reader = new BufferedReader(r);
            String line = reader.readLine();

            if (line == null) {
                return logList;
            }

            do {
                logList.add(line);
                line = reader.readLine();
            } while (line != null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return logList;
    }

    @Override
    public void log(final String str) {
        try {
            writer.write(now() + " >> " + str);
            writer.newLine();

            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
