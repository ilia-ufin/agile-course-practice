package ru.unn.agile.stack.infrastructure;

import ru.unn.agile.stack.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TxtLogger implements ILogger {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final String filename;
    private final BufferedWriter writer;

    public TxtLogger(final String filename) {
        this.filename = filename;
        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        this.writer = logWriter;
    }

    @Override
    public void log(final String message) {
        try {
            writer.write(now() + " > " + message);
            writer.newLine();
            writer.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        ArrayList<String> log = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return log;
    }

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.ENGLISH);
        return sdf.format(calendar.getTime());
    }

}
