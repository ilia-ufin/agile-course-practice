package ru.unn.agile.numbersinwords.infrastructure;

import ru.unn.agile.numbersinwords.viewmodel.ILogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TxtLogger implements ILogger {
    private FileWriter fileWriter;
    private BufferedWriter writeBuffer;
    private List<String> log = new ArrayList<>();
    private static final String ISO_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss ";

    public TxtLogger(final String logPath) throws IOException {
        fileWriter = new FileWriter(logPath);
        writeBuffer = new BufferedWriter(fileWriter);
    }

    private String getTimestamp() {
        return new SimpleDateFormat(ISO_DATE_PATTERN).format(Calendar.getInstance().getTime());
    }


    public void log(final String message) {
        try {
            log.add(message);
            writeBuffer.append(getTimestamp() + message);
            writeBuffer.newLine();
            writeBuffer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getLog() {
        return log;
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }
}
