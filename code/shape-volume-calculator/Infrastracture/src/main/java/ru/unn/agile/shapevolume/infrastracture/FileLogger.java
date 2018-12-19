package ru.unn.agile.shapevolume.infrastracture;


import ru.unn.agile.shapevolume.viewmodel.ILogger;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileLogger implements ILogger {
    private BufferedWriter writer;
    private String fileName;

    public FileLogger(final String fileName) {
        this.fileName = fileName;

    }

    @Override
    public void log(final String s) {
        try {
            if (writer == null) {
                writer = new BufferedWriter(new FileWriter(fileName));
            }
            writer.write(s);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
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

        } catch (IOException e) {
            throw new IllegalArgumentException("Something is wrong with file", e);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
}
