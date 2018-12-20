package ru.unn.agile.calculator.infrastructure;

import ru.unn.agile.calculator.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

public class FileLogger implements ILogger {
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String ERROR_CAN_NOT_OPEN_FILE = "Can't open file %s";

    private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);

    private final String fileName;
    private BufferedWriter fileWriter;

    public FileLogger(final String fileName) {
        this.fileName = fileName;

        try {
            fileWriter = new BufferedWriter(new FileWriter(fileName));
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println(String.format(ERROR_CAN_NOT_OPEN_FILE, fileName));
            e.printStackTrace();
        }
    }

    @Override
    public void log(final String message) {
        try {
            if (fileWriter != null) {
                fileWriter.write(dateFormat.format(new Date()) + " " + message);
                fileWriter.newLine();
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getLog() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
