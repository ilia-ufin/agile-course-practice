package ru.unn.agile.MyAbstractSet.infrastructure;

import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static org.junit.Assert.*;

public class TxtLoggerTest {
    private static final String FILENAME = "./AbstractSet-TxtLoggerTest.log";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException ex) {
            fail("File " + FILENAME + " wasn't found!");
        }
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertTrue(message.matches(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message1", "Test message2"};

        txtLogger.log(messages[0]);
        txtLogger.log(messages[1]);

        List<String> actualMessages = txtLogger.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertTrue(actualMessages.get(i).matches(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertTrue(message.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
