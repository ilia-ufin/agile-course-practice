package ru.unn.agile.infixtopostfixnotationconverter.infrastructure;

import ru.unn.agile.infixtopostfixnotationconverter.viewmodel.ILogger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FileLogger implements ILogger {
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    private List<String> messages = new ArrayList<>();
    private BufferedWriter stream;

    public FileLogger(final String filePath) throws IOException {
        stream = Files.newBufferedWriter(
                FileSystems.getDefault().getPath(filePath));
    }

    public void log(final String message) {
        try {
            final String formattedMessage = formatMessage(message);
            messages.add(formattedMessage);
            stream.append(formattedMessage);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getMessages() {
        return messages;
    }

    public void close() {
        try {
            messages.clear();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return dateFormat.format(calendar.getTime());
    }

    private String formatMessage(final String message) {
        return now() + ": " + message;
    }
}
