package ru.unn.agile.modifideStack.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.junit.Assert.*;

public class TxtLoggerTests {
    private static final String FILE_NAME = "./TxtLoggerTests.log";
    private static final String LOG_PATTERN = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*";
    private static final String TEST_MESSAGE = "Tes test";

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
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test(expected = Test.None.class)
    public void canCreateLogWithEmptyFilename() {
        TxtLogger testLogger = new TxtLogger("");

        assertEquals(0, testLogger.getLog().size());
    }

    @Test(expected = Test.None.class)
    public void canReadLogWithEmptyFilename() {
        TxtLogger testLogger = new TxtLogger("");

        assertEquals(0, testLogger.getLog().size());
    }

    @Test(expected = Test.None.class)
    public void canWriteIntoLogWithEmptyFilename() {
        TxtLogger testTxtLogger = new TxtLogger("");

        testTxtLogger.log(TEST_MESSAGE);

        assertEquals(0, testTxtLogger.getLog().size());
    }

    @Test
    public void canCreateFileLogOnDisk() {
        try {
            new BufferedReader(new FileReader(FILE_NAME));
        } catch (FileNotFoundException ex) {
            fail("File " + FILE_NAME + " was not found on disk!");
        }
    }

    @Test
    public void areDateAndTime() {
        txtLogger.log(TEST_MESSAGE);

        String log = txtLogger.getLog().get(0);
        assertTrue(log.matches(LOG_PATTERN));
    }

    @Test
    public void canWriteLogMessage() {
        txtLogger.log(TEST_MESSAGE);

        String message = txtLogger.getLog().get(0);
        assertTrue(message.matches(".*" + message + "$"));
    }

    @Test
    public void canWriteTwoLogMessages() {
        txtLogger.log(TEST_MESSAGE.concat(" #1"));
        txtLogger.log(TEST_MESSAGE.concat(" #2"));

        List<String> messages = txtLogger.getLog();

        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            assertTrue(message.matches(".*" + message + "$"));
        }
    }
}
