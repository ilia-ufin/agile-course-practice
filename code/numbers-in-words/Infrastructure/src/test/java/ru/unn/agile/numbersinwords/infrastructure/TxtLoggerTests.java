package ru.unn.agile.numbersinwords.infrastructure;

import org.junit.Test;
import java.io.IOException;

public class TxtLoggerTests {
    private static final String LOG_NAME = "TxtLoggerTests.log";
    private static final String TEST_STRING = "This should not ever be written to file";

    @Test
    public void catchesClosedWriterWrite() throws IOException {
        TxtLogger logger = new TxtLogger(LOG_NAME);

        logger.getFileWriter().close();
        logger.log(TEST_STRING);
    }
}
