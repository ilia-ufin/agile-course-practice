package ru.unn.agile.depositconverter.infrastructure;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class FileLoggerTests {
    private static final String FILE_NAME_LOG = "./FileLoggerTests-lab3-Deposit.log";
    private static final String TEST_MESSAGE = "Test message";
    private static final String DATE_AND_TIME_PATTERN =
            "|\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} |.*";
    private FileLogger fileLogger;

    @Before
    public void setUp() {
        fileLogger = new FileLogger(FILE_NAME_LOG);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(fileLogger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(FILE_NAME_LOG));
        } catch (FileNotFoundException e) {
            fail("File " + FILE_NAME_LOG + " wasn't found!");
        }
    }

    @Test
    public void canWriteLogMessage() {
        String testMessage = TEST_MESSAGE;

        fileLogger.log(testMessage);

        String message = fileLogger.getLog().get(0);
        assertTrue(message.matches(".*" + testMessage + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messagesLog = {TEST_MESSAGE + " 1", TEST_MESSAGE + " 2"};

        fileLogger.log(messagesLog[0]);
        fileLogger.log(messagesLog[1]);

        List<String> actualMessages = fileLogger.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            assertTrue(actualMessages.get(i).matches(".*" + messagesLog[i] + "$"));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String testMessageLog = TEST_MESSAGE + " for log";

        fileLogger.log(testMessageLog);

        String message = fileLogger.getLog().get(0);
        assertTrue(message.matches(DATE_AND_TIME_PATTERN));
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotGetLogForEmptyFileName() {
        String testPath = "";
        FileLogger fileLogger = new FileLogger(testPath);
        List<String> log = fileLogger.getLog();
    }

    @Test(expected = IllegalArgumentException.class)
        public void canNotWriteLogForEmptyFileName() {
            String testPath = "";
            FileLogger fileLogger = new FileLogger(testPath);
            String logString = "test";
            fileLogger.log(logString);
        }
}
