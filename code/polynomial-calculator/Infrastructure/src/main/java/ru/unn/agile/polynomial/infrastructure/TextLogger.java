package ru.unn.agile.polynomial.infrastructure;

import ru.unn.agile.polynomial.viewmodel.ILogger;
import java.io.*;
import java.util.*;

public class TextLogger implements ILogger {

    private final String filename;
    private BufferedWriter writer;

    public TextLogger(final String filename) {
        this.filename = filename;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<String> getListLog() {
        BufferedReader bufferedReader;
        ArrayList<String> listLog = new ArrayList<String>();
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            String line = bufferedReader.readLine();
            while (line != null) {
                listLog.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return listLog;
    }

    @Override
    public void log(final String message) {
        try {
            if (writer != null) {
                writer.write(message);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
