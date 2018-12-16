package ru.unn.agile.mathstatistics.infrastructure;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class TxtLoggerTests {
    private TxtLogger txtLogger;

    private static final String FILENAME = "./TestsTextLogger.txt";

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(FILENAME);
    }

    @Test(expected = Test.None.class)
    public void noThrowGetLogWhenIncorrectFilename() {
        TxtLogger logger = new TxtLogger("");
        logger.log("");
        logger.getLog();
    }

    @Test(expected = Test.None.class)
    public void noThrowWhenIncorrectFilename() {
        new TxtLogger("");
    }

    @Test(expected = Test.None.class)
    public void noThrowToLogWhenIncorrectFilename() {
        TxtLogger logger = new TxtLogger("");
        logger.log("");
    }

    @Test
    public void canCreateLogFile() {
        try {
            new BufferedReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            fail("File " + FILENAME + " wasn't found!");
        }
    }

    @Test
    public void canCreateLogger() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canWriteLogMessage() {
        String testMes = "Rainy winter";

        txtLogger.log(testMes);

        String mess = txtLogger.getLog().get(0);
        assertTrue(mess.matches(".*" + testMes + "$"));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Correct winter", "Snowly summer"};

        txtLogger.log(messages[0]);
        txtLogger.log(messages[1]);

        List<String> actualMessages = txtLogger.getLog();
        for (int i = 0; i < actualMessages.size(); i++) {
            String mess = actualMessages.get(i);
            assertTrue(mess.matches(".*" + messages[i] + "$"));
        }
    }

    @Test
    public void isLoggerIncludeTime() {
        String testMes = "Correct sun";

        txtLogger.log(testMes);

        String mess = txtLogger.getLog().get(0);
        assertTrue(mess.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} >> .*"));
    }

}
