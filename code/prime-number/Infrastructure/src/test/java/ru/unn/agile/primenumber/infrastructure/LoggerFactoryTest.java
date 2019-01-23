package ru.unn.agile.primenumber.infrastructure;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoggerFactoryTest {

    private static final String TEST_FILE_NAME = "build/logs.log";
    private static final String ERROR_MESSAGE_TEXT = "File name should be set";

    @Test
    public void canInitiateLogger() {
        Logger logger = new LoggerFactory().getLogger();

        assertNotNull(logger.getLogs());
    }

    @Test
    public void canInitiateTextLogger() {
        Logger logger = new LoggerFactory().getLogger();

        assertTrue(logger instanceof TextLogger);
    }

    @Test
    public void canInitiateTextLoggerBuilder() throws IOException {
        Logger logger = new TextLogger
                .TextLoggerBuilder()
                .setFileName(TEST_FILE_NAME)
                .build();
        assertTrue(logger.getLogs().isEmpty());
    }

    @Test(expected = FileNotFoundException.class)
    public void canInitiateTextLoggerBuilderWithEmptyString() throws IOException {
            new TextLogger
                    .TextLoggerBuilder()
                    .setFileName("")
                    .build();
    }

    @Test(expected = IllegalStateException.class)
    public void canInitiateTextLoggerBuilderWithoutFileName() {
        new TextLogger
                .TextLoggerBuilder()
                .build();
    }

    @Test
    public void canInitiateTextLoggerBuilderWithoutFileNameMessage() {
        try {
            new TextLogger
                    .TextLoggerBuilder()
                    .build();
        } catch (IllegalStateException e) {
            assertEquals(ERROR_MESSAGE_TEXT, e.getMessage());
        }
    }

    @Test
    public void canResetLogger() {
        try {
            LoggerFactory factory = new LoggerFactory();
            factory.resetLogger();
        } catch (IllegalStateException e) {
            assertEquals(ERROR_MESSAGE_TEXT, e.getMessage());
        }
    }

    @Test(expected = RuntimeException.class)
    public void canInitFactoryWithIncorrectPath() {
        LoggerFactory factory = mock(LoggerFactory.class);
        when(factory.buildPath()).thenReturn("");
        when(factory.getLogger()).thenCallRealMethod();
        when(factory.resetLogger()).thenCallRealMethod();

        factory.resetLogger();
    }

}
