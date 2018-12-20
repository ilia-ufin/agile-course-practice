package ru.unn.agile.lengthconverter.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TxtLoggerTest {
    private static final String FILE_NAME = "./TxtLoggerTest.log";

    private TxtLogger logger;

    @Before
    public void setUp() {
        logger = new TxtLogger(FILE_NAME);
    }

    @After
    public void tearDown() {
        logger = null;
    }

    @Test
    public void canCreateTxtLogger() {
        assertNotNull(logger);
    }

    @Test
    public void canCreateLogFile() {
        File logFile = new File(FILE_NAME);

        assertTrue(logFile.exists());
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = "Test message";
        logger.log(testMessage);

        String logMessage = logger.getLog().get(0);

        assertTrue(logMessage.matches(testMessage));
    }

    @Test
    public void canWriteTwoLogMessages() {
        String[] messages = {"Test message 1",
                "Test message 2"};

        for (String msg : messages) {
            logger.log(msg);
        }

        List<String> logList = logger.getLog();
        for (int i = 0; i < logList.size(); i++) {
            assertTrue(logList.get(i).matches(messages[i]));
        }
    }

    @Test(expected = Test.None.class)
    public void createLoggerWithEmptyFileName() {
        TxtLogger emptyLogger = new TxtLogger("");

        assertEquals(0, emptyLogger.getLog().size());
    }

    @Test
    public void writeMessageToLogWithIncorrectName() {
        TxtLogger emptyLogger = new TxtLogger("");

        emptyLogger.log("Message");

        assertEquals(0, emptyLogger.getLog().size());
    }

    @Test
    public void readMessageFromLogWithIncorrectName() {
        TxtLogger emptyLogger = new TxtLogger("");

        List<String> log = emptyLogger.getLog();

        assertEquals(0, log.size());
    }
}
