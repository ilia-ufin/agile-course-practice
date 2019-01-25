package ru.unn.agile.myhashmap.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewMod;

    public void setViewModel(final ViewModel viewMod) {
        this.viewMod = viewMod;
    }

    @Before
    public void setUp() {
        viewMod = new ViewModel(new FakeLogger());
    }

    @After
    public void tearDown() {
        viewMod = null;
    }
	
	@Test
    public void canCreateViewModelNotLogger() {
        ViewModel newViewMod = new ViewModel();

        assertNotNull(newViewMod);
    }
    
    @Test
    public void validDefaultStatusMessage() {
        assertEquals(viewMod.WAITING_FOR_INPUT, viewMod.statusMessageProperty().get());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void canNotCreateViewModelWithNullLogger() {
        ViewModel newViewMod = new ViewModel(null);
    }
    /*
    @Test
    public void canSetDefaultValues() {
        assertEquals(viewModel.IS_EMPTY, viewModel.getIsEmptyStatusProperty());
        assertEquals("0", viewModel.getmapSizeProperty());
    }
    */
    
    @Test
    public void canCreateViewModelNotNullLogger() {
        ViewModel newViewMod = new ViewModel(new FakeLogger());

        assertNotNull(newViewMod);
    }


}
