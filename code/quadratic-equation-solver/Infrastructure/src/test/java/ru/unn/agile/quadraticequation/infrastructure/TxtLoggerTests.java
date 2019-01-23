package ru.unn.agile.quadraticequation.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TxtLoggerTests {
    private static final String TEST_MESSAGE_1 = "Test message";
    private static final String TEST_MESSAGE_2 = "Second test message";
    private static final String FILE_IS_NOT_FOUND = "is not found!";
    private static final String FILENAME = "./TxtLoggerTests-lab3.log";
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
    public void canCreateRealLogFile() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            fail(FILENAME + FILE_IS_NOT_FOUND);
        }
    }

    @Test
    public void canWriteLogMessageInLogFile() {
        txtLogger.log(TEST_MESSAGE_1);

        List<String> message = txtLogger.getLog();

        assertTrue(message.get(0).contains(TEST_MESSAGE_1));
    }

    @Test(expected = Test.None.class)
    public void canWriteIntoTxtLoggerWithEmptyFilename() {
        TxtLogger testLogger = new TxtLogger("");
        testLogger.log(TEST_MESSAGE_1);
    }

    @Test
    public void canWriteFewLogMessageInLogFile() {
        txtLogger.log(TEST_MESSAGE_1);
        txtLogger.log(TEST_MESSAGE_2);

        List<String> message = txtLogger.getLog();

        assertTrue(message.get(0).contains(TEST_MESSAGE_1));
        assertTrue(message.get(1).contains(TEST_MESSAGE_2));
    }
}
