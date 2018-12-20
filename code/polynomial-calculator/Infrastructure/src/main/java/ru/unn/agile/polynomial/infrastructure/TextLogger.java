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
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @Override
    public List<String> getListLog() {
        ArrayList<String> log = new ArrayList<String>();
        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(filename));
            String line = bufferReader.readLine();
            while (line != null) {
                log.add(line);
                line = bufferReader.readLine();
            }
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
        return log;

    }

    @Override
    public void log(final String message) {

    }


}
