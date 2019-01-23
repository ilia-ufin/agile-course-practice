package ru.unn.agile.primenumber.infrastructure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InMemoryLoggerTest {

    private static final String STRING_LOG_CONSTANT_1 = "1";
    private static final String STRING_LOG_CONSTANT_2 = "2";
    private static final String STRING_LOG_EMPTY = "";

    @Test
    public void canLogString() {
        Logger logger = new InMemoryLogger();
        logger.log(STRING_LOG_CONSTANT_1);

        assertEquals(STRING_LOG_CONSTANT_1, logger.getLogs().get(0));
    }

    @Test
    public void canLogEmptyString() {
        Logger logger = new InMemoryLogger();
        logger.log(STRING_LOG_EMPTY);

        assertEquals(STRING_LOG_EMPTY, logger.getLogs().get(0));
    }

    @Test
    public void canLogTwoString() {
        Logger logger = new InMemoryLogger();
        logger.log(STRING_LOG_CONSTANT_1);
        logger.log(STRING_LOG_CONSTANT_2);

        assertEquals(STRING_LOG_CONSTANT_2, logger.getLogs().get(1));
    }

    @Test
    public void defaultLoggerIsEmptyString() {
        Logger logger = new InMemoryLogger();

        assertTrue(logger.getLogs().isEmpty());
    }
}
