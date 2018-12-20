package ru.unn.agile.salarycalculator.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LoggerTests {
    private static final String FILENAME = "./LoggerTests-lab3.log";
    private static Logger myLogger;

    @Before
    public void setUp() {
        myLogger = new Logger(FILENAME);
    }

    @Test
    public void canCreateLoggerClassWithFileName() {
        assertNotNull(myLogger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            fail("File " + FILENAME + " wasn't found!");
        }
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test message";

        myLogger.log(testMessage);

        String message = myLogger.getLog().get(0);
        assertEquals(message, myLogger.currentTime() + " --- " + testMessage);
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message 1", "Test message 2"};

        myLogger.log(messages[0]);
        myLogger.log(messages[1]);

        List<String> actualMessages = myLogger.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertEquals(actualMessages.get(i), myLogger.currentTime() + " --- " + messages[i]);
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        myLogger.log(testMessage);

        String message = myLogger.getLog().get(0);
        assertTrue(message.contains(myLogger.currentTime()));
    }

}
