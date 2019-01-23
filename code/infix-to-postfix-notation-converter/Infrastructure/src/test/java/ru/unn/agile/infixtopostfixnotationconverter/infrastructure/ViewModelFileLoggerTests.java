package ru.unn.agile.infixtopostfixnotationconverter.infrastructure;

import org.junit.After;
import org.junit.Before;

import ru.unn.agile.infixtopostfixnotationconverter.viewmodel.ViewModelLoggingTests;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class ViewModelFileLoggerTests extends ViewModelLoggingTests {
    private static final String DEFAULT_LOG_FILE_NAME =
            "infix-to-postfix-viewModel-fileLoggerTest.log";
    private static final Path DEFAULT_LOG_PATH =
            FileSystems.getDefault().getPath(DEFAULT_LOG_FILE_NAME);

    @Before
    @Override
    public void setUp() throws Exception {
        Files.deleteIfExists(DEFAULT_LOG_PATH);

        super.setUp();
        setLogger(new FileLogger(DEFAULT_LOG_FILE_NAME));
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        Files.deleteIfExists(DEFAULT_LOG_PATH);
    }
}
