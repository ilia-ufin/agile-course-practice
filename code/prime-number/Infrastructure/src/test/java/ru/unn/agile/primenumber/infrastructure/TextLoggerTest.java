package ru.unn.agile.primenumber.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TextLoggerTest {

    private static final String STRING_LOG_CONSTANT_1 = "Log1";
    private static final String STRING_LOG_CONSTANT_2 = "2";

    private LoggerFactory factory;

    @Before
    public void initFactory() {
        factory = new LoggerFactory();
    }

    @Test
    public void canInitiateLogger() {
        Logger logger = factory.getLogger();

        assertTrue(logger.getLogs().isEmpty());
    }

    @Test
    public void canResetFileLogger() {
        Logger logger = factory.resetLogger();

        assertTrue(logger.getLogs().isEmpty());
    }

    @Test
    public void canResetFileLoggerTwice() {
        Logger logger = factory.getLogger();

        logger.log(STRING_LOG_CONSTANT_1);
        Logger newLogger = factory.resetLogger();

        assertTrue(newLogger.getLogs().isEmpty());
    }

    @Test
    public void isSingletonLogger() {
        Logger logger = factory.getLogger();

        Logger newLogger = factory.getLogger();

        assertTrue(logger == newLogger);
    }

    @Test
    public void isLoggerReset() {
        Logger logger = factory.getLogger();

        Logger newLogger = factory.resetLogger();

        assertTrue(logger != newLogger);
    }

    @Test
    public void canLoginString() {
        Logger logger = factory.getLogger();

        logger.log(STRING_LOG_CONSTANT_1);

        assertTrue(logger.getLogs().size() == 1);
    }

    @Test
    public void canLoginStringValue() {
        Logger logger = factory.getLogger();

        logger.log(STRING_LOG_CONSTANT_1);

        assertEquals(STRING_LOG_CONSTANT_1, logger.getLogs().get(0));
    }

    @Test
    public void canLoginTwoStringValue() {
        Logger logger = factory.getLogger();

        logger.log(STRING_LOG_CONSTANT_1);
        logger.log(STRING_LOG_CONSTANT_2);
        assertEquals(STRING_LOG_CONSTANT_2, logger.getLogs().get(1));
    }

    @Test
    public void canLogTwoLoggerStringValue() {
        Logger logger1 = factory.getLogger();
        Logger logger2 = factory.getLogger();
        List expectedList = new LinkedList<String>() {
            {
                add(STRING_LOG_CONSTANT_1);
                add(STRING_LOG_CONSTANT_2);
            }
        };

        logger1.log(STRING_LOG_CONSTANT_1);
        logger2.log(STRING_LOG_CONSTANT_2);

        assertArrayEquals(expectedList.toArray(), logger2.getLogs().toArray());
    }

    @Test(expected = RuntimeException.class)
    public void canLogToNotFile() {
        TextLogger logger = mock(TextLogger.class);

        doCallRealMethod().when(logger).log(any(String.class));

        logger.log(STRING_LOG_CONSTANT_1);
    }

    @Test(expected = RuntimeException.class)
    public void canGetLogsFromNotFile() {
        TextLogger logger = mock(TextLogger.class);
        when(logger.getLogs()).thenCallRealMethod();

        logger.getLogs();
    }
}
