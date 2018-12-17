package ru.unn.agile.quadraticequation.viewmodel;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;

    public static final String NO_QUADRATIC_COEFFICIENT_ERR = "Enter quadratic coefficient";
    public static final String NON_NUMERIC_COEFFICIENTS_ERR = "Coefficients must be numeric";
    public static final String EMPTY_COEFFICIENTS_ERR = "Coefficients must be not empty";
    public static final String NO_ROOTS_MESSAGE = "No roots";
    public static final String SOLVE_WAS_PRESSED = "Solved. ";

    public void setViewModel(final ViewModel viewM) {
        this.viewModel = viewM;
    }

    @Before
    public void setUp() {
        FakeLogger fakeLogger = new FakeLogger();
        viewModel = new ViewModel(fakeLogger);
    }

    @Test
    public void canInitializeViewModel() {
        assertEquals("1", viewModel.getA());
        assertEquals("0", viewModel.getB());
        assertEquals("0", viewModel.getC());
        assertEquals("", viewModel.getRoots());
        assertEquals("", viewModel.getLogs());
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
        viewModel.setA("0");
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

    @Test
    public void isLogEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();

        assertEquals(0, log.size());
    }

    @Test
    public void canLogSomething() {
        viewModel.solve();

        List<String> log = viewModel.getLog();

        assertNotEquals(0, log.size());
    }

    @Test
    public void canLogEmptyCoefficients() {
        viewModel.setC("");
        viewModel.solve();

        List<String> message = viewModel.getLog();

        assertTrue(message.get(0).contains(EMPTY_COEFFICIENTS_ERR));
    }

    @Test
    public void canLogNoNumericCoefficients() {
        viewModel.setA("a");
        viewModel.setB("4");
        viewModel.setC("11");
        viewModel.solve();

        List<String> message = viewModel.getLog();

        assertTrue(message.get(0).contains(NON_NUMERIC_COEFFICIENTS_ERR));
    }

    @Test
    public void canLogNoQuadraticCoefficients() {
        viewModel.setA("0");
        viewModel.setB("3");
        viewModel.setC("11");
        viewModel.solve();

        List<String> message = viewModel.getLog();

        assertTrue(message.get(0).contains(NO_QUADRATIC_COEFFICIENT_ERR));
    }

    @Test
    public void canLogNoRoots() {
        viewModel.setA("7");
        viewModel.setB("5");
        viewModel.setC("11");
        viewModel.solve();

        List<String> message = viewModel.getLog();

        assertTrue(message.get(0).contains(NO_ROOTS_MESSAGE));
    }

    @Test
    public void canLogSolve() {
        viewModel.setA("1");
        viewModel.setB("0");
        viewModel.setC("-11");
        viewModel.solve();

        List<String> message = viewModel.getLog();

        assertTrue(message.get(0).contains(SOLVE_WAS_PRESSED));
    }

    @Test
    public void canGetLog() {
        viewModel.setA("11");
        viewModel.setB("15");
        viewModel.setC("7");
        viewModel.solve();

        assertNotEquals(null, viewModel.getLog());
    }

    @Test
    public void canSetLogger() {
        FakeLogger fakeLog = new FakeLogger();

        viewModel.setLogger(fakeLog);

        assertEquals("", viewModel.getLogs());
    }


    @Test
    public void canGetLogs() {
        viewModel.solve();

        assertNotEquals(null, viewModel.getLogs());
    }

    @Test
    public void canGetLogsProperty() {
        viewModel.solve();

        assertNotEquals(null, viewModel.logsProperty());
    }

    @Test
    public void canInitViewModel() {
        ViewModel vModel = new ViewModel();

        assertEquals(vModel.getA(), "1");
        assertEquals(vModel.getB(), "0");
        assertEquals(vModel.getC(), "0");
        assertEquals(vModel.getRoots(), "");
        assertEquals(vModel.getLogs(), "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotInitViewModelWithNullLogger() {
        ViewModel vModel = new ViewModel(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotSetNullLogger() {
        viewModel.setLogger(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotCreateViewModelWithNullLogger() {
        ViewModel newViewModel = new ViewModel(null);
    }
}
