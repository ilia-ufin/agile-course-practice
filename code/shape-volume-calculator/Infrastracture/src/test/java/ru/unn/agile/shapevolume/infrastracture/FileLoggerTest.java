package ru.unn.agile.shapevolume.infrastracture;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class FileLoggerTest {
    @Test(expected = Test.None.class)
    public void canCreateFileLogger() {
        FileLogger f = new FileLogger("test file");
    }

    @Test
    public void canGetFileName() {
        FileLogger f = new FileLogger("test file");
        assertEquals(f.getFileName(), "test file");
    }

    @Test
    public void canCreateFileLoggerAndLogSomething() throws IOException {
        String testPath = "test file";
        FileLogger f = new FileLogger(testPath);

        String logString = "test";
        f.log(logString);

        File file = new File(testPath);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        assertEquals(logString, reader.readLine());
    }

    @Test
    public void canGetLog() {
        String testPath = "test file";
        FileLogger f = new FileLogger(testPath);

        String logString = "test";
        f.log(logString);
        List<String> log = f.getLog();

        assertEquals(logString, log.get(0));
    }


    @Test(expected = IllegalArgumentException.class)
    public void canNotGetLogFromFolder() throws IOException {
        String testPath = "test";
        Files.createDirectories(Paths.get(testPath));
        FileLogger f = new FileLogger(testPath);
        List<String> log = f.getLog();
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotReadLogFromWrongFile() throws IOException {
        String testPath = "test1";
        FileLogger f = new FileLogger(testPath);
        f.log("test");
        f.setFileName("");
        List<String> log = f.getLog();
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotWriteLogInWrongFile() throws IOException {
        String testPath = "test";
        FileLogger f = new FileLogger(testPath);
        f.log("test");
        f.setFileName("");
        f.log("test");
    }

}
