package ru.unn.agile.romannumberconverter.infrastructure;

import org.junit.*;

import java.io.File;
import java.util.List;

import static junit.framework.TestCase.*;

public class TxtLoggerTest {
    private static final String FILE_NAME = "./TextLogger.log";
    private static final String DATE_TIME_LOG_LINE =
            "^\\d{2}.\\d{2}.\\d{4} \\d{2}:\\d{2}:\\d{2} -> ";
    private static final String TEST_MESSAGE = "Some test text";
    private TxtLogger logger;

    @Before
    public void setUp() {
        logger = new TxtLogger(FILE_NAME);
    }

    @After
    public void tearDown() {
        logger = null;
    }

    @Test
    public void canCreateLoggerWithCorrectFileName() {
        assertNotNull(logger);
    }

    @Test
    public void canCreateLogFileWithCorrectFileName() {
        File f = new File(FILE_NAME);
        assertTrue(f.exists());
    }

    @Test
    public void canWriteLogMessageToLogger() {
        logger.log(TEST_MESSAGE);
        List<String> listLogs = logger.getLog();
        assertTrue(listLogs.get(0).matches(DATE_TIME_LOG_LINE + TEST_MESSAGE));
    }

    @Test
    public void canWriteSeveralLogMessagesToLogger() {
        String[] msg = {TEST_MESSAGE + " 0", TEST_MESSAGE + " 1"};
        for (String message : msg) {
            logger.log(message);
        }
        List<String> listLogs = logger.getLog();
        for (int i = 0; i < listLogs.size(); i++) {
            assertTrue(listLogs.get(i).matches(DATE_TIME_LOG_LINE + msg[i]));
        }
    }

    @Test(expected = Test.None.class)
    public void noThrowWhenCreateTxtLoggerWithIncorrectFileName() {
        TxtLogger loggerEmpty = new TxtLogger("");
        assertEquals(0, loggerEmpty.getLog().size());
    }

    @Test(expected = Test.None.class)
    public void noThrowWhenWriteToTxtLoggerWithIncorrectFileName() {
        TxtLogger loggerEmpty = new TxtLogger("");
        loggerEmpty.log(TEST_MESSAGE);
        assertEquals(0, loggerEmpty.getLog().size());
    }

    @Test(expected = Test.None.class)
    public void noThrowWhenReadFromTxtLoggerWithIncorrectFileName() {
        TxtLogger loggerEmpty = new TxtLogger("");
        List<String> listLogs = loggerEmpty.getLog();
        assertEquals(0, listLogs.size());
    }
}
