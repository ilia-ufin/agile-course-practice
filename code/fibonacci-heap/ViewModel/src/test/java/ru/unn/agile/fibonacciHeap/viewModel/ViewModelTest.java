package ru.unn.agile.fibonacciHeap.viewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;
    private final String emptyError = "";

    public void setViewModel(final ViewModel vm) {
        this.viewModel = vm;
    }

    @Before
    public void setUp() {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
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
    public void shouldLogElementAddition() {
        viewModel.inputValueProperty().setValue("1");
        viewModel.addElem();

        List<String> log = viewModel.getLog();
        assertTrue(containtLine(log, "Добавлен элемент 1"));
    }

    @Test
    public void shouldLogLengthChange() {
        viewModel.inputValueProperty().setValue("1");
        viewModel.addElem();

        List<String> log = viewModel.getLog();
        assertTrue(containtLine(log, "Кол-во узлов в куче стало 1"));
    }

    @Test
    public void shouldLogMinChange() {
        viewModel.inputValueProperty().setValue("1");
        viewModel.addElem();

        List<String> log = viewModel.getLog();
        assertTrue(containtLine(log, "Минимальный элемент стал 1"));
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

    public boolean containtLine(final List<String> log, final String logLine) {
        return log.stream().filter(o -> o.contains(logLine)).findFirst().isPresent();
    }
}
