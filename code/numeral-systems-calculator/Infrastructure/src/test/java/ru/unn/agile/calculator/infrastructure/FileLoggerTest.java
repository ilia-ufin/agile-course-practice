package ru.unn.agile.calculator.infrastructure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileLoggerTest {
    private static final String LOG_FILE_NAME = "./numeral-systems-calculator-FileLoggerTest.log";

    @Test(expected = IllegalStateException.class)
    public void whenFileNameInvalidThenThrowException() {
        FileLogger fileLogger = new FileLogger("");

        fileLogger.log("test");
    }

    @Test()
    public void whenNoMessagesThenLogIsEmpty() {
        FileLogger fileLogger = new FileLogger(LOG_FILE_NAME);

        assertEquals("", fileLogger.getLog());
    }

    @Test()
    public void whenLogMessageThenCanGetItWithDate() {
        FileLogger fileLogger = new FileLogger(LOG_FILE_NAME);

        fileLogger.log("test");

        String log = fileLogger.getLog();
        assertTrue(log.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} test"));
    }

    @Test()
    public void whenLogSeveralMessagesThenCanGetThem() {
        FileLogger fileLogger = new FileLogger(LOG_FILE_NAME);

        fileLogger.log("test1");
        fileLogger.log("test2");

        String log = fileLogger.getLog();
        assertTrue(log.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} test1\n"
                + "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} test2"));
    }
}