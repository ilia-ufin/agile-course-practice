package ru.unn.agile.numerical_integration.infrastructure;

import org.junit.After;
import org.junit.Before;

import ru.unn.agile.numerical_integration.viewmodel.legacy.ViewModelLoggingTests;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class ViewModelFileLoggerTests extends ViewModelLoggingTests {
    private static final String LOG_NAME = "numerical_integration.log";
    private static final Path LOG_PATH = FileSystems.getDefault().getPath(LOG_NAME);

    @Before
    @Override
    public void setUp() throws Exception {
        Files.deleteIfExists(LOG_PATH);
        super.setUp();
        setLogger(new FileLogger(LOG_NAME));
    }
    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        Files.deleteIfExists(LOG_PATH);
    }
}
