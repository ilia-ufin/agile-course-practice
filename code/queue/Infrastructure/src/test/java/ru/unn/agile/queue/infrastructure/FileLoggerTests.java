package ru.unn.agile.queue.infrastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class FileLoggerTests {
    private static final String DEFAULT_LOGGER_FILENAME = "./FileLoggerTests.log";
    private FileLogger fileLogger;

    @Before
    public void setUp() {
        fileLogger = new FileLogger(DEFAULT_LOGGER_FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(fileLogger);
    }

    @Test
    public void canLogMessage() {
        String testMessage = "abc";

        fileLogger.log(testMessage);

        String message = fileLogger.getLog().get(0);
        assertTrue(message.matches(".*" + testMessage + "$"));
    }

    @Test
    public void canLogSeveralMessages() {
        String[] messages = {"abc", "def", "123"};

        fileLogger.log(messages[0]);
        fileLogger.log(messages[1]);
        fileLogger.log(messages[2]);

        List<String> actualMessages = fileLogger.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            String actualMessage = actualMessages.get(i);
            assertTrue(actualMessage.matches(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void isAddedDateAndTimeToMessage() {
        String testMessage = "abc";

        fileLogger.log(testMessage);

        String message = fileLogger.getLog().get(0);
        assertTrue(message.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }

    @Test(expected = Test.None.class)
    public void noThrowWhenCreateFileLoggerWithIncorrectPathToFile() {
        fileLogger = new FileLogger("");
    }

    @Test(expected = Test.None.class)
    public void noThrowWhenLogByFileLoggerWithIncorrectPathToFile() {
        fileLogger = new FileLogger("");

        fileLogger.log("abc");
    }

    @Test(expected = Test.None.class)
    public void noThrowWhenGetLogFromFileLoggerWithIncorrectPathToFile() {
        fileLogger = new FileLogger("");
        fileLogger.log("abc");

        fileLogger.getLog();
    }
}
