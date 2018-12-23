package ru.unn.agile.caesarcipher.infrastructure;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

import java.io.*;

import static junit.framework.TestCase.assertNotNull;

public class TxtLoggerTests {
    private static final String FILENAME = "./TxtLoggerTests.log";

    @Test
    public void canGenerateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            fail("File " + FILENAME + " was not found!");
        }
    }

    @Test
    public void canGenerateLoggerWithFileName() {
        TxtLogger logger;
        logger = new TxtLogger(FILENAME);

        assertNotNull(logger);
    }

    @Test
    public void canNotWriteToLog() {
        TxtLogger logger;
        logger = new TxtLogger("");

        logger.log("I can not do it");
    }

    @Test
    public void canCreateFile() {
        File file = new File(FILENAME);

        assertTrue(file.exists());
    }

    @Test
    public void checkEmptyLog() {
        TxtLogger logger;
        logger = new TxtLogger("");

        logger.log("I can not do it");

        assertTrue(logger.getLog().isEmpty());
    }
}
