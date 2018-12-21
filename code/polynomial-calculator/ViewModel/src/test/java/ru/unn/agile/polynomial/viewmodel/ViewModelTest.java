package ru.unn.agile.polynomial.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.polynomial.model.Polynomial;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ViewModelTest {
    private ViewModel viewModel;

    protected void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void initViewModel() {
        viewModel = new ViewModel();
        viewModel = new ViewModel(new FakeLogger());
    }

    @After
    public void nullViewModel() {
        viewModel = null;
    }

    @Test
    public void createViewModelWithDefaultFields() {
        assertEquals("", viewModel.getFirstPolynomialStr());
        assertEquals("", viewModel.getSecondPolynomialStr());
        assertEquals("", viewModel.getResultStr());
    }

    @Test
    public void createViewModelWithDefaultFieldsProperty() {
        assertEquals("", viewModel.firstPolynomialStrProperty().get());
        assertEquals("", viewModel.secondPolynomialStrProperty().get());
        assertEquals("", viewModel.resultStrProperty().get());
    }

    @Test
    public void canParsePolynomial() {
        Polynomial polynomial = viewModel.parsePolynomial("2.0x^3+4.0x^2+5.0x-42.0");

        assertEquals("2.0x^3 + 4.0x^2 + 5.0x - 42.0", polynomial.toString());
    }

    @Test
    public void canParsePolynomialWithZero() {
        Polynomial polynomial = viewModel.parsePolynomial("2.0x^3+5.0x-42.0");

        assertEquals("2.0x^3 + 5.0x - 42.0", polynomial.toString());
    }

    @Test
    public void canParsePolynomialForZeroCoefficient() {
        Polynomial polynomial = viewModel.parsePolynomial("1.0x^3-2.0x^2+3.0x");

        assertEquals("1.0x^3 - 2.0x^2 + 3.0x", polynomial.toString());
        String actualMessage = viewModel.getListLog().get(0);
        String expectedMessage = String.format(ViewModel.PARSE_PASSED,
                polynomial.toString());
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void canParseNumberWithZeroPolynomial() {
        Polynomial polynomial = viewModel.parsePolynomial("3.0");

        assertEquals("3.0", polynomial.toString());
    }

    @Test
    public void canAddZeroPolynomial() {
        viewModel.setFirstPolynomialStr("");
        viewModel.setSecondPolynomialStr("");

        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void canAddPolynomial() {
        viewModel.setFirstPolynomialStr("1.0x^3 - 2.0x^2 + 3.0x - 4.0");
        viewModel.setSecondPolynomialStr("-1.0x^3 - 2.0x^2 + 3.0x - 4.0");

        viewModel.add();

        String msg = viewModel.getListLog().get(2);
        String expectMsg = String.format(ViewModel.OPERATION_PASSED,
                viewModel.getFirstPolynomialStr(), viewModel.getSecondPolynomialStr(),
                viewModel.getResultStr());
        assertTrue(msg.contains(expectMsg));
        assertEquals("-4.0x^2 + 6.0x - 8.0", viewModel.getResultStr());
    }

    @Test
    public void canMultiplyByNumber() {
        viewModel.setFirstPolynomialStr("1.0x^3 - 2.0x^2 + 3.0x - 4.0");
        viewModel.setSecondPolynomialStr("5.0");

        viewModel.multiply();

        String actualMsg = viewModel.getListLog().get(2);
        String expectedMsg = String.format(ViewModel.OPERATION_PASSED,
                viewModel.getFirstPolynomialStr(), viewModel.getSecondPolynomialStr(),
                viewModel.getResultStr());
        assertTrue(actualMsg.contains(expectedMsg));
        assertEquals("5.0x^3 - 10.0x^2 + 15.0x - 20.0", viewModel.getResultStr());
    }

    @Test
    public void canSubtractPolynomial() {
        viewModel.setFirstPolynomialStr("1.0x^2 + 2.0x + 4.0");
        viewModel.setSecondPolynomialStr("1.0x^2 - 2.0x + 3.0");

        viewModel.subtract();

        String actualMessage = viewModel.getListLog().get(2);
        String expectedMessage = String.format(ViewModel.OPERATION_PASSED,
                viewModel.getFirstPolynomialStr(),
                viewModel.getSecondPolynomialStr(), viewModel.getResultStr());
        assertTrue(actualMessage.contains(expectedMessage));
        assertEquals("4.0x + 1.0", viewModel.getResultStr());
    }

    @Test
    public void canParseFirstDegreeWithoutFreeCoeff() {
        Polynomial polynomial = viewModel.parsePolynomial("1.0x");

        assertEquals("1.0x", polynomial.toString());
    }

    @Test
    public void canParseFirstDegreeWithFreeCoeffPlus() {
        Polynomial polynomial = viewModel.parsePolynomial("1.0x+1.0");

        assertEquals("1.0x + 1.0", polynomial.toString());
    }

    @Test
    public void canParseFirstDegreeWithFreeCoeffMinus() {
        Polynomial polynomial = viewModel.parsePolynomial("1.0x-1.0");

        assertEquals("1.0x - 1.0", polynomial.toString());
    }

    @Test
    public void canParseSecondDegreeWithoutFreeCoeff() {
        Polynomial polynomial = viewModel.parsePolynomial("1.0x^2");

        assertEquals("1.0x^2", polynomial.toString());
    }

    @Test
    public void canSubtractWrongFormatPolynomial() {
        viewModel.setFirstPolynomialStr("1.0x^2+2.0x+4.0");
        viewModel.setSecondPolynomialStr("fghfg");

        viewModel.subtract();

        assertEquals(PolynomialParser.FORMAT_ERROR + "2", viewModel.getResultStr());
    }

    @Test
    public void canAddWrongFormatPolynomial() {
        viewModel.setFirstPolynomialStr("dfgdfg");
        viewModel.setSecondPolynomialStr("1.0x^2+2.0x+4.0");

        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void canMultiplyWrongFormatPolynomial() {
        viewModel.setFirstPolynomialStr("fghfg");
        viewModel.setSecondPolynomialStr("fghfg");

        viewModel.multiply();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void canMultiplyPolynomialWithTwoFreeCoeff() {
        viewModel.setFirstPolynomialStr("x + 2.0 + 4.0");
        viewModel.setSecondPolynomialStr("2.0x -2.0+4.0");

        viewModel.multiply();

        assertEquals("2.0x^2 + 14.0x + 12.0", viewModel.getResultStr());
    }

    @Test
    public void canMultiplyWrongFormatPolynomialPoint() {
        viewModel.setFirstPolynomialStr("2.x -2.0+4.0");
        viewModel.setSecondPolynomialStr("2.0x^2 + 14.0x + 12.0");

        viewModel.multiply();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void canSubtractDifficultPolynomial() {
        viewModel.setFirstPolynomialStr("5.9x^6-78x^5-9");
        viewModel.setSecondPolynomialStr("x+x+5");

        viewModel.subtract();

        assertEquals("5.9x^6 - 78.0x^5 - 2.0x - 14.0", viewModel.getResultStr());
    }

    @Test
    public void canMultiplyWrongFormatPolynomialXDetectedAfterDegree() {
        viewModel.setFirstPolynomialStr("2.0x^x -2.0+4.0");
        viewModel.setSecondPolynomialStr("2.0x^2 + 14.0x + 12.0");

        viewModel.multiply();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void canAddPolynomialSymbolMinusBeforeX() {
        viewModel.setFirstPolynomialStr("-x -2.0");
        viewModel.setSecondPolynomialStr("2.0x^2 + 2.0x + 12.0");

        viewModel.add();

        String expectedFullLog = "Parse passed: -1.0x - 2.0\n"
                + "Parse passed: 2.0x^2 + 2.0x + 12.0\n"
                + "Operation: -1.0x - 2.0 2.0x^2 + 2.0x + 12.0 = 2.0x^2 + 1.0x + 10.0\n";
        String actualFullLog = viewModel.getLog();
        assertEquals("2.0x^2 + 1.0x + 10.0", viewModel.getResultStr());
        assertEquals(expectedFullLog, actualFullLog);
    }

    @Test
    public void caAddWrongFormatPolynomialSymbolMinusAfterPlus() {
        viewModel.setFirstPolynomialStr("2.0x^2 + 14.0x + 12.0");
        viewModel.setSecondPolynomialStr("2.0x^2 +- 14.0x + 12.0");

        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "2", viewModel.getResultStr());
    }

    @Test
    public void caAddWrongFormatPolynomialDigitAfterX() {
        viewModel.setFirstPolynomialStr("2.0x2 + 14.0x + 12.0");
        viewModel.setSecondPolynomialStr("2.0x^2 + 14.0x + 12.0");

        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void caAddWrongFormatPolynomialPointAfterX() {
        viewModel.setFirstPolynomialStr("2.0x. + 14.0x + 12.0");
        viewModel.setSecondPolynomialStr("2.0x^2 + 14.0x + 12.0");

        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void caAddWrongFormatPolynomialDegreeAfterPoint() {
        viewModel.setFirstPolynomialStr("2.^2 + 14.0x + 12.0");
        viewModel.setSecondPolynomialStr("2.0x^2 + 14.0x + 12.0");

        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void caAddWrongFormatPolynomialMinusAfterDegree() {
        viewModel.setFirstPolynomialStr("2x^-2 + 14.0x + 12.0");
        viewModel.setSecondPolynomialStr("2.0x^2 + 14.0x + 12.0");

        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void caAddWrongFormatPolynomialXAfterX() {
        viewModel.setFirstPolynomialStr("xx+1");
        viewModel.setSecondPolynomialStr("2.0x^2 + 14.0x + 12.0");

        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }
    @Test
    public void checkInitLog() {
        List<String> log = viewModel.getListLog();

        assertTrue(log.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void initViewModelWithNullLogger() {
        ILogger logger = null;

        viewModel = new ViewModel(logger);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkExceptionSetLoggerNull() {
        ILogger logger = null;

        viewModel.setLogger(logger);
    }

    @Test
    public void checkSetLoggerNotNull() {
        ILogger logger = new FakeLogger();

        boolean success = true;
        try {
            viewModel.setLogger(logger);
        } catch (Exception e) {
            success = false;
        }

        assertTrue(success);
    }

    @Test
    public void canGetLogProperty() {
        viewModel.logProperty().set("");
        String log = viewModel.logProperty().get();

        assertTrue(log.isEmpty());
    }



}
