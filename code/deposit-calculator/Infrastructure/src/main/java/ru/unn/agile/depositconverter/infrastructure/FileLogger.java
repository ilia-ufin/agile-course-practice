package ru.unn.agile.depositconverter.infrastructure;

import ru.unn.agile.depositconverter.viewmodel.ILogger;

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
    private static final String DATE_FORMAT_PATTERN = "dd-MM-yyyy HH:mm:ss";
    private static final String ERROR_FILE_MESSAGE = "Error file ";
    private final BufferedWriter writer;
    private final String filename;

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.ENGLISH);

        return simpleDateFormat.format(calendar.getTime());
    }

    public FileLogger(final String filename) {
        this.filename = filename;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }

    @Override
    public void log(final String str) {
        try {
            writer.write(" | " + now() + " | " + str);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            throw new IllegalArgumentException(ERROR_FILE_MESSAGE, e);
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufferedReader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            String line = bufferedReader.readLine();

            while (line != null) {
                log.add(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(ERROR_FILE_MESSAGE, e);
        }

        return log;
    }

}
