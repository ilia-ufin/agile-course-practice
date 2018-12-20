package ru.unn.agile.calculator.infrastructure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileLoggerTest {
    private static final String LOG_FILE_NAME = "./numeral-systems-calculator-FileLoggerTest.log";

    private static final String TEST_MESSAGE_1 = "test1";
    private static final String TEST_MESSAGE_2 = "test2";
    private static final String DATE_TIME_PATTERN = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";

    @Test()
    public void whenFileNameInvalidThenDoNothing() {
        FileLogger fileLogger = new FileLogger("");

        fileLogger.log(TEST_MESSAGE_1);

        assertEquals("", fileLogger.getLog());
    }

    @Test()
    public void whenNoMessagesThenLogIsEmpty() {
        FileLogger fileLogger = new FileLogger(LOG_FILE_NAME);

        assertEquals("", fileLogger.getLog());
    }

    @Test()
    public void whenLogMessageThenCanGetItWithDate() {
        FileLogger fileLogger = new FileLogger(LOG_FILE_NAME);

        fileLogger.log(TEST_MESSAGE_1);

        String log = fileLogger.getLog();
        assertTrue(log.matches(DATE_TIME_PATTERN + " " + TEST_MESSAGE_1));
    }

    @Test()
    public void whenLogSeveralMessagesThenCanGetThem() {
        FileLogger fileLogger = new FileLogger(LOG_FILE_NAME);

        fileLogger.log(TEST_MESSAGE_1);
        fileLogger.log(TEST_MESSAGE_2);

        String log = fileLogger.getLog();
        assertTrue(log.matches(DATE_TIME_PATTERN + " " + TEST_MESSAGE_1 + "\n"
                + DATE_TIME_PATTERN + " " + TEST_MESSAGE_2));
    }
}
