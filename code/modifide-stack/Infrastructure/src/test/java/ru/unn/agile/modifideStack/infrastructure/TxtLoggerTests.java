package ru.unn.agile.modifideStack.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.junit.Assert.*;

public class TxtLoggerTests {
    private static final String TEST_MESSAGE = "Tes test";
    private static final String LOG_PATTERN = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*";
    private static final String FILE_NAME = "./TxtLoggerTests.log";

    private TxtLogger txtLoggerT;

    @Before
    public void setUp() {
        txtLoggerT = new TxtLogger(FILE_NAME);
    }

    @After
    public void tearDown() {
        txtLoggerT = null;
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLoggerT);
    }

    @Test(expected = Test.None.class)
    public void canCreateLogWithEmptyFilename() {
        TxtLogger testLoggerT = new TxtLogger("");

        assertEquals(0, testLoggerT.getLog().size());
    }

    @Test(expected = Test.None.class)
    public void canReadLogWithEmptyFilename() {
        TxtLogger testLoggerT = new TxtLogger("");

        assertEquals(0, testLoggerT.getLog().size());
    }

    @Test(expected = Test.None.class)
    public void canWriteIntoLogWithEmptyFilename() {
        TxtLogger txtLoggerTest = new TxtLogger("");

        txtLoggerTest.log(TEST_MESSAGE);

        assertEquals(0, txtLoggerTest.getLog().size());
    }

    @Test
    public void canCreateFileLogOnDisk() {
        try {
            new BufferedReader(new FileReader(FILE_NAME));
        } catch (FileNotFoundException err) {
            fail("File '" + FILE_NAME + "' was not found on disk!");
        }
    }

    @Test
    public void areDateAndTimeTest() {
        txtLoggerT.log(TEST_MESSAGE);

        String log = txtLoggerT.getLog().get(0);
        assertTrue(log.matches(LOG_PATTERN));
    }

    @Test
    public void canWriteLogMessage() {
        txtLoggerT.log(TEST_MESSAGE);

        String message = txtLoggerT.getLog().get(0);
        assertTrue(message.matches(".*" + message + "$"));
    }

    @Test
    public void canWriteTwoLogMessages() {
        txtLoggerT.log(TEST_MESSAGE.concat(" #1"));
        txtLoggerT.log(TEST_MESSAGE.concat(" #2"));

        List<String> messages = txtLoggerT.getLog();

        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            assertTrue(message.matches(".*" + message + "$"));
        }
    }
}
