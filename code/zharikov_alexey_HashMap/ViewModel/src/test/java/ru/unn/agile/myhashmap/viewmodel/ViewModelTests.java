package ru.unn.agile.myhashmap.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        viewModel = new ViewModel(new FakeLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotCreateViewModelWithNullLogger() {
        ViewModel newViewModel = new ViewModel(null);
    }

    @Test
    public void correctDefaultStatusMessage() {
        assertEquals(viewModel.WAITING_FOR_INPUT, viewModel.statusMessageProperty().get());
    }

    @Test
    public void canCreateViewModelBezLogger() {
        ViewModel newViewModel = new ViewModel();

        assertNotNull(newViewModel);
    }


    /*
    @Test
    public void canSetDefaultValues() {
        assertEquals(viewModel.IS_EMPTY, viewModel.getIsEmptyStatusProperty());
        assertEquals("0", viewModel.getmapSizeProperty());
    }
    */
    @Test
    public void canCreateViewModelWithNotNullLogger() {
        ViewModel newViewModel = new ViewModel(new FakeLogger());

        assertNotNull(newViewModel);
    }


}
