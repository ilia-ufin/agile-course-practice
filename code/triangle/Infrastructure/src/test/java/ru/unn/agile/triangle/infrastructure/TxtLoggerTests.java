package ru.unn.agile.triangle.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.fail;

public class TxtLoggerTests {
    private static final String FILENAME = "./TxtLogger_Tests-lab3.log";
 //   private static final String RE_LOG_LINE = "^\\d{2}.\\d{2}.\\d{4} \\d{2}:\\d{2}:\\d{2} - ";
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
        } catch (FileNotFoundException e) {
            fail("File " + FILENAME + " wasn't found!");
        }
    }

    @Test
    public void canWriteOneLogMessage() {
        String message = "One log message";

        txtLogger.log(message);

        int logMessage = txtLogger.getLogger().size();
        assertEquals(1, logMessage);

    }

    @Test
    public void canWriteSomeLogMessage() {
        String[] messages = {"First log message", "Second log message"};

        for (String message : messages) {
            txtLogger.log(message);
        }

        int logMessage = txtLogger.getLogger().size();
        assertEquals(2, logMessage);
    }

    @Test
    public void canCreateLoggerWithIncorrectName() {
        TxtLogger emptyLogger = new TxtLogger("");

        assertEquals(0, emptyLogger.getLogger().size());
    }

    @Test
    public void canWriteIfFileNameIsInCorrect() {
        TxtLogger emptyLogger = new TxtLogger("");

        emptyLogger.log("Message");

        assertEquals(0, emptyLogger.getLogger().size());
    }

    @Test
    public void canReadIFileNameWhenInCorrect() {
        TxtLogger emptyLogger = new TxtLogger("");

        List<String> log = emptyLogger.getLogger();

        assertEquals(0, log.size());
    }
}
