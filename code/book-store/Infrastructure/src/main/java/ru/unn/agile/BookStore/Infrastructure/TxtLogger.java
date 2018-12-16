package ru.unn.agile.BookStore.Infrastructure;

import ru.unn.agile.BookStore.ViewModel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TxtLogger implements ILogger {
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter writer;
    private final String file;

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }

    public TxtLogger(final String filename) {
        this.file = filename;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
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
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        FileReader filereader;
        BufferedReader bufReader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            filereader = new FileReader(file);
            bufReader = new BufferedReader(filereader);
            String line = bufReader.readLine();
            while (line != null) {
                log.add(line);
                line = bufReader.readLine();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return log;
    }

}
