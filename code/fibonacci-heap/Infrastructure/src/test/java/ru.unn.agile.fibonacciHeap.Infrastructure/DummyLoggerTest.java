package ru.unn.agile.fibonacciHeap.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class DummyLoggerTest {
    private DummyLogger txtLogger;
    private static final String DATE_TIME_REGEX
            = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} >> .*";
    private static final String FILENAME = "./TextLogger.test.txt";

    @Before
    public void setUp() {
        txtLogger = new DummyLogger(FILENAME);
    }

    @Test
    public void shouldntFailOnEmptyFilename() {
        new DummyLogger("");
    }

    @Test
    public void shouldCreateLogFile() {
        File f = new File(FILENAME);

        assertTrue(f.exists());
    }

    @Test
    public void shouldAddTimeInfoToLog() {
        String message = "info: some data";

        txtLogger.log(message);

        String mess = txtLogger.getLog().get(0);
        assertTrue(mess.matches(DATE_TIME_REGEX));
    }

    @Test
    public void shouldWriteLogMessage() {
        String testMessage = "warn: deprecated usage";

        txtLogger.log(testMessage);
        String mess = txtLogger.getLog().get(0);

        assertTrue(mess.matches(".*" + testMessage + "$"));
    }

    @Test
    public void shouldWriteMultipleMessages() {
        String[] messages = {
            "warn: deprecated usage",
            "info: some data"
        };

        txtLogger.log(messages[0]);
        txtLogger.log(messages[1]);

        List<String> logMessages = txtLogger.getLog();

        assertTrue(logMessages.get(0).matches(".*" + messages[0] + "$"));
        assertTrue(logMessages.get(1).matches(".*" + messages[1] + "$"));
    }
}
