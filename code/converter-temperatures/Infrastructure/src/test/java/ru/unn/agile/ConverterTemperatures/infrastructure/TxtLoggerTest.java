package ru.unn.agile.ConverterTemperatures.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TxtLoggerTest {
    private static final String FILE_NAME = "./TxtLogger.log";
    private static final String TEST_MESSAGE = "Value -300 in incorrect";
    private static final String TEST_MESSAGE_2 = "Value -450 in incorrect";
    private TxtLogger txtLogger;

    @Before
    public void initTxtLogger() {
        txtLogger = new TxtLogger(FILE_NAME);
    }

    @After
    public void nullLogger() {
        txtLogger = null;
    }

    @Test
    public void canCreateLogger() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canCreateLogFile() {
        File logfile = new File(FILE_NAME);

        assertTrue(logfile.exists());
    }

    @Test
    public void canWriteLogMessage() {
        txtLogger.log(TEST_MESSAGE);

        String logMessage = txtLogger.getLog().get(0);
        assertTrue(logMessage.matches(TEST_MESSAGE));
    }

    @Test
    public void canWriteTwoMessages() {
        String[] messages = {TEST_MESSAGE, TEST_MESSAGE_2};

        for (String mes : messages) {
            txtLogger.log(mes);
        }

        List<String> log = txtLogger.getLog();
        for (int i = 0; i < log.size(); i++) {
            assertTrue(log.get(i).matches(messages[i]));
        }
    }

    @Test(expected = Test.None.class)
    public void canCreateLogWithEmptyFilename() {
        TxtLogger logger = new TxtLogger("");
        logger.log(TEST_MESSAGE);
        assertEquals(0, logger.getLog().size());
    }
}
