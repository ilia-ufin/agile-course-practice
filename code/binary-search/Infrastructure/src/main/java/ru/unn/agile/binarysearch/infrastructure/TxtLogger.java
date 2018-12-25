package ru.unn.agile.binarysearch.infrastructure;

import ru.unn.agile.binarysearch.viewmodel.ILogger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TxtLogger implements ILogger {
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private BufferedWriter bufferedWriter;
    private List<String> log = new ArrayList<>();

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }

    public TxtLogger(final String logPath) throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter(logPath));
    }

    public BufferedWriter getWriter() {
        return bufferedWriter;
    }

    public void log(final String message) {
        try {
            log.add(message);
            bufferedWriter.append(now() + message + "\r\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getLog() {
        return log;
    }
}
