package ru.unn.agile.ArraySorter.infrastructure;

import ru.unn.agile.ArraySorter.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TextLogger implements ILogger {
    private final BufferedWriter writer;
    private final String filepath;
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public TextLogger(final String filepath) {
        this.filepath = filepath;
        BufferedWriter logWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(filepath);
            logWriter = new BufferedWriter(fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }

    private static String getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    @Override
    public void log(final String messageToLog) {
        try {
            writer.write(getCurrentDateTime() + " > " + messageToLog);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        ArrayList<String> logList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String stringLine = bufferedReader.readLine();
            while (stringLine != null) {
                logList.add(stringLine);
                stringLine = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return logList;
    }
}
