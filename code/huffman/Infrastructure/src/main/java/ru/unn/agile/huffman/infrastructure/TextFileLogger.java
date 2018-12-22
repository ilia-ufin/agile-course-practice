package ru.unn.agile.huffman.infrastructure;

import ru.unn.agile.huffman.viewmodel.ILogger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TextFileLogger implements ILogger {
    public static final String STANDARD_LOG_FILE_NAME = "logFile.log";

    private final String fileName;
    private final BufferedWriter writer;

    public TextFileLogger(final String fileName) {
        this.fileName = fileName;

        BufferedWriter newLogWriter = null;
        try {
            newLogWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        writer = newLogWriter;
    }


    @Override
    public List<String> getLog() {
        ArrayList<String> log = new ArrayList<String>();
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();

            while (line != null) {
                log.add(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }

    @Override
    public void log(final String s) {
        try {
            String mesage = getTime() + " > " + s + "\n";
            writer.write(mesage);
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
