package ru.unn.agile.segment2d.infrastructure;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import ru.unn.agile.segment2d.viewmodel.ILogger;

public class TxtLogger implements ILogger {
    private static final String DT_FORMAT = "dd.MM.yyyy HH:mm:ss";
    private final String fileName;
    private BufferedWriter  writer;

    public TxtLogger(final String fileName) {
        this.fileName = fileName;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void log(final String s) {
        String timeMsg = new SimpleDateFormat(DT_FORMAT).format(new Date());
        try {
            if (writer != null) {
                writer.write(timeMsg + " : " + s + "\n");
                writer.flush();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader bufferReader;
        List<String> log = new ArrayList<>();
        try {
            bufferReader = new BufferedReader(new FileReader(fileName));
            String line = bufferReader.readLine();
            while (line != null) {
                log.add(line);
                line = bufferReader.readLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return log;
    }
}
