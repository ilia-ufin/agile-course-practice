package ru.unn.agile.triangle.infrastructure;

import ru.unn.agile.triangle.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TxtLogger implements ILogger {

    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter writer;
    private final String fileName;

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }

    public TxtLogger(final String filename) {
        this.fileName = filename;

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
        try {
            writer.write(now() + " > " + s);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLogger() {
        BufferedReader reader;
        List<String> logger = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String message = reader.readLine();

            while (message != null && !message.isEmpty()) {
                logger.add(message);
                message = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return logger;
    }

}
