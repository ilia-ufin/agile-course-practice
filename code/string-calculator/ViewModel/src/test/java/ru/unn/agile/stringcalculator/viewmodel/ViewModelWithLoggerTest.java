package ru.unn.agile.stringcalculator.viewmodel;

import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ViewModelWithLoggerTest extends ViewModelTest {
    private ViewModel viewModel;

    @Override
    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
        super.setExternalViewModel(this.viewModel);
    }

    @Override
    public void setUp() {
        if (viewModel == null) {
            FakeLogger fakeLogger = new FakeLogger();
            viewModel = new ViewModel(fakeLogger);
            super.setExternalViewModel(viewModel);
        }
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        new ViewModel(null);
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterCalculation() {
        setInputData("2,1");
        viewModel.calculate();
        String logMsg = viewModel.getLog().get(0);

        assertTrue(logMsg.matches(".*" + LogMessages.CALCULATE_WAS_PRESSED.toString() + ".*"));
    }

    @Test
    public void logContainsInputStringAfterCalculation() {
        setInputData("25");

        viewModel.calculate();

        String logMsg = viewModel.getLog().get(0);
        assertTrue(logMsg.matches(".*" + viewModel.inputDataProperty().get()));
    }

    @Test
    public void stringInputIsProperlyFormatted() {
        setInputData("12,1");

        viewModel.calculate();

        String logMsg = viewModel.getLog().get(0);
        assertTrue(logMsg.matches(".*String: " + viewModel.inputDataProperty().get() + ".*"));
    }

    @Test
    public void typeDefaultOperationIsCorrectlyMentionedInLog() {
        setInputData("13");

        viewModel.calculate();

        String logMsg = viewModel.getLog().get(0);
        assertTrue(logMsg.matches(".*ADD.*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        setInputData("33");
        viewModel.calculate();

        setInputData("88");
        viewModel.calculate();

        setInputData("4");
        viewModel.calculate();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void loggingDoesNotOccurWhenButtonIsDisabled() {
        viewModel.calculate();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void areaLogIsEmptyWhenButtonIsDisabled() {
        viewModel.calculate();

        assertNull(viewModel.areaLogProperty().get());
    }

    @Test
    public void argumentsAreCorrectlyLogged() {
        setInputData("6,3");

        viewModel.subsChanged(Boolean.TRUE, Boolean.FALSE);

        String logMsg = viewModel.getLog().get(0);
        assertTrue(logMsg.matches(".*" + LogMessages.EDITING_FINISHED
                + "Input string: \\[ " + viewModel.inputDataProperty().get() + " \\]"));
    }

    @Test
    public void areaLogContainsLogMessagesAfterCalculation() {
        setInputData("3,3");

        viewModel.calculate();

        assertFalse(viewModel.getAreaLog().isEmpty());
    }

    @Test
    public void areaLogMessageIsEqualsLogMessage() {
        setInputData("5,6");

        viewModel.calculate();

        assertEquals(viewModel.getLog().get(0) + "\n", viewModel.getAreaLog());
    }

    @Test
    public void doNotLogSameParametersTwice() {
        viewModel.subsChanged(Boolean.FALSE, Boolean.TRUE);
        setInputData("12");
        viewModel.subsChanged(Boolean.TRUE, Boolean.FALSE);
        setInputData("12");
        viewModel.subsChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }

}
