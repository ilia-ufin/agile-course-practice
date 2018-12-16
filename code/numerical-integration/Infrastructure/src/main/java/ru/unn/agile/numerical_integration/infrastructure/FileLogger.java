package ru.unn.agile.numerical_integration.infrastructure;

import ru.unn.agile.numerical_integration.viewmodel.legacy.ILogger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FileLogger implements ILogger {
    private BufferedWriter writeBuffer;
    private List<String> log = new ArrayList<>();
    private static final String DATE_FORMAT_PATTERN = "yyyy/MM/dd_HH:mm:ss";

    public FileLogger(final String logPath) throws IOException {
        writeBuffer = Files.newBufferedWriter(
                FileSystems.getDefault().getPath(logPath));
    }

    private String getCurrentTimeStamp() {
        return new SimpleDateFormat(DATE_FORMAT_PATTERN).format(Calendar.getInstance().getTime());
    }

    public void log(final String message) {
        try {
            final String logMessage = getCurrentTimeStamp() + message;
            log.add(logMessage);
            writeBuffer.append(logMessage);
            writeBuffer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getLog() {
        return log;
    }

    public void close() {
        try {
            log.clear();
            writeBuffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
