package ru.unn.agile.vector3d.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel(new FakeLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    protected void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Test
    public void canSetInitialVectorValues() {
        String emptyString = "";

        StringProperty initialX = viewModel.vectorXProperty();
        StringProperty initialY = viewModel.vectorYProperty();
        StringProperty initialZ = viewModel.vectorZProperty();

        assertEquals(emptyString, initialX.get());
        assertEquals(emptyString, initialY.get());
        assertEquals(emptyString, initialZ.get());
    }

    @Test
    public void canSetInitialOtherVectorValues() {
        String emptyString = "";

        StringProperty initialOtherX = viewModel.otherVectorXProperty();
        StringProperty initialOtherY = viewModel.otherVectorYProperty();
        StringProperty initialOtherZ = viewModel.otherVectorZProperty();

        assertEquals(emptyString, initialOtherX.get());
        assertEquals(emptyString, initialOtherY.get());
        assertEquals(emptyString, initialOtherZ.get());
    }

    @Test
    public void canSetDefaultMultiplicationCoeffAndResult() {
        String emptyString = "";

        String defaultMultCoeff = viewModel.getMultiplicationCoeff();
        String defaultResult = viewModel.getResult();

        assertEquals(emptyString, defaultMultCoeff);
        assertEquals(emptyString, defaultResult);
    }


    @Test
    public void canSetDefaultOperationAndStatus() {
        ObjectProperty<Operation> defaultOperation = viewModel.operationProperty();
        StringProperty defaultStatus = viewModel.statusProperty();

        assertEquals(Operation.ADD, defaultOperation.get());
        assertEquals(ViewModel.Status.WAITING.toString(), defaultStatus.get());
        assertNotNull(viewModel.getOperations());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        StringProperty expectedStatus = viewModel.statusProperty();
        assertEquals(ViewModel.Status.WAITING.toString(), expectedStatus.get());
    }

    @Test
    public void buttonIsDisabledWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        BooleanProperty expectedButtonState = viewModel.calculationDisabledProperty();
        assertTrue(expectedButtonState.get());
    }

    @Test
    public void statusIsReadyWhenCalculateWithEmptyFields() {
        setInputData();

        StringProperty expectedStatus = viewModel.statusProperty();
        assertEquals(ViewModel.Status.READY.toString(), expectedStatus.get());
    }

    @Test
    public void canReportBadFormat() {
        setInputData();
        viewModel.vectorYProperty().set("aa");

        StringProperty expectedStatus = viewModel.statusProperty();
        assertEquals(ViewModel.Status.BAD_FORMAT.toString(), expectedStatus.get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.vectorXProperty().set("trash");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.vectorXProperty().set("1");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetAddOperation() {
        viewModel.operationProperty().set(Operation.ADD);

        assertEquals(Operation.ADD, viewModel.operationProperty().get());
    }

    @Test
    public void addIsDefaultOperation() {
        assertEquals(Operation.ADD, viewModel.operationProperty().get());
    }

    @Test
    public void operationAddHasCorrectResult() {
        setInputData();

        viewModel.calculate();

        assertEquals("(4.1, -1.9, 9.3)", viewModel.resultProperty().get());
    }

    @Test
    public void operationSubtractHasCorrectResult() {
        setInputData();
        viewModel.operationProperty().set(Operation.SUBTRACT);

        viewModel.calculate();

        assertEquals("(2.1, -5.9, 3.3)", viewModel.resultProperty().get());
    }

    @Test
    public void operationDotHasCorrectResult() {
        setInputData();
        viewModel.operationProperty().set(Operation.DOT);

        viewModel.calculate();

        assertEquals("14.2", viewModel.resultProperty().get());
    }

    @Test
    public void canCheckBadFormatWhenOperationIsDot() {
        setInputData();
        viewModel.operationProperty().set(Operation.DOT);

        viewModel.otherVectorXProperty().set("aa");

        StringProperty expectedStatus = viewModel.statusProperty();
        assertEquals(ViewModel.Status.BAD_FORMAT.toString(), expectedStatus.get());
    }

    @Test
    public void operationMultiplyHasCorrectResult() {
        viewModel.vectorXProperty().set("4");
        viewModel.vectorYProperty().set("-3");
        viewModel.vectorZProperty().set("5");
        viewModel.multiplicationCoeffProperty().set("2");
        viewModel.operationProperty().set(Operation.MULTIPLY);

        viewModel.calculate();

        assertEquals("(8.0, -6.0, 10.0)", viewModel.resultProperty().get());
    }

    @Test
    public void canCheckBadFormatWhenOperationIsMultiply() {
        viewModel.vectorXProperty().set("1");
        viewModel.vectorYProperty().set("-2");
        viewModel.vectorZProperty().set("3");
        viewModel.operationProperty().set(Operation.MULTIPLY);

        viewModel.multiplicationCoeffProperty().set("aa");

        StringProperty expectedStatus = viewModel.statusProperty();
        assertEquals(ViewModel.Status.BAD_FORMAT.toString(), expectedStatus.get());
    }

    @Test
    public void buttonIsActiveWhenOperationIsMultiply() {
        viewModel.vectorXProperty().set("1");
        viewModel.vectorYProperty().set("-2");
        viewModel.vectorZProperty().set("3");
        viewModel.multiplicationCoeffProperty().set("2");

        viewModel.operationProperty().set(Operation.MULTIPLY);

        assertFalse(viewModel.isCalculationDisabled());
    }

    @Test
    public void operationMagnitudeHasCorrectResult() {
        viewModel.vectorXProperty().set("1");
        viewModel.vectorYProperty().set("2");
        viewModel.vectorZProperty().set("3");
        viewModel.operationProperty().set(Operation.MAGNITUDE);

        viewModel.calculate();

        assertEquals("3.7416573867739413", viewModel.resultProperty().get());
    }

    @Test
    public void operationNormalizeHasCorrectResult() {
        viewModel.vectorXProperty().set("-80");
        viewModel.vectorYProperty().set("0");
        viewModel.vectorZProperty().set("60");
        viewModel.operationProperty().set(Operation.NORMALIZE);

        viewModel.calculate();

        assertEquals("(-0.8, 0.0, 0.6)", viewModel.resultProperty().get());
    }

    @Test
    public void operationCrossHasCorrectResult() {
        viewModel.vectorXProperty().set("-7");
        viewModel.vectorYProperty().set("-17");
        viewModel.vectorZProperty().set("1");

        viewModel.otherVectorXProperty().set("-2");
        viewModel.otherVectorYProperty().set("8");
        viewModel.otherVectorZProperty().set("9");
        viewModel.operationProperty().set(Operation.CROSS);

        viewModel.calculate();

        assertEquals("(-161.0, 61.0, -90.0)", viewModel.resultProperty().get());
    }

    @Test
    public void buttonIsActiveWhenChangeOperationWithoutOtherVector() {
        setInputData();
        viewModel.otherVectorXProperty().set("aaa");

        viewModel.operationProperty().set(Operation.NORMALIZE);

        assertFalse(viewModel.isCalculationDisabled());
    }

    @Test(expected = IllegalArgumentException.class)
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        new ViewModel(null);
    }

    @Test
    public void logIsEmptyAfterInit() {
        List<String> log = viewModel.getLogList();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsOperationChangedMessage() {
        viewModel.operationProperty().setValue(Operation.NORMALIZE);

        String message = viewModel.getLogList().get(0);
        String expectedMessage = String.format(ViewModel.LogMessages.OPERATION_WAS_CHANGED,
                Operation.NORMALIZE);
        assertTrue(message.contains(expectedMessage));
    }

    @Test
    public void operationIsNotLoggedIfNotChanged() {
        viewModel.operationProperty().setValue(Operation.NORMALIZE);

        viewModel.operationProperty().setValue(Operation.NORMALIZE);

        assertEquals(1, viewModel.getLogList().size());
    }

    @Test
    public void logContainsMultCoefChangedMessage() {
        viewModel.multiplicationCoeffProperty().setValue("2.0");

        String message = viewModel.getLogList().get(0);
        String expectedMessage = String.format(ViewModel.LogMessages.MULT_COEF_WAS_CHANGED, "2.0");
        assertTrue(message.contains(expectedMessage));
    }

    @Test
    public void logContainsVectorsChangedMessageOnSingleFieldChange() {
        viewModel.vectorXProperty().setValue("1.0");

        String message = viewModel.getLogList().get(0);
        String expectedMessage = getExpectedVectorsChangedMessage();
        assertTrue(message.contains(expectedMessage));
    }

    @Test
    public void logContainsMultipleVectorChangedMessages() {
        setInputData();

        List<String> log = viewModel.getLogList();
        assertEquals(log.size(), 6);

        String message = viewModel.getLogList().get(5);
        String expectedMessage = getExpectedVectorsChangedMessage();
        assertTrue(message.contains(expectedMessage));
    }

    @Test
    public void logContainsCalculateMessage() {
        setInputData();
        viewModel.operationProperty().setValue(Operation.DOT);

        viewModel.calculate();

        String message = viewModel.getLogList().get(viewModel.getLogList().size() - 1);
        String expectedMessage = String.format(ViewModel.LogMessages.CALCULATE_WAS_PRESSED,
                viewModel.getVectorX(), viewModel.getVectorY(), viewModel.getVectorZ(),
                viewModel.getOtherVectorX(), viewModel.getOtherVectorY(),
                viewModel.getOtherVectorZ(), viewModel.getMultiplicationCoeff(),
                viewModel.getOperation());
        assertTrue(message.contains(expectedMessage));
    }

    @Test
    public void logPropertyIsEmptyAfterInit() {
        assertEquals("", viewModel.getLog());
        assertEquals("", viewModel.logProperty().get());
    }

    @Test
    public void logPropertyMirrorsLog() {
        setInputData();
        viewModel.operationProperty().setValue(Operation.CROSS);

        viewModel.calculate();

        List<String> log = viewModel.getLogList();
        StringBuilder expectedLog = new StringBuilder();
        for (String line : log) {
            expectedLog.append(line).append("\n");
        }
        assertTrue(viewModel.logProperty().get().contains(expectedLog.toString()));
    }

    @Test
    public void canCreateViewModelWithoutLogger() {
        ViewModel vm = new ViewModel();

        assertNotNull(vm);
    }

    @Test
    public void gettingStatusWithWrongNameReturnsNull() {
        ViewModel.Status status = ViewModel.Status.getByName("BAD NAME");

        assertNull(status);
    }

    @Test
    public void canCreateLogMessage() {
        ViewModel.LogMessages logMsg = new ViewModel.LogMessages();

        assertNotNull(logMsg);
    }

    private void setInputData() {
        viewModel.vectorXProperty().set("3.1");
        viewModel.vectorYProperty().set("-3.9");
        viewModel.vectorZProperty().set("6.3");

        viewModel.otherVectorXProperty().set("1");
        viewModel.otherVectorYProperty().set("2");
        viewModel.otherVectorZProperty().set("3");
    }

    private String getExpectedVectorsChangedMessage() {
        return String.format(ViewModel.LogMessages.VECTORS_WERE_CHANGED,
                viewModel.vectorXProperty().get(),
                viewModel.vectorYProperty().get(),
                viewModel.vectorZProperty().get(),
                viewModel.otherVectorXProperty().get(),
                viewModel.otherVectorYProperty().get(),
                viewModel.otherVectorZProperty().get());
    }
}
