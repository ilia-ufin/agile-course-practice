package ru.unn.agile.stringcalculator.viewmodel;

import javafx.beans.property.StringProperty;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ViewModelTest {
    private ViewModel viewModel;

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        if (viewModel == null) {
            viewModel = new ViewModel();
        }
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
        assertEquals(Operation.ADD, viewModel.getOperation());
    }

    @Test
    public void hasAddOperationCorrectResultWhenInputDataIsCorrect() {
        setInputData("31,8");

        viewModel.calculate();

        assertEquals("39", viewModel.getResult());
    }

    @Test
    public void isItImpossibleToCalculateWhenButtonInActive() {
        setInputData("21.8");

        viewModel.calculate();

        assertNull(viewModel.resultProperty().get());
    }

    @Test
    public void operationsListIsNotNull() {
        assertNotNull(viewModel.operationProperty());
    }

    @Test
    public void operationsListContainsOnlyAddOperation() {
        assertEquals(Operation.ADD, viewModel.getOperations().get(0));
    }

    @Test
    public void statusIsWaitingWhenInputFieldIsEmpty() {
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsBadFormatWhenInputDataIsNotCorrect() {
        setInputData("31.8,5");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenInputDataIsCorrect() {
        setInputData("66,8");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsSuccessWhenCalculationPerformedSuccessfully() {
        setInputData("71,8");

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void isCalculateButtonNotActiveWhenStatusIsWaiting() {
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void isCalculateButtonNotActiveWhenStatusIsBadFormat() {
        setInputData("31.8,5");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void isCalculateButtonActiveWhenStatusIsReady() {
        setInputData("31,8");

        assertFalse(viewModel.isCalculationDisabled());
    }

    protected void setInputData(final String str) {
        viewModel.inputDataProperty().set(str);
    }

}
