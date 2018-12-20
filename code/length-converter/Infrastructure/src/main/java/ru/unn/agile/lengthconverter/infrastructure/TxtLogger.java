package ru.unn.agile.lengthconverter.infrastructure;

import ru.unn.agile.lengthconverter.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtLogger implements ILogger {
    private final String fileName;
    private FileWriter fileWriter;

    public TxtLogger(final String fileName) {
        this.fileName = fileName;

        try {
            fileWriter = new FileWriter(fileName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void log(final String str) {
        try {
            fileWriter.write(str + "\n");
            fileWriter.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<String> getLog() {
        List<String> log = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line = reader.readLine();
            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return log;
    }
}
