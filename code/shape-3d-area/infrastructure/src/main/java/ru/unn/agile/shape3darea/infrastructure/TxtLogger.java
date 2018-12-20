package ru.unn.agile.shape3darea.infrastructure;

import javafx.collections.ObservableList;
import ru.unn.agile.shape3darea.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static javafx.collections.FXCollections.observableArrayList;

public class TxtLogger implements ILogger {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private BufferedWriter writer = null;
    private String fileName;
    private ObservableList<String> log = observableArrayList();

    public TxtLogger(final String fileName) {
        this.fileName = fileName;
        try {
            FileWriter file = new FileWriter(fileName);
            writer = new BufferedWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(final String message) {
        try {
            if (writer != null) {
                writer.write(getCurrentTime() + " > " + message);
                writer.newLine();
                writer.flush();
                updateLogs();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateLogs() {
        ArrayList<String> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.setAll(result);
    }

    @Override
    public ObservableList<String> logProperty() {
        return log;
    }

    @Override
    public List<String> getLog() {
        return new ArrayList<>(log);
    }

    private static String getCurrentTime() {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        final Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }
}
