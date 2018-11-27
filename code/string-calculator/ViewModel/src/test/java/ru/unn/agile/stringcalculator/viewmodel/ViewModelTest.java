package ru.unn.agile.stringcalculator.viewmodel;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
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
    public void canSetInitialInputData() {
        StringProperty initialInputData = viewModel.inputDataProperty();

        assertEquals("", initialInputData.get());
    }

    @Test
    public void canSetAddOperation() {
        assertEquals(Operation.ADD, viewModel.operationProperty().get());
    }


    @Test
    public void hasAddOperationCorrectResultWhenInputDataIsCorrect() {
        viewModel.inputDataProperty().set("31,8");

        viewModel.calculate();

        assertEquals("39", viewModel.resultProperty().get());
    }

    @Test
    public void isItImpossibleToCalculateWhenButtonInActive() {
        viewModel.inputDataProperty().set("31.8");

        viewModel.calculate();

        assertNull(viewModel.resultProperty().get());
    }

    @Test
    public void operationsListContainsOnlyAddOperation() {
        ObservableList operationsList = viewModel.getOperations();

        assertEquals(Operation.ADD, operationsList.get(0));
    }

    @Test
    public void statusIsWaitingWhenInputFieldIsEmpty() {
        StringProperty expectedStatus = viewModel.statusProperty();

        assertEquals(Status.WAITING.toString(), expectedStatus.get());
    }

    @Test
    public void statusIsBadFormatWhenInputDataIsNotCorrect() {
        viewModel.inputDataProperty().set("31.8,5");

        StringProperty expectedStatus = viewModel.statusProperty();
        assertEquals(Status.BAD_FORMAT.toString(), expectedStatus.get());
    }

    @Test
    public void statusIsReadyWhenInputDataIsCorrect() {
        viewModel.inputDataProperty().set("31,8");

        StringProperty expectedStatus = viewModel.statusProperty();
        assertEquals(Status.READY.toString(), expectedStatus.get());
    }

    @Test
    public void statusIsSuccesWhenCalculationPerformedSuccessfully() {
        viewModel.inputDataProperty().set("31,8");

        viewModel.calculate();

        StringProperty expectedStatus = viewModel.statusProperty();
        assertEquals(Status.SUCCESS.toString(), expectedStatus.get());
    }

    @Test
    public void isCalculateButtonNotActiveWhenStatusIsWaiting() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void isCalculateButtonNotActiveWhenStatusIsBadFormat() {
        viewModel.inputDataProperty().set("31.8,5");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void isCalculateButtonActiveWhenStatusIsReady() {
        viewModel.inputDataProperty().set("31,8");

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

}
