package ru.unn.agile.stack.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.junit.Assert.*;

public class TxtLoggerTests {
    private static final String FILE_NAME = "./TxtLoggerTests.log";
    private static final String LOG_PATTERN = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*";
    private static final String TEST_MESSAGE = "Test message";

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
    public void canCreateTxtLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test(expected = Test.None.class)
    public void canCreateTxtLoggerWithEmptyFilename() {
        TxtLogger testLogger = new TxtLogger("");

        assertEquals(0, testLogger.getLog().size());
    }

    @Test(expected = Test.None.class)
    public void canReadTxtLoggerWithEmptyFilename() {
        TxtLogger testLogger = new TxtLogger("");

        assertEquals(0, testLogger.getLog().size());
    }

    @Test(expected = Test.None.class)
    public void canWriteIntoTxtLoggerWithEmptyFilename() {
        TxtLogger testLogger = new TxtLogger("");

        testLogger.log(TEST_MESSAGE);

        assertEquals(0, testLogger.getLog().size());
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(FILE_NAME));
        } catch (FileNotFoundException ex) {
            fail("File " + FILE_NAME + " was not found!");
        }
    }

    @Test
    public void areDateAndTimeContainedInLog() {
        txtLogger.log(TEST_MESSAGE);

        String logMessage = txtLogger.getLog().get(0);
        assertTrue(logMessage.matches(LOG_PATTERN));
    }

    @Test
    public void canWriteOneLogMessage() {
        txtLogger.log(TEST_MESSAGE);

        String logMessage = txtLogger.getLog().get(0);
        assertTrue(logMessage.matches(".*" + logMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessages() {
        txtLogger.log(TEST_MESSAGE.concat(" #1"));
        txtLogger.log(TEST_MESSAGE.concat(" #2"));

        List<String> logMessages = txtLogger.getLog();

        for (int i = 0; i < logMessages.size(); i++) {
            String logMessage = logMessages.get(i);
            assertTrue(logMessage.matches(".*" + logMessage + "$"));
        }
    }
}
