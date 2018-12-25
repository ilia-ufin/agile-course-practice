package ru.unn.agile.BookStore.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;

public class TxtLoggerTest {
    private static final String FILENAME = "./BookStore-TxtLoggerTest.log";
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
    public void canWriteDownLogMessage() {
        String testMessage = "Test message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertTrue(message.matches(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteDownSomeLogMessage() {
        String[] testMessages = {"Message #1", "Message 2"};

        txtLogger.log(testMessages[0]);
        txtLogger.log(testMessages[1]);

        List<String> newMessages = txtLogger.getLog();
        for (int i = 0; i < newMessages.size(); i++) {
            assertTrue(newMessages.get(i).matches(".*" + testMessages[i] + "$"));
        }
    }

    @Test
    public void doesLogIncludeDateAndTime() {
        String testMessage = "Message";

        txtLogger.log(testMessage);

        String message = txtLogger.getLog().get(0);
        assertTrue(message.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }
}
