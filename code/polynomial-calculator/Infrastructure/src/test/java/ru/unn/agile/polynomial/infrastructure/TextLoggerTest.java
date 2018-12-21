package ru.unn.agile.polynomial.infrastructure;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

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

    @Test
    public void canWriteTwoLogMessages() {
        textLogger.log(TEXT_1);
        textLogger.log(TEXT_2);

        String logMessage = textLogger.getListLog().get(1);

        assertFalse(logMessage.matches(TEXT_1));
        assertTrue(logMessage.matches(TEXT_2));
    }

    @Test
    public void checkLogFileCreated() {
        final File file = new File(FILE_NAME);

        Assert.assertTrue(file.exists());
        assertFalse(file.isDirectory());
    }

    @Test(expected = Test.None.class)
    public void noExceptionForLoggerWithIncorrectFileName() {
        TextLogger textLogger = new TextLogger("*/*/");

        List<String> listLog = textLogger.getListLog();

        assertEquals(0, listLog.size());
    }
}
