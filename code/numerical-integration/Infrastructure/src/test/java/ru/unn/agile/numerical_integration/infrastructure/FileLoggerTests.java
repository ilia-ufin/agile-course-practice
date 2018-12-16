package ru.unn.agile.numerical_integration.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class FileLoggerTests {
    private static final String LOG_NAME = "numerical_integration.log";
    private static final Path LOG_PATH = FileSystems.getDefault().getPath(LOG_NAME);
    private FileLogger logger;

    public String getLineFromLog() throws IOException {
        BufferedReader br = Files.newBufferedReader(LOG_PATH);
        String line = br.readLine();
        br.close();
        return line;
    }

    @Before
    public void setUp() throws IOException {
        Files.deleteIfExists(LOG_PATH);

        logger = new FileLogger(LOG_NAME);
    }

    @After
    public void tearDown() throws IOException {
        logger.close();
        logger = null;

        Files.deleteIfExists(LOG_PATH);
    }

    @Test
    public void canCreateFile() {
        assertTrue(Files.exists(LOG_PATH));
    }

    @Test
    public void canCreateFileLoggerIfFileAlreadyExists()  throws IOException {
        logger.close();
        logger = null;
        logger = new FileLogger(LOG_NAME);

        assertTrue(Files.exists(LOG_PATH));
    }

    @Test
    public void canLogMessage() throws IOException {
        logger.log("Ololo");

        assertTrue(getLineFromLog().contains("Ololo"));
    }

    @Test
    public void canLogMessageIfFileAlreadyExists() throws IOException {
        logger.close();
        logger = null;
        logger = new FileLogger(LOG_NAME);

        logger.log("Ololo");

        assertTrue(getLineFromLog().contains("Ololo"));
    }

    @Test
    public void canGetLog() {
        logger.log("Ololo");
        assertTrue(logger.getLog().get(0).contains("Ololo"));
    }
}
