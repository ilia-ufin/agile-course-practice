package ru.unn.agile.salarycalculator.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class LoggerTests {
    private static final String FILENAME = "./LoggerTests-lab3.log";
    private static Logger myLogger;

    @Before
    public void setUp() {
        myLogger = new Logger(FILENAME);
    }

    @Test
    public void canCreateLoggerClassWithFileName() {
        assertNotNull(myLogger);
    }

    @Test
    public void canWriteLogMessage() {
        String myTestMessage = "my test message";

        myLogger.log(myTestMessage);

        String message = myLogger.getLog().get(0);
        assertEquals(message, myLogger.currentTime() + " --- " + myTestMessage);
    }

    @Test
    public void canWriteSomeLogMessage() {
        String[] myMessages = {"my Test Message 1", "my Test Message 2"};

        myLogger.log(myMessages[0]);
        myLogger.log(myMessages[1]);

        List<String> actualMessages = myLogger.getLog();
        for (int strLog = 0; strLog < actualMessages.size(); strLog++) {
            assertEquals(actualMessages.get(strLog),
                    myLogger.currentTime() + " --- " + myMessages[strLog]);
        }
    }

    @Test
    public void doesLogContainsDateAndTime() {
        String myTestMessage = "My Test Message";

        myLogger.log(myTestMessage);

        String message = myLogger.getLog().get(0);
        assertTrue(message.contains(myLogger.currentTime()));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void doesLogContainsDateAndTimeAtPositionMoreThenSizeLog() {
        String myTestMessage = "My Test Message";

        myLogger.log(myTestMessage);

        String message = myLogger.getLog().get(5);
    }

    @Test
    public void doesLogContainsDateAndTimeAt5Position() {
        String myTestMessage = "My Test Message";

       for (int stringLog = 0; stringLog < 6; stringLog++) {
           myLogger.log(myTestMessage);
       }
       String message = myLogger.getLog().get(5);

        assertTrue(message.contains(myLogger.currentTime()));
    }

}
