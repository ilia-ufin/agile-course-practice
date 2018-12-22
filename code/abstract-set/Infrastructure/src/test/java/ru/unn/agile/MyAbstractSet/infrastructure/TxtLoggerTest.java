package ru.unn.agile.MyAbstractSet.infrastructure;

import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static org.junit.Assert.*;

public class TxtLoggerTest {
    private static final String FILENAME = "./AbstractSet-TxtLoggerTest.log";
    private static final String TEST_MESSAGE = "Test message";
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
    public void canCreateLogFile() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException ex) {
            fail("File " + FILENAME + " wasn't found!");
        }
    }

    @Test
    public void canWriteLogMessage() {
        txtLogger.log(TEST_MESSAGE);

        String message = txtLogger.getLog().get(0);
        assertTrue(message.matches(".*" + TEST_MESSAGE + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {TEST_MESSAGE.concat("1"), TEST_MESSAGE.concat("2")};

        txtLogger.log(messages[0]);
        txtLogger.log(messages[1]);

        List<String> currentMessages = txtLogger.getLog();
        for (int i = 0; i < currentMessages.size(); i++) {
            assertTrue(currentMessages.get(i).matches(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void doesLogContainDateTime() {
        txtLogger.log(TEST_MESSAGE);

        String message = txtLogger.getLog().get(0);
        assertTrue(message.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*"));
    }

    @Test(expected = Test.None.class)
    public void canWriteInLogWithEmptyFileName() {
        TxtLogger logger = new TxtLogger("");

        logger.log(TEST_MESSAGE);
        assertEquals(0, logger.getLog().size());
    }

    @Test(expected = Test.None.class)
    public void canReadFromLogWithEmptyFileName() {
        TxtLogger logger = new TxtLogger("");

        assertEquals(0, logger.getLog().size());
    }
}
