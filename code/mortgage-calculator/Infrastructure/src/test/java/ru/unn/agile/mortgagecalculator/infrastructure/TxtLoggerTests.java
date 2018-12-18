package ru.unn.agile.mortgagecalculator.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
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
        ArrayList<String> expected = new ArrayList<>();

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

    @Test
    public void canSeveralLogs() {
        txtLogger.log("I'm superman");
        txtLogger.log("I'm batman");

        boolean containsRecords = false;
        List<String> logs = txtLogger.getLog();

        if (logs.get(0).contains("I'm superman") && logs.get(1).contains("I'm batman")) {
            containsRecords = true;
        }

        assertTrue(containsRecords);
    }

    @Test
    public void canCreateFile() {
        File f = new File(FILENAME);

        assertTrue(f.exists());
    }

    @Test
    public void checkEmptyLog() {
        txtLogger = new TxtLogger("");

        txtLogger.log("I don't be superman");

        assertTrue(txtLogger.getLog().isEmpty());
    }

    @Test
    public void checkFormatTime() {
        txtLogger.log("I don't be superman");

        String res = txtLogger.getLog().get(0);

        assertTrue(res.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}: INFO .*"));
    }

}
