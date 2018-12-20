package ru.unn.agile.fibonacciHeap.Infrastructure;

import ru.unn.agile.fibonacciHeap.viewModel.ILogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DummyLogger implements ILogger {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final BufferedWriter writer;
    private final String fileName;

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return dateFormat.format(cal.getTime());
    }

    public DummyLogger(final String fileName) {
        this.fileName = fileName;

        BufferedWriter logWriter;

        try {
            logWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        writer = logWriter;
    }

    @Override
    public List<String> getLog() {
        try {
            return Files.readAllLines(new File(fileName).toPath(), Charset.defaultCharset());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void log(final String str) {
        try {
            writer.write(now() + " >> " + str);
            writer.newLine();

            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
