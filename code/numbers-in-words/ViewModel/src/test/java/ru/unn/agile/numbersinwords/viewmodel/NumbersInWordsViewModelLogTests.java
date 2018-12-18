package ru.unn.agile.numbersinwords.viewmodel;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class NumbersInWordsViewModelLogTests {
    private NumbersInWordsViewModel viewModel;
    private ILogger logger;

    public void setLogger(final ILogger logger) {
        this.logger = logger;
        viewModel = new NumbersInWordsViewModel(logger);
    }

    @Before
    public void setUp() throws IOException {
        FakeLogger newLogger = new FakeLogger();
        this.setLogger(newLogger);
    }


    @Test(expected = IllegalArgumentException.class)
    public void throwsErrorOnNullLogger() throws IllegalArgumentException {
        NumbersInWordsViewModel viewModel1 = new NumbersInWordsViewModel(null);
    }

    @Test
    public void canLogInput() {
        viewModel.setNumber("123456");
        String log = viewModel.getLogMessages().toString();

        assertTrue(log.contains(NumbersInWordsViewModel.LOG_MESSAGE_INPUT));
    }

    @Test
    public void canLogConvert() {
        viewModel.setNumber("11");
        viewModel.convert();
        String log = viewModel.getLogMessages().toString();

        assertTrue(log.contains(NumbersInWordsViewModel.LOG_MESSAGE_CONVERT));
    }
}
