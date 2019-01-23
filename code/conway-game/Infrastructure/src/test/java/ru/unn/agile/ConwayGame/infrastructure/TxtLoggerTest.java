package ru.unn.agile.ConwayGame.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class TxtLoggerTest {
    private static final String FILE_NAME = "./ConwayGame-TextLogger.log";
    private static final String DATE_TIME_LOG =
            "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*";
    private static final String TEST_MESSAGE = "Some text";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(FILE_NAME);
    }

    @After
    public void tearDown() {
        txtLogger = null;
    }

    @Test
    public void canCreateLogFileWithCorrectFileName() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canCreateLoggerWithCorrectFileName() {
        File file = new File(FILE_NAME);
        assertTrue(file.exists());
    }

    @Test
    public void canWriteLogMessage() {
        txtLogger.log(TEST_MESSAGE);

        assertTrue(txtLogger.getLog().get(0).matches(DATE_TIME_LOG + TEST_MESSAGE));
    }

    @Test
    public void canWriteSeveralLogMessages() {
        String[] messages = {TEST_MESSAGE + " 1", TEST_MESSAGE + " 2"};

        txtLogger.log(messages[0]);
        txtLogger.log(messages[1]);

        List<String> testMessages = txtLogger.getLog();
        for (int i = 0; i < testMessages.size(); i++) {
            assertTrue(testMessages.get(i).matches(DATE_TIME_LOG + messages[i]));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        txtLogger.log(TEST_MESSAGE);

        List<String> logs = txtLogger.getLog();
        String message = logs.get(0);
        assertTrue(message.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
