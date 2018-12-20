package ru.unn.agile.polynomial.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;


public class TextLoggerTest {
    private static final String FILE_NAME = "./textLogger.log";
    private TextLogger textLogger;

    @Before
    public void initTxtLogger() {
        textLogger = new TextLogger(FILE_NAME);
    }

    @After
    public void nullLogger() {
        textLogger = null;
    }

    @Test
    public void canCreateLogger() {
        assertNotNull(textLogger);
    }

    @Test
    public void initialWithInvalidFileName() {
        textLogger = new TextLogger("");
    }

    }
