package ru.unn.agile.numerical_integration.viewmodel.legacy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelLoggingTests {
    private ViewModel viewModel;
    private ILogger logger;

    public void setLogger(final ILogger logger) {
        this.logger = logger;
    }

    @Before
    public void setUp() throws Exception {
        viewModel = new ViewModel();
        logger = new FakeLogger();
        viewModel.setLogger(logger);
    }
    @After
    public void tearDown() throws Exception {
        logger.close();
        logger = null;
        viewModel = null;
    }

    @Test
    public void canLogComputeClick() {
        viewModel.compute();

        assertTrue(viewModel.getLogMessages().toString()
                .contains(ViewModel.LOG_COMPUTE_BUTTON_CLICKED));
    }

    @Test
    public void canLogFunctionTextFieldChanged() {
        viewModel.setFunction("3*x*x");

        assertTrue(viewModel.getLogMessages().toString()
                .contains(ViewModel.LOG_FUNCTION_INPUT + " "
                        + ViewModel.LOG_INPUT_EXPRESSION_CHANGED));
    }

    @Test
    public void canLogLeftBorderTextFieldChanged() {
        viewModel.setLeftBorderValue("-2");

        assertTrue(viewModel.getLogMessages().toString()
                .contains(ViewModel.LOG_LEFT_BORDER_INPUT + " "
                        + ViewModel.LOG_INPUT_EXPRESSION_CHANGED));
    }

    @Test
    public void canLogRightBorderTextFieldChanged() {
        viewModel.setRightBorderValue("-2");

        assertTrue(viewModel.getLogMessages().toString()
                .contains(ViewModel.LOG_RIGHT_BORDER_INPUT + " "
                        + ViewModel.LOG_INPUT_EXPRESSION_CHANGED));
    }


    @Test
    public void canLogSplitsNumberTextFiledChanged() {
        viewModel.setSplitsNumber("-2");

        assertTrue(viewModel.getLogMessages().toString()
                .contains(ViewModel.LOG_SPLITS_NUMBER_INPUT + " "
                        + ViewModel.LOG_INPUT_EXPRESSION_CHANGED));
    }

}
