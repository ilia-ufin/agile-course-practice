package ru.unn.agile.numbersinwords.infrastructure;

import org.junit.Test;
import java.io.IOException;

public class TxtLoggerTests {
    private static final String LOG_NAME = "testlog.log";

    @Test
    public void catchesClosedWriterWrite() throws IOException {
        TxtLogger logger = new TxtLogger(LOG_NAME);

        logger.close();
        logger.log("test");
    }

    @Test
    public void catchesClosedWriterClose() throws IOException {
        TxtLogger logger = new TxtLogger(LOG_NAME);

        logger.getFileWriter().close();
        logger.close();
    }
}
