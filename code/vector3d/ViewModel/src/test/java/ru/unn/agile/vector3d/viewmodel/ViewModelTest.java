package ru.unn.agile.vector3d.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetInitialVectorValues() {
        // Arrange
        String emptyString = "";

        // Act
        StringProperty initialX = viewModel.vectorXProperty();
        StringProperty initialY = viewModel.vectorYProperty();
        StringProperty initialZ = viewModel.vectorZProperty();

        // Assert
        assertEquals(emptyString, initialX.get());
        assertEquals(emptyString, initialY.get());
        assertEquals(emptyString, initialZ.get());
    }

    @Test
    public void canSetInitialOtherVectorValues() {
        // Arrange
        String emptyString = "";

        // Act
        StringProperty initialOtherX = viewModel.otherVectorXProperty();
        StringProperty initialOtherY = viewModel.otherVectorYProperty();
        StringProperty initialOtherZ = viewModel.otherVectorZProperty();

        // Assert
        assertEquals(emptyString, initialOtherX.get());
        assertEquals(emptyString, initialOtherY.get());
        assertEquals(emptyString, initialOtherZ.get());
    }

    @Test
    public void canSetDefaultMultiplicationCoeffAndResult() {
        // Arrange
        String emptyString = "";

        // Act
        StringProperty defaultMultCoeff = viewModel.multiplicationCoeffProperty();
        StringProperty defaultResult = viewModel.resultProperty();

        // Assert
        assertEquals(emptyString, defaultMultCoeff.get());
        assertEquals(emptyString, defaultResult.get());
    }


    @Test
    public void canSetDefaultOperationAndStatus() {
        // Arrange & Act
        ObjectProperty<Operation> defaultOperation = viewModel.operationProperty();
        StringProperty defaultStatus = viewModel.statusProperty();

        // Assert
        assertEquals(Operation.ADD, defaultOperation.get());
        assertEquals(ViewModel.Status.WAITING.toString(), defaultStatus.get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        // Arrange & Act
        viewModel.calculate();

        // Assert
        StringProperty expectedStatus = viewModel.statusProperty();
        assertEquals(ViewModel.Status.WAITING.toString(), expectedStatus.get());
    }

    @Test
    public void buttonIsDisabledWhenCalculateWithEmptyFields() {
        // Arrange & Act
        viewModel.calculate();

        // Assert
        BooleanProperty expectedButtonState = viewModel.calculationDisabledProperty();
        assertTrue(expectedButtonState.get());
    }

    @Test
    public void statusIsReadyWhenCalculateWithEmptyFields() {
        // Arrange & Act
        setInputData();

        // Assert
        StringProperty expectedStatus = viewModel.statusProperty();
        assertEquals(ViewModel.Status.READY.toString(), expectedStatus.get());
    }

    @Test
    public void canReportBadFormat() {
        // Arrange & Act
        setInputData();
        viewModel.vectorYProperty().set("aa");

        // Assert
        StringProperty expectedStatus = viewModel.statusProperty();
        assertEquals(ViewModel.Status.BAD_FORMAT.toString(), expectedStatus.get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        // Arrange & Act
        setInputData();
        viewModel.vectorXProperty().set("trash");

        // Assert
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        // Arrange & Act
        setInputData();

        // Assert
        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        // Arrange & Act
        viewModel.vectorXProperty().set("1");

        // Assert
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetAddOperation() {
        // Arrange & Act
        viewModel.operationProperty().set(Operation.ADD);

        // Assert
        assertEquals(Operation.ADD, viewModel.operationProperty().get());
    }

    @Test
    public void addIsDefaultOperation() {
        // Arrange & Act & Assert
        assertEquals(Operation.ADD, viewModel.operationProperty().get());
    }

    @Test
    public void operationAddHasCorrectResult() {
        // Arrange
        setInputData();

        // Act
        viewModel.calculate();

        // Assert
        assertEquals("(4.1, -1.9, 9.3)", viewModel.resultProperty().get());
    }

    @Test
    public void operationSubtractHasCorrectResult() {
        // Arrange
        setInputData();
        viewModel.operationProperty().set(Operation.SUBTRACT);

        // Act
        viewModel.calculate();

        // Assert
        assertEquals("(2.1, -5.9, 3.3)", viewModel.resultProperty().get());
    }

    @Test
    public void operationDotHasCorrectResult() {
        // Arrange
        setInputData();
        viewModel.operationProperty().set(Operation.DOT);

        // Act
        viewModel.calculate();

        // Assert
        assertEquals("14.2", viewModel.resultProperty().get());
    }

    @Test
    public void canCheckBadFormatWhenOperationIsDot() {
        // Arrange
        setInputData();
        viewModel.operationProperty().set(Operation.DOT);

        // Act
        viewModel.otherVectorXProperty().set("aa");

        // Assert
        StringProperty expectedStatus = viewModel.statusProperty();
        assertEquals(ViewModel.Status.BAD_FORMAT.toString(), expectedStatus.get());
    }

    @Test
    public void operationMultiplyHasCorrectResult() {
        // Arrange
        viewModel.vectorXProperty().set("4");
        viewModel.vectorYProperty().set("-3");
        viewModel.vectorZProperty().set("5");
        viewModel.multiplicationCoeffProperty().set("2");
        viewModel.operationProperty().set(Operation.MULTIPLY);

        // Act
        viewModel.calculate();

        // Assert
        assertEquals("(8.0, -6.0, 10.0)", viewModel.resultProperty().get());
    }

    @Test
    public void canCheckBadFormatWhenOperationIsMultiply() {
        // Arrange
        viewModel.vectorXProperty().set("1");
        viewModel.vectorYProperty().set("-2");
        viewModel.vectorZProperty().set("3");
        viewModel.operationProperty().set(Operation.MULTIPLY);

        // Act
        viewModel.multiplicationCoeffProperty().set("aa");

        // Assert
        StringProperty expectedStatus = viewModel.statusProperty();
        assertEquals(ViewModel.Status.BAD_FORMAT.toString(), expectedStatus.get());
    }

    @Test
    public void buttonIsActiveWhenOperationIsMultiply() {
        // Arrange
        viewModel.vectorXProperty().set("1");
        viewModel.vectorYProperty().set("-2");
        viewModel.vectorZProperty().set("3");
        viewModel.multiplicationCoeffProperty().set("2");

        // Act1
        viewModel.operationProperty().set(Operation.MULTIPLY);

        // Assert
        assertFalse(viewModel.isCalculationDisabled());
    }

    @Test
    public void operationMagnitudeHasCorrectResult() {
        // Arrange
        viewModel.vectorXProperty().set("1");
        viewModel.vectorYProperty().set("2");
        viewModel.vectorZProperty().set("3");
        viewModel.operationProperty().set(Operation.MAGNITUDE);

        // Act
        viewModel.calculate();

        // Assert
        assertEquals("3.7416573867739413", viewModel.resultProperty().get());
    }

    @Test
    public void operationNormalizeHasCorrectResult() {
        // Arrange
        viewModel.vectorXProperty().set("-80");
        viewModel.vectorYProperty().set("0");
        viewModel.vectorZProperty().set("60");
        viewModel.operationProperty().set(Operation.NORMALIZE);

        // Act
        viewModel.calculate();

        // Assert
        assertEquals("(-0.8, 0.0, 0.6)", viewModel.resultProperty().get());
    }

    @Test
    public void operationCrossHasCorrectResult() {
        // Arrange
        viewModel.vectorXProperty().set("-7");
        viewModel.vectorYProperty().set("-17");
        viewModel.vectorZProperty().set("1");

        viewModel.otherVectorXProperty().set("-2");
        viewModel.otherVectorYProperty().set("8");
        viewModel.otherVectorZProperty().set("9");
        viewModel.operationProperty().set(Operation.CROSS);

        // Act
        viewModel.calculate();

        // Assert
        assertEquals("(-161.0, 61.0, -90.0)", viewModel.resultProperty().get());
    }

    @Test
    public void buttonIsActiveWhenChangeOperationWithoutOtherVector() {
        // Arrange
        setInputData();
        viewModel.otherVectorXProperty().set("aaa");

        // Act1
        viewModel.operationProperty().set(Operation.NORMALIZE);

        // Assert
        assertFalse(viewModel.isCalculationDisabled());
    }

    private void setInputData() {
        viewModel.vectorXProperty().set("3.1");
        viewModel.vectorYProperty().set("-3.9");
        viewModel.vectorZProperty().set("6.3");

        viewModel.otherVectorXProperty().set("1");
        viewModel.otherVectorYProperty().set("2");
        viewModel.otherVectorZProperty().set("3");
    }
}
