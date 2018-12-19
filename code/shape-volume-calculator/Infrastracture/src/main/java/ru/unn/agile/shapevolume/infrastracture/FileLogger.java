package ru.unn.agile.shapevolume.infrastracture;


import ru.unn.agile.shapevolume.viewmodel.ILogger;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileLogger implements ILogger {
    private BufferedWriter writer;
    private String fileName;

    public FileLogger(final String fileName) {
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            this.fileName = fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(final String s) {
        try {
            writer.write(s);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            throw new IllegalArgumentException("Something is wrong with file", e);
        }
    }

    @Override
    public List<String> getLog() {
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> log = new LinkedList<>();
            String currentLine = br.readLine();
            while (currentLine != null) {
                log.add(currentLine);
                currentLine = br.readLine();
            }
            return log;

        } catch (Exception e) {
            throw new IllegalArgumentException("Something is wrong with file", e);
        }
    }
}
