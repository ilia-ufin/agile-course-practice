package ru.unn.agile.huffman.infrastructure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;

public class TextFileLoggerTest {
    private static final String TEST_LOG_MESSAGE = "test log message";
    private static final String FILENAME = "testlog.txt";
    private static final String REGEX_FORMAT_DT =
            "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"; //"yyyy-MM-dd HH:mm:ss"
    private TextFileLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TextFileLogger(FILENAME);
    }

    @Test
    public void creationLogger() {
        assertNotNull(txtLogger);
    }

    @Test(expected = Test.None.class)
    public void noExceptionWhenRecordingToTxtLoggerWithIncorrectPathToFile() {
        TextFileLogger logger = new TextFileLogger("");

        logger.log(TEST_LOG_MESSAGE);
    }

    @Test(expected = Test.None.class)
    public void noThrowExceptionWhenCreateTxtLoggerWithIncorrectPathToFile() {
        TextFileLogger logger = new TextFileLogger("");
    }

    @Test(expected = Test.None.class)
    public void noThrowExceptionWhenGetLogFromTxtLoggerWithIncorrectPathToFile() {
        TextFileLogger logger = new TextFileLogger("");
        logger.log(TEST_LOG_MESSAGE);

        logger.getLog();
    }

    @Test
    public void checkDateAndTimeFormat() {
        txtLogger.log(TEST_LOG_MESSAGE);

        String logString = txtLogger.getLog().get(0);
        assertTrue(logString.matches(REGEX_FORMAT_DT + ".*"));
    }

    @Test
    public void canWriteLogMessage() {
        String logMessage = TEST_LOG_MESSAGE;

        txtLogger.log(logMessage);

        String logString = txtLogger.getLog().get(0);
        assertTrue(logString.matches(REGEX_FORMAT_DT + " > " + logMessage));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void cantReadUnexistingLogMessage() {
        String logMessage = TEST_LOG_MESSAGE;

        txtLogger.log(logMessage);

        String logString = txtLogger.getLog().get(2);
    }




    @Test
    public void checkCountStingMessagesInLog() {
        int len = fillLog();

        assertEquals(len, txtLogger.getLog().size());
    }

    private int fillLog() {
        String[] messages = {"message 1",
                "message 2",
                "message 3"
        };
        for (String it : messages) {
            txtLogger.log(it);
        }

        return messages.length;
    }
}

