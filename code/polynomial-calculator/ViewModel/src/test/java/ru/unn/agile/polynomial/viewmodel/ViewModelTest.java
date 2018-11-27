package ru.unn.agile.polynomial.viewmodel;

import org.junit.Test;
import ru.unn.agile.polynomial.model.Polynomial;

import static org.junit.Assert.assertEquals;

public class ViewModelTest {
    @Test
    public void createViewModelWithDefaultFields() {
        ViewModel viewModel = new ViewModel();

        assertEquals("", viewModel.getFirstPolynomialStr());
        assertEquals("", viewModel.getSecondPolynomialStr());
        assertEquals("", viewModel.getResultStr());
    }

    @Test
    public void createViewModelWithDefaultFieldsProperty() {
        ViewModel viewModel = new ViewModel();

        assertEquals("", viewModel.firstPolynomialStrProperty().get());
        assertEquals("", viewModel.secondPolynomialStrProperty().get());
        assertEquals("", viewModel.resultStrProperty().get());
    }

    @Test
    public void canParsePolynomial() {
        ViewModel viewModel = new ViewModel();

        Polynomial polynomial = viewModel.parsePolynomial("2.0x^3+4.0x^2+5.0x-42.0");

        assertEquals("2.0x^3 + 4.0x^2 + 5.0x - 42.0", polynomial.toString());
    }

    @Test
    public void canParsePolynomialWithZero() {
        ViewModel viewModel = new ViewModel();

        Polynomial polynomial = viewModel.parsePolynomial("2.0x^3+5.0x-42.0");

        assertEquals("2.0x^3 + 5.0x - 42.0", polynomial.toString());
    }

    @Test
    public void canParsePolynomialForZeroCoefficient() {
        ViewModel viewModel = new ViewModel();

        Polynomial polynomial = viewModel.parsePolynomial("1.0x^3-2.0x^2+3.0x");

        assertEquals("1.0x^3 - 2.0x^2 + 3.0x", polynomial.toString());
    }

    @Test
    public void canParseNumberWithZeroPolynomial() {
        ViewModel viewModel = new ViewModel();

        Polynomial polynomial = viewModel.parsePolynomial("3.0");

        assertEquals("3.0", polynomial.toString());
    }

    @Test
    public void canAddZeroPolynomial() {
        ViewModel viewModel = new ViewModel();

        viewModel.setFirstPolynomialStr("");
        viewModel.setSecondPolynomialStr("");

        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void canAddPolynomial() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("1.0x^3-2.0x^2+3.0x-4.0");
        viewModel.setSecondPolynomialStr("-1.0x^3-2.0x^2+3.0x-4.0");

        viewModel.add();

        assertEquals("-4.0x^2 + 6.0x - 8.0", viewModel.getResultStr());
    }

    @Test
    public void canMultiplyByNumber() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("1.0x^3-2.0x^2+3.0x-4.0");
        viewModel.setSecondPolynomialStr("5.0");

        viewModel.multiply();

        assertEquals("5.0x^3 - 10.0x^2 + 15.0x - 20.0", viewModel.getResultStr());
    }

    @Test
    public void canSubtractPolynomial() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("1.0x^2+2.0x+4.0");
        viewModel.setSecondPolynomialStr("1.0x^2-2.0x+3.0");

        viewModel.subtract();

        assertEquals("4.0x + 1.0", viewModel.getResultStr());
    }

    @Test
    public void canParseFirstDegreeWithoutFreeCoeff() {
        ViewModel viewModel = new ViewModel();

        Polynomial polynomial = viewModel.parsePolynomial("1.0x");

        assertEquals("1.0x", polynomial.toString());
    }

    @Test
    public void canParseFirstDegreeWithFreeCoeffPlus() {
        ViewModel viewModel = new ViewModel();

        Polynomial polynomial = viewModel.parsePolynomial("1.0x+1.0");

        assertEquals("1.0x + 1.0", polynomial.toString());
    }

    @Test
    public void canParseFirstDegreeWithFreeCoeffMinus() {
        ViewModel viewModel = new ViewModel();

        Polynomial polynomial = viewModel.parsePolynomial("1.0x-1.0");

        assertEquals("1.0x - 1.0", polynomial.toString());
    }

    @Test
    public void canParseSecondDegreeWithoutFreeCoeff() {
        ViewModel viewModel = new ViewModel();

        Polynomial polynomial = viewModel.parsePolynomial("1.0x^2");

        assertEquals("1.0x^2", polynomial.toString());
    }

    @Test
    public void canSubtractWrongFormatPolynomial() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("1.0x^2+2.0x+4.0");
        viewModel.setSecondPolynomialStr("fghfg");

        viewModel.subtract();

        assertEquals(PolynomialParser.FORMAT_ERROR + "2", viewModel.getResultStr());
    }

    @Test
    public void canAddWrongFormatPolynomial() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("dfgdfg");
        viewModel.setSecondPolynomialStr("1.0x^2+2.0x+4.0");
        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void canMultiplyWrongFormatPolynomial() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("fghfg");
        viewModel.setSecondPolynomialStr("fghfg");

        viewModel.multiply();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void canMultiplyPolynomialWithTwoFreeCoeff() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("x + 2.0 + 4.0");
        viewModel.setSecondPolynomialStr("2.0x -2.0+4.0");

        viewModel.multiply();

        assertEquals("2.0x^2 + 14.0x + 12.0", viewModel.getResultStr());
    }

    @Test
    public void canMultiplyWrongFormatPolynomialPoint() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("2.x -2.0+4.0");
        viewModel.setSecondPolynomialStr("2.0x^2 + 14.0x + 12.0");

        viewModel.multiply();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void canSubtractDifficultPolynomial() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("5.9x^6-78x^5-9");
        viewModel.setSecondPolynomialStr("x+x+5");

        viewModel.subtract();

        assertEquals("5.9x^6 - 78.0x^5 - 2.0x - 14.0", viewModel.getResultStr());
    }

    @Test
    public void canMultiplyWrongFormatPolynomialXDetectedAfterDegree() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("2.0x^x -2.0+4.0");
        viewModel.setSecondPolynomialStr("2.0x^2 + 14.0x + 12.0");

        viewModel.multiply();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void canAddPolynomialSymbolMinusBeforeX() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("-x -2.0");
        viewModel.setSecondPolynomialStr("2.0x^2 + 2.0x + 12.0");

        viewModel.add();

        assertEquals("2.0x^2 + 1.0x + 10.0", viewModel.getResultStr());
    }

    @Test
    public void caAddWrongFormatPolynomialSymbolMinusAfterPlus() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("2.0x^2 + 14.0x + 12.0");
        viewModel.setSecondPolynomialStr("2.0x^2 +- 14.0x + 12.0");

        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "2", viewModel.getResultStr());
    }

    @Test
    public void caAddWrongFormatPolynomialDigitAfterX() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("2.0x2 + 14.0x + 12.0");
        viewModel.setSecondPolynomialStr("2.0x^2 + 14.0x + 12.0");

        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void caAddWrongFormatPolynomialPointAfterX() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("2.0x. + 14.0x + 12.0");
        viewModel.setSecondPolynomialStr("2.0x^2 + 14.0x + 12.0");

        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void caAddWrongFormatPolynomialDegreeAfterPoint() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("2.^2 + 14.0x + 12.0");
        viewModel.setSecondPolynomialStr("2.0x^2 + 14.0x + 12.0");

        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void caAddWrongFormatPolynomialMinusAfterDegree() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("2x^-2 + 14.0x + 12.0");
        viewModel.setSecondPolynomialStr("2.0x^2 + 14.0x + 12.0");

        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }

    @Test
    public void caAddWrongFormatPolynomialXAfterX() {
        ViewModel viewModel = new ViewModel();
        viewModel.setFirstPolynomialStr("xx+1");
        viewModel.setSecondPolynomialStr("2.0x^2 + 14.0x + 12.0");

        viewModel.add();

        assertEquals(PolynomialParser.FORMAT_ERROR + "1", viewModel.getResultStr());
    }
}
