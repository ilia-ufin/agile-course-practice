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
    private final String logFile;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return dateFormat.format(cal.getTime());
    }


    @Override
    public void log(final String str) {
        try {
            writer.write(getTime() + ": INFO " + str);
            writer.newLine();

            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public TxtLogger(final String logFile) {
        this.logFile = logFile;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(logFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

        writer = logWriter;
    }

    @Override
    public List<String> getLog() {
        BufferedReader buffer;
        ArrayList<String> logs = new ArrayList<>();
        try {

            FileReader fileReader = new FileReader(logFile);

            buffer = new BufferedReader(fileReader);

            String record = buffer.readLine();

            while (record != null) {
                logs.add(record);
                record = buffer.readLine();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return logs;
    }

}
