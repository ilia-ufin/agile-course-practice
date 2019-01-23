package ru.unn.agile.infixtopostfixnotationconverter.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class FileLoggerTests {
    private static final String DEFAULT_LOG_FILE_NAME =
            "infix-to-postfix-stream-logger-test.log";
    private static final Path DEFAULT_LOG_PATH =
            FileSystems.getDefault().getPath(DEFAULT_LOG_FILE_NAME);
    private FileLogger logger;

    @Before
    public void setUp() throws IOException {
        Files.deleteIfExists(DEFAULT_LOG_PATH);

        logger = new FileLogger(DEFAULT_LOG_FILE_NAME);
    }

    @After
    public void tearDown() throws IOException {
        logger.close();
        logger = null;

        Files.deleteIfExists(DEFAULT_LOG_PATH);
    }

    @Test
    public void canLogMessage() throws IOException {
        logger.log("hello");

        boolean hasLine = false;
        try (BufferedReader inputStream =
                Files.newBufferedReader(DEFAULT_LOG_PATH)) {
            hasLine = inputStream.readLine().contains("hello");
        }
        assertTrue(hasLine);
    }

    @Test
    public void canReturnLoggedMessage() {
        logger.log("hello");

        assertTrue(logger.getMessages().get(0).contains("hello"));
    }
}
