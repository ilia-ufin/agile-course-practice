package ru.unn.agile.primenumber.infrastructure;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class TextLogger implements Logger {

    private BufferedWriter writer;
    private String fileName;

    private void setLoggerFile(final String fileName) throws IOException {
        this.fileName = fileName;
        writer = new BufferedWriter(new FileWriter(fileName));
    }

    @Override
    public void log(final String s) {
        try {
            writer.write(s);
            writer.newLine();
            writer.flush();
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getLogs() {
        try {
            return Files.readAllLines(new File(fileName).toPath(), Charset.defaultCharset());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static class TextLoggerBuilder {

        private TextLogger logger;
        private String fileName;

        public TextLoggerBuilder() {
            logger = new TextLogger();
        }

        public TextLoggerBuilder setFileName(final String fileName) throws IOException {
            this.fileName = fileName;
            logger.setLoggerFile(fileName);
            return this;
        }

        public TextLogger build() {
            if (fileName == null) {
                throw new IllegalStateException("File name should be set");
            }
            return logger;
        }
    }
}
