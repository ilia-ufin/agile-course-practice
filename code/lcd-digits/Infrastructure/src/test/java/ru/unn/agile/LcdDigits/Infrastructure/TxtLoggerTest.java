package ru.unn.agile.LcdDigits.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;

public class TxtLoggerTest {
    private static final String FILENAME = "./LcdDigits-TxtLoggerTest.log";
    private TxtLogger logger;

    @Before
    public void setUp() {
        logger = new TxtLogger(FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(logger);
    }

    @Test
    public void canWriteDownLogMessage() {
        String testMessage = "Test message";

        logger.log(testMessage);

        String message = logger.getLog().get(0);
        assertTrue(message.matches(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteDownSomeLogMessage() {
        String[] testMsgs = {"Message #1", "Message 2"};

        logger.log(testMsgs[0]);
        logger.log(testMsgs[1]);

        List<String> newMessages = logger.getLog();
        for (int i = 0; i < newMessages.size(); i++) {
            assertTrue(newMessages.get(i).matches(".*" + testMsgs[i] + "$"));
        }
    }

    @Test
    public void doesLogIncludeDateAndTime() {
        String testMessage = "Message";

        logger.log(testMessage);

        String message = logger.getLog().get(0);
        assertTrue(message.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
