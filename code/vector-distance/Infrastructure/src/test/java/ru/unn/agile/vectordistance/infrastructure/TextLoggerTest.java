package ru.unn.agile.vectordistance.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class TextLoggerTest {
    private static final String FILENAME = "./TextLogger.log";
    private static final String TEST_MESSAGE = "Test message";
    private TextLogger logger;

    @Before
    public void setUp() {
        logger = new TextLogger(FILENAME);
    }

    @After
    public void tearDown() {
        logger = null;
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(logger);
    }

    @Test
    public void canLogMessage() {
        logger.log(TEST_MESSAGE);

        String logMessage = logger.getLog().get(0);

        assertTrue(logMessage.matches(".*" + TEST_MESSAGE + "$"));
    }

    @Test
    public void canLogMessages() {
        String[] messages = {TEST_MESSAGE, TEST_MESSAGE, TEST_MESSAGE};
        for (int i = 0; i < messages.length; i++) {
            logger.log(messages[i]);
        }

        List<String> loggedMessages = logger.getLog();

        for (int i = 0; i < loggedMessages.size(); i++) {
            String loggedMessage = loggedMessages.get(i);
            assertTrue(loggedMessage.matches(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void isNotExceptionThrownWhileCreatingLoggerWithEmptyFileName() {
        logger = new TextLogger("");
    }

    @Test
    public void isNotExceptionThrownWhileCreatingLoggerWithIncorrectFileName() {
        logger = new TextLogger("***");
    }

    @Test
    public void isNotExceptionThrownWhenLogByLoggerWithIncorrectFileName() {
        logger = new TextLogger("");

        logger.log(TEST_MESSAGE);
    }

    @Test
    public void isNotExceptionThrownWhenGetLogByLoggerWithIncorrectFileName() {
        logger = new TextLogger("");

        logger.log(TEST_MESSAGE);
        logger.getLog();
    }

}
