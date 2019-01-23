package ru.unn.agile.binarysearch.infrastructure;

import org.junit.Test;
import java.io.IOException;

public class TxtLoggerTests {
    private static final String FILENAME = "./TxtLoggerTests.log";

    @Test
    public void catchesClosedWriterWrite() throws IOException {
        TxtLogger logger = new TxtLogger(FILENAME);

        logger.getWriter().close();
        logger.log("test");
    }
}
