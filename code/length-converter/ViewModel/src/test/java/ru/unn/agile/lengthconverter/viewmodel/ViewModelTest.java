package ru.unn.agile.lengthconverter.viewmodel;

import org.junit.After;
import org.junit.Before;

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
}
