package ru.unn.agile.segment2d.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class TxtLoggerTest {
    private static final String FILE_NAME = "./TxtLoggerTest.log";
    private static final String DT_LOG_LINE = "^\\d{2}.\\d{2}.\\d{4} \\d{2}:\\d{2}:\\d{2} : ";
    private static final String TEST_MESSAGE = "This is a test message";

    private TxtLogger txtLogger;

    @Before
    public void initTxtLogger() {
        txtLogger = new TxtLogger(FILE_NAME);
    }

    @After
    public void tearDown() {
        txtLogger = null;
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canWriteLogMessage() {
        txtLogger.log(TEST_MESSAGE);

        String logMessage = txtLogger.getLog().get(0);
        assertTrue(logMessage.matches(DT_LOG_LINE + TEST_MESSAGE));
    }

    @Test
    public void canWriteSeveralMessages() {
        String[] messages = {TEST_MESSAGE + "0", TEST_MESSAGE + "1", TEST_MESSAGE + "2"};

        for (String msg : messages) {
            txtLogger.log(msg);
        }

        List<String> fullLog = txtLogger.getLog();
        for (int i = 0; i < fullLog.size(); i++) {
            assertTrue(fullLog.get(i).matches(DT_LOG_LINE + messages[i]));
        }
    }

    @Test
    public void noThrowsWhenCreatingTxtLoggerWithIncorrectFileName() {
        TxtLogger emptyLogger = new TxtLogger("");

        assertEquals(0, emptyLogger.getLog().size());
    }

    @Test
    public void noThrowsWhenWritingToTxtLoggerWithIncorrectFileName() {
        TxtLogger emptyLogger = new TxtLogger("");

        emptyLogger.log(TEST_MESSAGE);

        assertEquals(0, emptyLogger.getLog().size());
    }

    @Test
    public void noThrowsWhenReadingFromTxtLoggerWithIncorrectFileName() {
        TxtLogger emptyLogger = new TxtLogger("");

        List<String> log = emptyLogger.getLog();

        assertEquals(0, log.size());
    }
}
