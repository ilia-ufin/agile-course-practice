package ru.unn.agile.shape3darea.viewmodel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DummyLoggerTest {
    @Test
    public void checkReturnEmptyLog() {
        DummyLogger logger = new DummyLogger();
        logger.log("Test log");
        assertEquals(0, logger.getLog().size());
    }

    @Test
    public void checkReturnEmptyLogProperty() {
        DummyLogger logger = new DummyLogger();
        logger.log("Test log property");
        assertEquals(0, logger.logProperty().size());
    }
}
