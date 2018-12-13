package ru.unn.agile.numbersinwords.infrastructure;

import ru.unn.agile.numbersinwords.viewmodel.ILogger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TxtLogger implements ILogger {
    private BufferedWriter writeBuffer;
    private List<String> log = new ArrayList<>();
    private static final String ISO_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    public TxtLogger(final String logPath) throws IOException {
        writeBuffer = Files.newBufferedWriter(
                FileSystems.getDefault().getPath(logPath));
    }

    private String getTimestamp() {
        return new SimpleDateFormat(ISO_DATE_PATTERN).format(Calendar.getInstance().getTime());
    }


    public void log(final String message) {
        try {
            log.add(message);
            writeBuffer.append(getTimestamp() + message + "\n");
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
            writeBuffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
