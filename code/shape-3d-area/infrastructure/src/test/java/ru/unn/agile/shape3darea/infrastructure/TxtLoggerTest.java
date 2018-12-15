package ru.unn.agile.shape3darea.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.shape3darea.viewmodel.ILogger;

import java.io.*;
import java.util.List;

import static org.junit.Assert.*;

public class TxtLoggerTest {
    private static final String FILE_NAME = "shape_3d_area_txt_logger_test.log";
    private ILogger logger;

    private int getNumberOfLines() throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        int numberOfLines = 0;
        while (reader.readLine() != null) {
            ++numberOfLines;
        }
        return numberOfLines;
    }

    private int getNumberOfSymbols() throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        int numberOfSymbols = 0;
        while (reader.read() != -1) {
            ++numberOfSymbols;
        }
        return numberOfSymbols;
    }

    @Before
    public void setUp() {
        logger = new TxtLogger(FILE_NAME);
    }

    @After
    public void tearDown() {
        logger = null;
    }

    @Test
    public void checkLogEmptyWhenCreated() {
        assertTrue(logger.logProperty().isEmpty());
    }

    @Test
    public void checkLogFileWasCreated() {
        final File file = new File(FILE_NAME);
        assertTrue(file.exists());
        assertFalse(file.isDirectory());
    }

    @Test
    public void checkNumberOfLinesIncrement() throws IOException {
        final int oldNumberOfLines = getNumberOfLines();
        String message = "Test message";

        logger.log(message);
        final int newNumberOfLines = getNumberOfLines();

        assertEquals(oldNumberOfLines + 1, newNumberOfLines);
    }

    @Test
    public void checkIncreaseNumberOfSymbolsIn() throws IOException {
        final int oldNumberOfSymbols = getNumberOfSymbols();
        String message = "Test message";

        logger.log(message);
        int newNumberOfSymbols = getNumberOfSymbols();

        assertTrue(oldNumberOfSymbols + message.length() <= newNumberOfSymbols);
    }

    @Test
    public void canGetLogWithOneMessage() {
        String message = "Test message";

        logger.log(message);

        String receivedMessage = logger.logProperty().get(0);
        assertTrue(receivedMessage.matches("(.*)" + message + "(.*)"));
    }

    @Test
    public void canGetLogWithTwoMessages() {
        String firstMessage = "Test message first";
        String secondMessage = "Test message second";

        logger.log(firstMessage);
        logger.log(secondMessage);

        List<String> messages = logger.logProperty();
        assertEquals(2, messages.size());
        assertTrue(messages.get(0).matches(".*" + firstMessage + "(.*)"));
        assertTrue(messages.get(1).matches(".*" + secondMessage + "(.*)"));
    }

    @Test
    public void checkLogContainsDate() {
        String message = "Test message";

        logger.log(message);

        String receivedMessage = logger.logProperty().get(0);
        assertTrue(receivedMessage.matches(("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} > .*")));
    }

    @Test
    public void canNotLogWithInvalidLogger() {
        ILogger invalidLogger = new TxtLogger("");
        invalidLogger.log("Message");
        assertEquals(0, invalidLogger.getLog().size());
    }
}
