package ru.unn.agile.LcdDigits.Infrastructure;

import ru.unn.agile.LcdDigits.viewmodel.ILogger;

import java.io.*;
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
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);

        return dateFormat.format(calendar.getTime());
    }

    public TxtLogger(final String file) {
        this.file = file;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer = bufferedWriter;
    }

    @Override
    public void log(final String str) {
        try {
            writer.write(now() + " > " + str);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        ArrayList<String> log = new ArrayList<String>();

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                log.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return log;
    }
}
