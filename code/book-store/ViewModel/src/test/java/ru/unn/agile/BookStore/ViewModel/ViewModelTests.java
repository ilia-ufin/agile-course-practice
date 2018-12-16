package ru.unn.agile.BookStore.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    private void setInputData() {
        viewModel.books1Property().set("1");
        viewModel.books2Property().set("2");
        viewModel.books3Property().set("3");
        viewModel.books4Property().set("4");
        viewModel.books5Property().set("5");
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getBooks1());
        assertEquals("", viewModel.getBooks2());
        assertEquals("", viewModel.getBooks3());
        assertEquals("", viewModel.getBooks4());
        assertEquals("", viewModel.getBooks5());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithNonSetFields() {
        viewModel.calculate();

        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.books1Property().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingWhenNotEnoughFieldsAreFill() {
        viewModel.books1Property().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.books1Property().set("trash");

        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.books1Property().set("1");

        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.isCalculationDisabled());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void calculationHasCorrectResult() {
        viewModel.books1Property().set("1");
        viewModel.books2Property().set("3");
        viewModel.books3Property().set("2");
        viewModel.books4Property().set("1");
        viewModel.books5Property().set("5");

        viewModel.calculate();

        assertEquals("82.8", viewModel.getResult());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.books5Property().set("#selfie");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }
}
