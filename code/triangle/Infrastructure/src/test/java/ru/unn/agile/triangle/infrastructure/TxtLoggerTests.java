package ru.unn.agile.triangle.infrastructure;

import org.junit.After;
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
    private static final String MESSAGE = "One log message";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(FILENAME);
    }

    @After
    public void tearDown() {
        txtLogger = null;
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
        txtLogger.log(MESSAGE);

        int logMessage = txtLogger.getLogger().size();
        assertEquals(1, logMessage);

    }

    @Test
    public void canWriteSomeLogMessage() {
        String[] messages = {MESSAGE, MESSAGE};


        for (String message : messages) {
            txtLogger.log(message);
        }

        int logMessage = txtLogger.getLogger().size();
        assertEquals(2, logMessage);
    }

    @Test
    public void canCreateLoggerWithIncorrectName() {
        TxtLogger emptyLogger = new TxtLogger(FILENAME);

        assertEquals(0, emptyLogger.getLogger().size());
    }

    @Test
    public void canWriteWhenFileNameIsInCorrect() {
        TxtLogger emptyLogger = new TxtLogger(FILENAME);

        emptyLogger.log(MESSAGE);

        assertEquals(1, emptyLogger.getLogger().size());
    }

    @Test
    public void canReadFileNameWhenInCorrect() {
        TxtLogger emptyLogger = new TxtLogger(FILENAME);

        List<String> log = emptyLogger.getLogger();

        assertEquals(0, log.size());
    }

    @Test(expected = Test.None.class)
    public void createLoggerWithoutTrowExpWhenIncorrectPathToFileName() {
        TxtLogger txtLogger = new TxtLogger(FILENAME);
    }

    @Test(expected = Test.None.class)
    public void createLogByTextLoggerWhenIncorrectPathToFileName() {
        TxtLogger txtLogger = new TxtLogger(FILENAME);

        txtLogger.log(MESSAGE);
    }

    @Test(expected = Test.None.class)
    public void canGetLogOnTextLoggerWhenPathToFileIncorrect() {
        TxtLogger txtLogger = new TxtLogger(FILENAME);
        txtLogger.log(MESSAGE);

        txtLogger.getLogger();
    }
}
