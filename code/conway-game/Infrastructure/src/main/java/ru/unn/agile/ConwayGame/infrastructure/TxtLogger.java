package ru.unn.agile.ConwayGame.infrastructure;

import ru.unn.agile.ConwayGame.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TxtLogger implements ILogger {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final String file;
    private final BufferedWriter writer;

    private static String getCurrentDate() {
        return new SimpleDateFormat(DATE_FORMAT).format(new Date());
    }

    public TxtLogger(final String file) {
        this.file = file;

        BufferedWriter logBufferWriter = null;

        try {
            logBufferWriter = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer = logBufferWriter;
    }

    @Override
    public void log(final String str) {
        try {
            writer.write(getCurrentDate() + " > " + str);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        FileReader fileReader;
        BufferedReader buffReader;
        ArrayList<String> logList = new ArrayList<>();
        try {
            fileReader = new FileReader(file);
            buffReader = new BufferedReader(fileReader);
            String line = buffReader.readLine();
            while (line != null) {
                logList.add(line);
                line = buffReader.readLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return logList;
    }
}
