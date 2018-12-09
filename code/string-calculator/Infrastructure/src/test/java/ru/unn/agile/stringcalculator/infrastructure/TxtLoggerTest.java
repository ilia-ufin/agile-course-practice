package ru.unn.agile.stringcalculator.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TxtLoggerTest {
    private static final String FILENAME = "./TxtLoggerTest.log";
    private static final String REGEX_FORMAT_DT =
            "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"; //"yyyy-MM-dd HH:mm:ss"
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
        } catch (FileNotFoundException e) {
            fail("File " + FILENAME + " wasn't found!");
        }
    }

    @Test(expected = Test.None.class)
    public void noThrowExceptionWhenCreateTxtLoggerWithIncorrectPathToFile() {
        TxtLogger logger = new TxtLogger("");
    }

    @Test(expected = Test.None.class)
    public void noThrowExceptionWhenRecordingToTxtLoggerWithIncorrectPathToFile() {
        TxtLogger logger = new TxtLogger("");

        logger.log("message");
    }

    @Test(expected = Test.None.class)
    public void noThrowExceptionWhenGetLogFromTxtLoggerWithIncorrectPathToFile() {
        TxtLogger logger = new TxtLogger("");
        logger.log("message");

        logger.getLog();
    }

    @Test
    public void checkDateAndTimeFormat() {
        txtLogger.log("Logging string");

        String logString = txtLogger.getLog().get(0);
        assertTrue(logString.matches(REGEX_FORMAT_DT + ".*"));
    }

    @Test
    public void canWriteLogMessage() {
        String logMessage = "Logging message";

        txtLogger.log(logMessage);

        String logString = txtLogger.getLog().get(0);
        assertTrue(logString.matches(REGEX_FORMAT_DT + " > " + logMessage));
    }

    @Test
    public void checkCountStingMessagesInLog() {
        String[] logMessages = {"Logging message one(1)",
                "Logging message two(2)",
                "Logging message three(3)"
        };

        fillLog(logMessages);

        assertEquals(logMessages.length, txtLogger.getLog().size());
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] logMessages = {"Logging message one 1",
                "Logging message two 2",
                "Logging message three 3"
        };

        fillLog(logMessages);

        List<String> log = txtLogger.getLog();
        for (int i = 0; i < log.size(); ++i) {
            assertTrue(log.get(i).matches(REGEX_FORMAT_DT + " > " + logMessages[i]));
        }
    }

    private void fillLog(final String[] logMessages) {
        for (String it : logMessages) {
            txtLogger.log(it);
        }
    }

}

