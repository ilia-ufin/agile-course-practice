package ru.unn.agile.matrix.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import ru.unn.agile.matrix.viewmodel.ViewModel.Operation;
import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        if (viewModel == null) {
            viewModel = new ViewModel(new FakeLogger());
        }
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    private void enterOnlyOneInputField() {
        viewModel.matrixAProperty().setValue("[1]");
        viewModel.matrixBProperty().setValue("");
    }

    private void enterBadFormatInputData() {
        viewModel.matrixAProperty().setValue("[1][0 1]");
        viewModel.matrixBProperty().setValue("[0][0");
    }

    private void enterIncompatibleSizeMatrices() {
        viewModel.matrixAProperty().setValue("[1 2]");
        viewModel.matrixBProperty().setValue("[3]");
    }

    private void enterSameSizedMatrices() {
        viewModel.matrixAProperty().setValue("[1 0][0 1]");
        viewModel.matrixBProperty().setValue("[1 0][0 1]");
    }

    private String setupMatrixAdditionAndGetResult() {
        viewModel.operationProperty().setValue(Operation.ADD);
        viewModel.matrixAProperty().setValue("[1 0][0 1]");
        viewModel.matrixBProperty().setValue("[-1 0][0 -1]");

        return "[0.0 0.0][0.0 0.0]";
    }

    private String setupMatrixSubtractionAndGetResult() {
        String matrix = "[1 0][0 1]";
        viewModel.operationProperty().setValue(Operation.SUBTRACT);
        viewModel.matrixAProperty().setValue(matrix);
        viewModel.matrixBProperty().setValue(matrix);

        return "[0.0 0.0][0.0 0.0]";
    }

    private String setupMatrixMultiplicationAndGetResult() {
        String matrix = "[1 0][0 1]";
        viewModel.operationProperty().setValue(Operation.MULTIPLY);
        viewModel.matrixAProperty().setValue(matrix);
        viewModel.matrixBProperty().setValue(matrix);

        return "[1.0 0.0][0.0 1.0]";
    }

    private String getLastLog() {
        List<String> log = viewModel.getLog();
        if (log.isEmpty()) {
            return "";
        } else {
            return log.get(log.size() - 1);
        }
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("[1 2][3 4]", viewModel.matrixAProperty().get());
        assertEquals("[4 3][2 1]", viewModel.matrixBProperty().get());
        assertEquals(Operation.ADD, viewModel.operationProperty().get());
        assertEquals(false, viewModel.calculateButtonDisabledProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughDataIsEntered() {
        enterOnlyOneInputField();

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        enterBadFormatInputData();

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportIncompatibleSizeForAddition() {
        viewModel.operationProperty().setValue(Operation.ADD);
        enterIncompatibleSizeMatrices();

        assertEquals(Status.INCOMPATIBLE_SIZE.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportIncompatibleSizeForSubtraction() {
        viewModel.operationProperty().setValue(Operation.SUBTRACT);
        enterIncompatibleSizeMatrices();

        assertEquals(Status.INCOMPATIBLE_SIZE.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportIncompatibleSizeForMultiplication() {
        viewModel.operationProperty().setValue(Operation.MULTIPLY);
        enterIncompatibleSizeMatrices();

        assertEquals(Status.INCOMPATIBLE_SIZE.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenInputIsValid() {
        enterSameSizedMatrices();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void convertButtonIsDisabledIfNotEnoughDataIsEntered() {
        enterOnlyOneInputField();

        assertTrue(viewModel.calculateButtonDisabledProperty().get());
    }

    @Test
    public void convertButtonIsDisabledWhenFormatIsBad() {
        enterBadFormatInputData();

        assertTrue(viewModel.calculateButtonDisabledProperty().get());
    }

    @Test
    public void convertButtonIsDisabledWhenIncompatibleSizeMatricesAreEntered() {
        viewModel.operationProperty().setValue(Operation.ADD);
        enterIncompatibleSizeMatrices();

        assertTrue(viewModel.calculateButtonDisabledProperty().get());
    }

    @Test
    public void convertButtonIsEnabledWithCorrectInput() {
        enterSameSizedMatrices();

        assertFalse(viewModel.calculateButtonDisabledProperty().get());
    }

    @Test
    public void canGetSuccessMessage() {
        enterSameSizedMatrices();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canAddMatrices() {
        String expectedResult = setupMatrixAdditionAndGetResult();

        viewModel.calculate();

        assertEquals(expectedResult, viewModel.resultProperty().get());
    }

    @Test
    public void canSubtractMatrices() {
        String expectedResult = setupMatrixSubtractionAndGetResult();

        viewModel.calculate();

        assertEquals(expectedResult, viewModel.resultProperty().get());
    }

    @Test
    public void canMultiplyMatrices() {
        String expectedResult = setupMatrixMultiplicationAndGetResult();

        viewModel.calculate();

        assertEquals(expectedResult, viewModel.resultProperty().get());
    }

    @Test
    public void statusUpdatesWhenOperationIsChanged() {
        viewModel.operationProperty().setValue(Operation.MULTIPLY);
        viewModel.matrixAProperty().setValue("[1 2]");
        viewModel.matrixBProperty().setValue("[3][4]");

        viewModel.operationProperty().setValue(Operation.ADD);

        assertEquals(Status.INCOMPATIBLE_SIZE.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonAvailabilityUpdatesWhenOperationIsChanged() {
        viewModel.operationProperty().setValue(Operation.MULTIPLY);
        viewModel.matrixAProperty().setValue("[1 2]");
        viewModel.matrixBProperty().setValue("[3][4]");

        viewModel.operationProperty().setValue(Operation.ADD);

        assertTrue(viewModel.calculateButtonDisabledProperty().get());
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test (expected = IllegalArgumentException.class)
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        new ViewModel(null);
    }

    @Test
    public void logIsEmptyAtTheBeginning() {
        List<String> log = viewModel.getLog();

        assertEquals(0, log.size());
    }

    @Test
    public void calculatePutsSomethingToLog() {
        viewModel.calculate();

        List<String> log = viewModel.getLog();
        assertNotEquals(0, log.size());
    }

    @Test
    public void logContainsProperMessageAfterCalculateButtonIsPressed() {
        viewModel.calculate();
        String message = getLastLog();

        assertTrue(message.matches(".*" + LogMessages.CALCULATE_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterCalculateButtonIsPressed() {
        setupMatrixAdditionAndGetResult();

        viewModel.calculate();

        String message = getLastLog();
        assertTrue(message.matches(
                ".*" + viewModel.matrixAProperty().get()
                + ".*" + viewModel.matrixBProperty().get() + ".*"));
    }

    @Test
    public void argumentsInfoIsProperlyFormattedAfterCalculateButtonIsPressed() {
        setupMatrixAdditionAndGetResult();

        viewModel.calculate();

        String message = getLastLog();
        assertTrue(message.matches(".*"
                + viewModel.matrixAProperty().get()
                + viewModel.operationProperty().get().toString()
                + viewModel.matrixBProperty().get() + ".*"));
    }

    @Test
    public void operationIsMentionedInTheLogAfterCalculateButtonIsPressed() {
        setupMatrixAdditionAndGetResult();

        viewModel.calculate();

        String message = getLastLog();
        assertTrue(message.matches(".*" + Operation.ADD.toString() + ".*"));
    }

    @Test
    public void canLogSeveralMessages() {
        viewModel.calculate();
        viewModel.calculate();
        viewModel.calculate();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void operationIsLoggedWhenChanged() {
        viewModel.operationProperty().set(Operation.ADD);

        viewModel.operationProperty().set(Operation.MULTIPLY);

        String message = getLastLog();
        assertTrue(message.matches(
                ".*" + LogMessages.OPERATION_CHANGED + Operation.MULTIPLY.toString() + ".*"));
    }

    @Test
    public void operationIsNotLoggedIfNotChanged() {
        viewModel.operationProperty().set(Operation.MULTIPLY);

        viewModel.operationProperty().set(Operation.MULTIPLY);

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void argumentsAreCorrectlyLoggedAfterTheirEditing() {
        viewModel.matrixAProperty().set("[1]");
        viewModel.matrixBProperty().set("[0]");

        String message = getLastLog();
        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED
                + "A = \\[1\\]; B = \\[0\\].*"));
    }

    @Test
    public void calculateIsNotCalledWhenButtonIsDisabled() {
        enterIncompatibleSizeMatrices();

        viewModel.calculate();

        String message = getLastLog();
        assertFalse(message.matches(".*" + LogMessages.CALCULATE_PRESSED + ".*"));
    }

    @Test
    public void doNotLogSameInputParametersTwice() {
        String matrix = "[42 24][123 321]";
        viewModel.matrixAProperty().set(matrix);

        viewModel.matrixAProperty().set(matrix);

        assertEquals(1, viewModel.getLog().size());
    }
}

