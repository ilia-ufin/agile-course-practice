package ru.unn.agile.lengthconverter.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

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

        List<String> log = logger.getLog();
        for (int i = 0; i < log.size(); i++) {
            assertTrue(log.get(i).matches(messages[i]));
        }
    }
}
