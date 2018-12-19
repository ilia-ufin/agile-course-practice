package ru.unn.agile.fibonacciHeap.viewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ViewModelTest {
    private ViewModel viewModel;
    private final String emptyError = "";

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void shouldSetDefaultValues() {
        assertEquals("", viewModel.errorProperty().get());
        assertEquals("", viewModel.inputValueProperty().get());
        assertEquals("", viewModel.minElemProperty().get());
        assertEquals("", viewModel.heapLengthProperty().get());
    }

    @Test
    public void shouldSetLengthLabel() {
        viewModel.inputValueProperty().setValue("1");
        viewModel.addElem();

        assertEquals("1", viewModel.heapLengthProperty().get());
    }

    @Test
    public void shouldSetMinLabel() {
        viewModel.inputValueProperty().setValue("1");
        viewModel.addElem();

        assertEquals("1", viewModel.minElemProperty().get());
    }

    @Test
    public void shouldClearInputAfterAppyingl() {
        viewModel.inputValueProperty().setValue("1");
        viewModel.addElem();

        assertEquals("", viewModel.inputValueProperty().get());
    }

    @Test
    public void shouldReportBadFormat() {
        viewModel.inputValueProperty().setValue("asd");
        viewModel.addElem();

        assertNotEquals(emptyError, viewModel.errorProperty().get());
    }
}
