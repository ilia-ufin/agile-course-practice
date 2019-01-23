package ru.unn.agile.primenumber.infrastructure;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerFactory {
    private static final String PATH_LOG_FILE = "build/tmp/logs_";
    private static final String PATH_LOG_FILE_EXTENSION = ".log";
    private static final String STRING_FORMAT = "yyyy_MM_dd_hh_dd_ss_SSS";

    private Logger logger;

    public Logger getLogger() {
        String path = buildPath();
        try {
            if (logger == null) {
                logger = new TextLogger
                        .TextLoggerBuilder()
                        .setFileName(path)
                        .build();
            }
            return logger;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String buildPath() {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(STRING_FORMAT);
        return PATH_LOG_FILE
                + formatter.format(currentDate)
                + PATH_LOG_FILE_EXTENSION;
    }

    public Logger resetLogger() {
       logger = null;
       return getLogger();
    }
}
