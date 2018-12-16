package ru.unn.agile.matrix.infrastructure;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import ru.unn.agile.matrix.viewmodel.ILogger;

public class TxtLogger implements ILogger {
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter writer;
    private final String filename;

    public TxtLogger(final String filename) throws IOException {
        this.filename = filename;
        writer = new BufferedWriter(new FileWriter(filename));
    }

    @Override
    public void log(final String s) {
        try {
            writer.write(now() + " >> " + s);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return log;
    }

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }
}
