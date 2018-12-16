package ru.unn.agile.numbersinwords.infrastructure;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class TxtLoggerTests {
    private static final String LOG_NAME = "testlog.log";

    @Test
    public void catchesDeletedFileWrite() throws IOException {
        TxtLogger logger = new TxtLogger(LOG_NAME);
        File file = new File(LOG_NAME);

        boolean deleteResult = file.delete();
        logger.log("test");

        assertTrue(deleteResult);
    }

    @Test
    public void catchesDeletedFileClose() throws IOException {
        TxtLogger logger = new TxtLogger(LOG_NAME);
        File file = new File(LOG_NAME);

        boolean deleteResult = file.delete();
        logger.close();

        assertTrue(deleteResult);
    }
}
