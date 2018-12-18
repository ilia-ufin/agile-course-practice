package ru.unn.agile.mortgagecalculator.infrastructure;

import ru.unn.agile.mortgagecalculator.viewmodel.legacy.ILogger;

import java.io.*;
import java.util.Locale;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class TxtLogger implements ILogger {
    private final BufferedWriter writer;
    private final BufferedReader reader;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return dateFormat.format(cal.getTime());
    }

    @Override
    public void log(final String str) {
        if (writer == null) {
            return;
        }
        try {
            writer.write(getTime() + ": INFO " + str);
            writer.newLine();

            writer.flush();
        } catch (IOException e) {
            return;
        }
    }

    public TxtLogger(final String logFile) {
        BufferedReader logReader = null;
        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(logFile));
            FileReader fileReader = new FileReader(logFile);
            logReader = new BufferedReader(fileReader);

        } catch (IOException e) {
            e.printStackTrace();
        }

        writer = logWriter;
        reader = logReader;

    }

    @Override
    public List<String> getLog() {
        ArrayList<String> logs = new ArrayList<>();
        if (writer == null) {
            return logs;
        }
        try {
            String record = reader.readLine();

            while (record != null) {
                logs.add(record);
                record = reader.readLine();
            }

        } catch (IOException e) {
            return logs;
        }

        return logs;
    }

}
