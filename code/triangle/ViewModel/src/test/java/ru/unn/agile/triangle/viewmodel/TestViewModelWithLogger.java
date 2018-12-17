package ru.unn.agile.triangle.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestViewModelWithLogger {
    private ViewModel viewModel;

    protected void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void init() {
        viewModel = new ViewModel(new FakeLog());
    }

    @After
    public void destroy() {
        viewModel = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void canInitEmptyLogger() {
        viewModel.setLogger(null);
    }

    @Test
    public void canLogLogger() {
        viewModel.setLogs("q");

        assertEquals("q", viewModel.getLogs());
    }

    @Test
    public void canChangeFocusXProperty() {
        viewModel.aXProperty().set("1");
        viewModel.checkOnFocusChanged(false, false);

        assertEquals("Updated input. Input arguments are: 1, , , , ,  \n", viewModel.getLogs());
    }

}
