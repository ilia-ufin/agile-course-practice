package ru.unn.agile.fraction.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class FractionalNumbersCalcTest {
    @Test
    public void canCreateFractionalNumberWithInitialValues() {
        Fraction frac = new Fraction(1, 2);

        assertNotNull(frac);
    }

    @Test
    public void canGetInitialNumeratorValue() {
        Fraction frac = new Fraction(1, 2);

        assertEquals(1, frac.getNumerator());
    }

    @Test
    public void canGetInitialDenominatorValue() {
        Fraction frac = new Fraction(1, 2);

        assertEquals(2, frac.getDenominator());
    }

    @Test
    public void canSetInitialNumeratorValue() {
        Fraction frac = new Fraction(1, 2);

        frac.setNumerator(5);

        assertEquals(5, frac.getNumerator());
    }

    @Test
    public void isFractionNumberEqualToItSelf() {
        Fraction frac1 = new Fraction(1, 2);

        assertEquals(frac1, frac1);
    }

    @Test
    public void isNullEqualToFractionNumber() {
        Fraction frac1 = new Fraction(1, 1);

        assertNotEquals(null, frac1);
    }

    @Test
    public void canConvertToStringFractionNumberWithPositiveValues() {
        Fraction fractionalNumber = new Fraction(1, 2);

        assertEquals("1/2", fractionalNumber.toString());
    }

    @Test
    public void canConvertToStringFractionNumberWithNegativeValue() {
        Fraction fractionalNumber = new Fraction(-1, 2);

        assertEquals("-1/2", fractionalNumber.toString());
    }

    @Test
    public void areEqualFractionNumbersEqual() {
        Fraction frac1 = new Fraction(3, 5);
        Fraction frac2 = new Fraction(3, 5);

        assertTrue(frac1.equals(frac2));
    }

    @Test
    public void areDifferentFractionNumbersNotEqual() {
        Fraction frac1 = new Fraction(3, 5);
        Fraction frac2 = new Fraction(1, 2);

        assertFalse(frac1.equals(frac2));
    }

    @Test
    public void areFractionNumbersWithDifferentNumeratorNotEqual() {
        Fraction frac1 = new Fraction(1, 8);
        Fraction frac2 = new Fraction(3, 8);

        assertFalse(frac1.equals(frac2));
    }

    @Test
    public void areFractionNumbersWithDifferentDenominatorNotEqual() {
        Fraction frac1 = new Fraction(3, 5);
        Fraction frac2 = new Fraction(3, 4);

        assertFalse(frac1.equals(frac2));
    }

    @Test
    public void canMultiplyFractionNumbersWithPositiveValues() {
        Fraction frac1 = new Fraction(1, 5);
        Fraction frac2 = new Fraction(2, 3);

        Fraction multiplyResult = frac1.multiply(frac2);

        assertTrue(multiplyResult.equals(new Fraction(2, 15)));
    }

    @Test
    public void canMultiplyFractionNumbersWithNegativeValue() {
        Fraction frac1 = new Fraction(-1, 5);
        Fraction frac2 = new Fraction(2, 3);

        Fraction multiplyResult = frac1.multiply(frac2);

        assertTrue(multiplyResult.equals(new Fraction(-2, 15)));
    }

    @Test
    public void canDivideFractionNumbersWithPositiveValues() {
        Fraction frac1 = new Fraction(1, 5);
        Fraction frac2 = new Fraction(2, 3);

        Fraction divideResult = frac1.divide(frac2);

        assertTrue(divideResult.equals(new Fraction(3, 10)));
    }

    @Test
    public void canDivideFractionNumbersWithNegativeValues() {
        Fraction frac1 = new Fraction(-1, 5);
        Fraction frac2 = new Fraction(-2, 3);

        Fraction divideResult = frac1.divide(frac2);

        assertTrue(divideResult.equals(new Fraction(3, 10)));
    }

    @Test(expected = ArithmeticException.class)
    public void canDivideFractionNumbersWithZeroNumerator() {
        Fraction frac1 = new Fraction(-1, 5);
        Fraction frac2 = new Fraction(0, 3);

        Fraction divideResult = frac1.divide(frac2);
    }

    @Test
    public void canDivideEqualsFractionNumbers() {
        Fraction frac1 = new Fraction(5, 5);
        Fraction frac2 = new Fraction(5, 5);

        Fraction substractResult = frac1.substract(frac2);

        assertEquals("0/1", substractResult.toString());
    }

    @Test
    public void canAddFractionNumbersWithPositiveValues() {
        Fraction frac1 = new Fraction(2, 8);
        Fraction frac2 = new Fraction(7, 3);

        Fraction addResult = frac1.add(frac2);
        String res = addResult.toString();

        assertEquals("31/12", res);
    }

    @Test
    public void canAddFractionNumbersWithNegativeValues() {
        Fraction frac1 = new Fraction(-2, 8);
        Fraction frac2 = new Fraction(-7, 3);

        Fraction addResult = frac1.add(frac2);
        String res = addResult.toString();

        assertEquals("-31/12", res);
    }

    @Test
    public void canSubstractFractionNumbersWithPositiveValues() {
        Fraction frac1 = new Fraction(2, 8);
        Fraction frac2 = new Fraction(7, 3);

        Fraction addResult = frac1.substract(frac2);
        String res = addResult.toString();

        assertEquals("-25/12", res);
    }

    @Test
    public void canSubstractFractionNumbersWithNegativeValues() {
        Fraction frac1 = new Fraction(-1, 9);
        Fraction frac2 = new Fraction(-3, 9);

        Fraction addResult = frac1.substract(frac2);
        String res = addResult.toString();

        assertEquals("2/9", res);
    }

    @Test
    public void canSubstractFractionNumbersWithZeroValue() {
        Fraction frac1 = new Fraction(4, 9);
        Fraction frac2 = new Fraction(0, 5);

        Fraction addResult = frac1.substract(frac2);
        String res = addResult.toString();

        assertEquals("4/9", res);
    }

    @Test(expected = ArithmeticException.class)
    public void canDenominationBeZero() {
        Fraction frac = new Fraction(2, 0);
    }

    @Test(expected = ArithmeticException.class)
    public void canDenominationBeLessThanZero() {
        Fraction frac = new Fraction(2, -1);
    }
}
