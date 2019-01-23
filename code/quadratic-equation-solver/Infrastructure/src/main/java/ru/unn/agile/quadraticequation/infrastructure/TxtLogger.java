package ru.unn.agile.quadraticequation.infrastructure;

import ru.unn.agile.quadraticequation.viewmodel.ILogger;

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
    private final BufferedWriter buffWriter;
    private final String fileName;

    public TxtLogger(final String fileName) {
        this.fileName = fileName;
        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.buffWriter = logWriter;
    }

    public void log(final String s) {
        try {
            this.buffWriter.write(now() + " > " + s);
            this.buffWriter.newLine();
            this.buffWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> getLog() {
        ArrayList<String> logList = new ArrayList<String>();
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(fileName));
            String newLine = buffReader.readLine();
            while (newLine != null) {
                logList.add(newLine);
                newLine = buffReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return logList;
    }

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return sdf.format(calendar.getTime());
    }
}
