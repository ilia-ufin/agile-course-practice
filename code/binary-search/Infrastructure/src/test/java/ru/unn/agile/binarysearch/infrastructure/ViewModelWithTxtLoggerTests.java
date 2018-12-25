package ru.unn.agile.binarysearch.infrastructure;

import ru.unn.agile.binarysearch.viewmodel.ViewModelTests;
import java.io.IOException;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    private static final String LOG_NAME = "ViewModelWithTxtLoggerTests.log";

    @Override
    public void setUp() throws IOException {
        TxtLogger realLogger = new TxtLogger(LOG_NAME);
        this.createViewModelWithLogger(realLogger);
    }
}
