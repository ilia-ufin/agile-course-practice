package ru.unn.agile.caesarcipher.infrastructure;

import org.junit.Test;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
}
