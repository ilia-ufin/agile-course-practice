package ru.unn.agile.shapevolume.infrastracture;


import ru.unn.agile.shapevolume.viewmodel.ILogger;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileLogger implements ILogger {
    private BufferedWriter writer;
    private String fileName;

    public FileLogger(String fileName) {

        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            this.fileName = fileName;
        } catch (IOException e) {
            throw new IllegalArgumentException("Something is wrong with file",e);
        }


    }

    @Override
    public void log(String s) {
        try {
            writer.write(s);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<String> getLog() {
        File file = new File(fileName);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> log = new LinkedList<>();
            String currentLine = br.readLine();
            while(currentLine != null) {
                log.add(currentLine);
                currentLine = br.readLine();
            }
            return log;

        } catch (IOException e) {
            throw new IllegalArgumentException("Something is wrong with file",e);
        }
    }
}
