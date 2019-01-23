package ru.unn.agile.queue.infrastructure;

import ru.unn.agile.queue.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FileLogger implements ILogger {
    private static final String DATE_AND_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final BufferedWriter logWriter;
    private final String logFilename;

    public FileLogger(final String logFilename) {
        this.logFilename = logFilename;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(logFilename));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.logWriter = logWriter;
    }

    @Override
    public void log(final String message) {
        try {
            logWriter.write(now() + " > " + message);
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        ArrayList<String> log = new ArrayList<String>();
        try {
            BufferedReader logReader = new BufferedReader(new FileReader(logFilename));

            String messageLine = logReader.readLine();
            while (messageLine != null) {
                log.add(messageLine);
                messageLine = logReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_AND_TIME_FORMAT,
                Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }
}
