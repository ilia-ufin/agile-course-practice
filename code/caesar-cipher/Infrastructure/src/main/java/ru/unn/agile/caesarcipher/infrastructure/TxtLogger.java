package ru.unn.agile.caesarcipher.infrastructure;

import ru.unn.agile.caesarcipher.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TxtLogger implements ILogger {
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter writer;
    private final String filename;

    private static String getTimes() {
        return new SimpleDateFormat(DATE_PATTERN).format(Calendar.getInstance().getTime());
    }

    public TxtLogger(final String filename) {
        this.filename = filename;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }

    public void log(final String message) {
        try {
            writer.write(getTimes() + message);
            writer.flush();
            writer.newLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> getLog() {
        BufferedReader read;
        ArrayList<String> logger = new ArrayList<String>();
        try {
            read = new BufferedReader(new FileReader(filename));
            String lines = read.readLine();

            while (lines != null) {
                logger.add(lines);
                lines = read.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return logger;
    }

}
