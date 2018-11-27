package ru.unn.agile.quadraticequation.viewmodel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTest {

    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @Test
    public void canInitializeViewModel() {
        assertEquals("0", viewModel.getA());
        assertEquals("0", viewModel.getB());
        assertEquals("0", viewModel.getC());
        assertEquals("", viewModel.getRoots());
    }

    @Test
    public void canSolve() {
        viewModel.setA("4");
        viewModel.setB("1");
        viewModel.setC("0");

        viewModel.solve();

        assertEquals("[-0.25, 0.0]", viewModel.getRoots());
    }

    @Test
    public void canSolveWithOneRoot() {
        viewModel.setA("1");
        viewModel.setB("2");
        viewModel.setC("1");

        viewModel.solve();

        assertEquals("[-1.0, -1.0]", viewModel.getRoots());
    }

    @Test
    public void canSolveWithoutRoots() {
        viewModel.setA("1");
        viewModel.setB("1");
        viewModel.setC("1");

        viewModel.solve();

        assertEquals(viewModel.NO_ROOTS_MESSAGE, viewModel.getRoots());
    }

    @Test
    public void canSolveWithoutSetRoots() {
        viewModel.solve();

        assertEquals(ViewModel.NO_QUADRATIC_COEFFICIENT_ERR, viewModel.getRoots());
    }

    @Test
    public void canSolveWithNonNumericCoefficients() {
        viewModel.setA("a");
        viewModel.setB("b");
        viewModel.setC("c");

        viewModel.solve();

        assertEquals(ViewModel.NON_NUMERIC_COEFFICIENTS_ERR, viewModel.getRoots());
    }
}
