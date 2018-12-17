package ru.unn.agile.mortgagecalculator.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class TxtLoggerTests {
    private TxtLogger txtLogger;

    private static final String FILENAME = "./TxtLoggerTests.txt";

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(FILENAME);
    }

    @Test
    public void initialWithInvalidFileName() {
        txtLogger = new TxtLogger("");
    }

    @Test
    public void canCreateLogFile() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canWriteToLog() {
        txtLogger.log("I'm superman");
    }

    @Test
    public void canNotWriteToLog() {
        txtLogger = new TxtLogger("");

        txtLogger.log("I don't be superman");
    }

    @Test
    public void canGetLog() {
        ArrayList<String> expected = new ArrayList<String>();

        assertEquals(expected.getClass(), txtLogger.getLog().getClass());
    }

    @Test
    public void canGetNotEmptyLog() {
        txtLogger.log("I'm superman");

        boolean containsRecord = false;

        for (String record : txtLogger.getLog()) {
            if (record.contains("I'm superman")) {
                containsRecord = true;
                break;
            }
        }

        assertTrue(containsRecord);
    }

    @Test
    public void canGetEmptyLog() {
        boolean containsRecord = false;

        for (String record : txtLogger.getLog()) {
            if (record.contains("I'm not superman")) {
                containsRecord = true;
                break;
            }
        }

        assertFalse(containsRecord);
    }

}
