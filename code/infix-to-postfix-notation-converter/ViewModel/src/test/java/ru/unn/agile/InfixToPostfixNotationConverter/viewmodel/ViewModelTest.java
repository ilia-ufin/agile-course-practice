package ru.unn.agile.InfixToPostfixNotationConverter.viewmodel;

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

    private void enterValidInputData() {
        viewModel.infixExpressionProperty().set("1+1");
    }

    private void enterInvalidInputData() {
        viewModel.infixExpressionProperty().set("trash");
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.infixExpressionProperty().get());
        assertEquals("", viewModel.postfixExpressionProperty().get());
        assertEquals("", viewModel.expressionResultProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenInputFieldIsFilled() {
        enterValidInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        enterInvalidInputData();

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void convertButtonIsDisabledInitially() {
        assertTrue(viewModel.convertButtonDisabledProperty().get());
    }

    @Test
    public void convertButtonIsDisabledWhenFormatIsBad() {
        enterInvalidInputData();

        assertTrue(viewModel.convertButtonDisabledProperty().get());
    }

    @Test
    public void convertButtonIsEnabledWithCorrectInput() {
        enterValidInputData();

        assertFalse(viewModel.convertButtonDisabledProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        enterValidInputData();

        viewModel.convertAndCalculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void afterRemovingInputDataStatusIsWaiting() {
        viewModel.infixExpressionProperty().set("1+1");

        viewModel.infixExpressionProperty().set("");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canProduceCorrectOutputForSumOfTwoNumbers() {
        viewModel.infixExpressionProperty().set("123+321");
        String expectedPostfixNotation = "[123, 321, +]";
        String expectedResult = "444";

        viewModel.convertAndCalculate();

        assertEquals(expectedPostfixNotation, viewModel.postfixExpressionProperty().get());
        assertEquals(expectedResult, viewModel.expressionResultProperty().get());
    }
}

