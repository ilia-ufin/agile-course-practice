package ru.unn.agile.fraction.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.unn.agile.fraction.model.Fraction.Operation;

import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;

    private void setInputData(final String fn, final String fd,
                              final String sn, final String sd) {
        viewModel.firstNumeratorProperty().set(fn);
        viewModel.firstDenominatorProperty().set(fd);
        viewModel.secondNumeratorProperty().set(sn);
        viewModel.secondDenominatorProperty().set(sd);
    }

    private void assertFraction(final String rn, final String rd) {
        assertEquals(rn, viewModel.resultNumeratorProperty().get());
        assertEquals(rd, viewModel.resultDenominatorProperty().get());
    }

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void initViewModelWithDefaultValues() {
        assertEquals("", viewModel.firstNumeratorProperty().get());
        assertEquals("", viewModel.firstDenominatorProperty().get());
        assertEquals("", viewModel.secondNumeratorProperty().get());
        assertEquals("", viewModel.secondDenominatorProperty().get());
        assertEquals(Operation.ADD, viewModel.operationProperty().get());
        assertEquals("", viewModel.resultNumeratorProperty().get());
        assertEquals("", viewModel.resultDenominatorProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void viewModelPropertiesAreInitializedUponStart() {
        assertNotEquals(null, viewModel.firstNumeratorProperty().get());
        assertNotEquals(null, viewModel.firstDenominatorProperty().get());
        assertNotEquals(null, viewModel.secondNumeratorProperty().get());
        assertNotEquals(null, viewModel.secondDenominatorProperty().get());
        assertNotEquals(null, viewModel.resultNumeratorProperty().get());
        assertNotEquals(null, viewModel.resultDenominatorProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData("1", "2", "3", "4");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsBadWhenFieldWrongFormat() {
        viewModel.firstNumeratorProperty().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.firstNumeratorProperty().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenInit() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData("4", "5", "6", "7");
        viewModel.firstNumeratorProperty().set("b");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.firstDenominatorProperty().set("1");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData("1", "2", "3", "4");

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetAddOperation() {
        Operation addOperation = Operation.ADD;

        viewModel.operationProperty().set(addOperation);

        assertEquals(addOperation, viewModel.operationProperty().get());
    }

    @Test
    public void addIsDefaultOperation() {
        Operation defaultOperation = Operation.ADD;
        Operation initOperation = viewModel.operationProperty().get();

        assertEquals(defaultOperation, initOperation);
    }

    @Test
    public void canCalculateWithEmptyFieldsAndStatusWaiting() {
        viewModel.calculate();

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void operationAddHasCorrectResult() {
        setInputData("1", "2", "1", "3");

        viewModel.calculate();

        assertFraction("5", "6");
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData("1", "2", "6", "8");

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.secondNumeratorProperty().set("c");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetCorrectData() {
        setInputData("12", "23", "11", "30");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void operationSubstractHasCorrectResult() {
        setInputData("4", "5", "5", "13");
        viewModel.operationProperty().set(Operation.SUBSTRACT);

        viewModel.calculate();

        assertFraction("27", "65");
    }

    @Test
    public void operationMultiplyHasCorrectResult() {
        setInputData("3", "4", "2", "7");
        viewModel.operationProperty().set(Operation.MULTIPLY);

        viewModel.calculate();

        assertFraction("3", "14");
    }

    @Test
    public void operationDivideHasCorrectResult() {
        setInputData("13", "44", "23", "22");
        viewModel.operationProperty().set(Operation.DIVIDE);

        viewModel.calculate();

        assertFraction("13", "46");
    }
}
