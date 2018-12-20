package ru.unn.agile.intersect.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import static ru.unn.agile.intersect.infrastructure.RegexMatcher.matchesPattern;

public class LoggerTests {
    private static final String FILENAME = "./Logger_Tests.log";

    private Logger logger;

    @Before
    public void setUp() {
        logger = new Logger(FILENAME);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(logger);
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

        logger.writeLog(testMessage);

        String message = logger.readLog().get(0);
        assertThat(message, matchesPattern(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message 1", "Test message 2"};

        logger.writeLog(messages[0]);
        logger.writeLog(messages[1]);

        List<String> actualMessages = logger.readLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertThat(actualMessages.get(i), matchesPattern(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessage = "Test message";

        logger.writeLog(testMessage);

        String message = logger.readLog().get(0);
        assertThat(message, matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}: .*"));
    }

    @Test(expected = Test.None.class)
    public void shouldntThrowErrorEmptyFileName() {
        logger = new Logger("");

        assertEquals(0, logger.readLog().size());
    }

    @Test(expected = Test.None.class)
    public void shouldntThrowErrorIncorrectFileName() {
        logger = new Logger("");

        logger.writeLog("Some data");

        assertEquals(0, logger.readLog().size());
    }

    @Test(expected = Test.None.class)
    public void shouldntThrowErrorReadWithIncorrectFileName() {
        logger = new Logger("");

        List<String> logList = logger.readLog();

        assertEquals(0, logList.size());
    }
}
