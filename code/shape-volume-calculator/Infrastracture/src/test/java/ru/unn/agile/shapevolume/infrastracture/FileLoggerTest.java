package ru.unn.agile.shapevolume.infrastracture;

import org.junit.Test;
import ru.unn.agile.shapevolume.viewmodel.ILogger;

import java.io.*;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class FileLoggerTest {
    @Test
    public void canCreateFileLogger() {
        ILogger f = new FileLogger("test file");
    }

    @Test
    public void canCreateFileLoggerAndLogSomething() throws IOException {
        String test_file = "test file";
        ILogger f = new FileLogger(test_file);

        String logString = "test";
        f.log(logString);

        File file = new File(test_file);
        BufferedReader br = new BufferedReader(new FileReader(file));
        assertEquals(logString, br.readLine());
    }

    @Test
    public void canGetLog() {
        String test_file = "test file";
        ILogger f = new FileLogger(test_file);

        String logString = "test";
        f.log(logString);
        List<String> log = f.getLog();

        assertEquals(logString, log.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotCreateFileLoggerFromEmptyString() {
        ILogger f = new FileLogger("");
    }

}
