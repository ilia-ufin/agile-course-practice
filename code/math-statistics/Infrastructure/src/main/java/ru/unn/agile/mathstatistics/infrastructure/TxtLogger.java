package ru.unn.agile.mathstatistics.infrastructure;

import ru.unn.agile.mathstatistics.viewmodel.ILogger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Locale;
import java.io.BufferedWriter;
import java.util.Calendar;

public class TxtLogger implements ILogger {
    private final BufferedWriter writer;
    private final String fileName;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static String getActualTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return dateFormat.format(cal.getTime());
    }

    public TxtLogger(final String fileName) {
        this.fileName = fileName;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        writer = logWriter;
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufferedReader;
        ArrayList<String> logList = new ArrayList<String>();
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));

            String logLine = bufferedReader.readLine();

            while (logLine != null) {
                logList.add(logLine);
                logLine = bufferedReader.readLine();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return logList;
    }

    @Override
    public void log(final String str) {
        try {
            writer.write(getActualTime() + " >> " + str);
            writer.newLine();

            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
