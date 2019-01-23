package ru.unn.agile.ArraySorter.infrastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;


public class TextLoggerTests {
    private static final String DEFAULT_FILEPATH = "./TextLoggerTests.log";
    private TextLogger textLogger;

    @Before
    public void setUp() {
        textLogger = new TextLogger(DEFAULT_FILEPATH);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(textLogger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(DEFAULT_FILEPATH));
        } catch (FileNotFoundException e) {
            fail("File " + DEFAULT_FILEPATH + " wasn't found!");
        }
    }

    @Test
    public void isDateTimeAddedToLogMessage() {
        String testMessage = "Test message";

        textLogger.log(testMessage);

        String message = textLogger.getLog().get(0);
        assertTrue(message.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }

    @Test
    public void canLogMessage() {
        String testMessage = "Test message to log";

        textLogger.log(testMessage);

        String message = textLogger.getLog().get(0);
        assertTrue(message.matches(".*" + testMessage + "$"));
    }

    @Test
    public void canLogSeveralMessages() {
        String[] messages = {"Test #1", "Test #2", "Test #3", "Test #4"};

        for (int i = 0; i < messages.length; ++i) {
            textLogger.log(messages[i]);
        }

        List<String> actualMessages = textLogger.getLog();
        for (int i = 0; i < actualMessages.size(); ++i) {
            String message = actualMessages.get(i);
            assertTrue(message.matches(".*" + messages[i] + "$"));
        }
    }

    @Test(expected = Test.None.class)
    public void noThrowCtorWhenInvalidFilenameIsSpecified() {
        new TextLogger("");
    }

    @Test(expected = Test.None.class)
    public void noThrowToLogWhenInvalidFilenameIsSpecified() {
        TextLogger logger = new TextLogger("");
        logger.log("hello");
    }

    @Test(expected = Test.None.class)
    public void noThrowGetLogWhenInvalidFilenameIsSpecified() {
        TextLogger logger = new TextLogger("");
        logger.log("hello");
        logger.getLog();
    }
}
