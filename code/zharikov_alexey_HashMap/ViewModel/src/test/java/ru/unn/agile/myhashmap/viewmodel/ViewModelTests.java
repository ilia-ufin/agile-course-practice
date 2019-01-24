package ru.unn.agile.myhashmap.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void correctDefaultStatusMessage() {
        assertEquals(viewModel.WAITING_FOR_INPUT, viewModel.statusMessageProperty().get());
    }
    /*
    @Test
    public void canSetDefaultValues() {
        assertEquals(viewModel.IS_EMPTY, viewModel.getIsEmptyStatusProperty());
        assertEquals("0", viewModel.getmapSizeProperty());
    }
    */
}
