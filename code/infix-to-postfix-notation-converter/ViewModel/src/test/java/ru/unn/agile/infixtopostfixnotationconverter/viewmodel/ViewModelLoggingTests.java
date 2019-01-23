package ru.unn.agile.infixtopostfixnotationconverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelLoggingTests {
    private ViewModel viewModel;
    private ILogger logger;

    protected void setLogger(final ILogger value) {
        this.logger = value;
        this.viewModel.setLogger(value);
    }

    @Before
    public void setUp() throws Exception {
        viewModel = new ViewModel();
        logger = new DummyLogger();
        viewModel.setLogger(logger);
    }

    @After
    public void tearDown() throws Exception {
        logger.close();
        logger = null;
        viewModel = null;
    }

    @Test
    public void canLogComputeButtonClick() {
        viewModel.infixExpressionProperty().setValue("1234+4321");

        viewModel.convertAndCalculate();

        assertTrue(viewModel.logLinesProperty().toString()
                .contains(ViewModel.LOG_MESSAGE_COMPUTE_BUTTON_CLICKED));
    }

    @Test
    public void canLogInputExpressionChange() {
        viewModel.infixExpressionProperty().set("1+1");

        assertTrue(viewModel.logLinesProperty().toString()
                .contains(ViewModel.LOG_MESSAGE_INPUT_EXPRESSION_CHANGED));
    }
}
