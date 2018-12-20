package ru.unn.agile.polynomial.infrastructure;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;


public class TextLoggerTest {
    private static final String FILE_NAME = "./textLogger.log";
    private TextLogger textLogger;
    private static final String TEXT_1 = "Text 1";
    private static final String TEXT_2 = "Text 2";

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

    @Test
    public void canCreateLogFile() {
        Assert.assertNotNull(textLogger);
    }

    @Test
    public void canWriteLogMessage() {
        textLogger.log(TEXT_1);

        String logMessage = textLogger.getListLog().get(0);
        assertTrue(logMessage.matches(TEXT_1));
    }

    }
