package ru.unn.agile.polynomial.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PolynomialTest {
    @Test
    public void checkInit() {
        double[] result = {1.0, -2.0, 3.0, -4.0};

        Polynomial p = new Polynomial(result);

        assertArrayEquals(result, p.getCoeffs(), Polynomial.DELTA);
        assertEquals(3, p.getDegree());
    }

    @Test
    public void checkFirstDegreeWithCoeffPlus() {
        double[] toInit = {1.0, 1.0};

        Polynomial p = new Polynomial(toInit);

        assertEquals("1.0x + 1.0", p.toString());
    }

    @Test
    public void checkFirstDegreeWithCoeffMinus() {
        double[] toInit = {1.0, -1.0};

        Polynomial p = new Polynomial(toInit);

        assertEquals("1.0x - 1.0", p.toString());
    }
    @Test
    public void checkInitWithFirstZero() {
        double[] toInit = {0.0, 0.0, 3.0};
        double[] result = {3.0};

        Polynomial p = new Polynomial(toInit);

        assertArrayEquals(result, p.getCoeffs(), Polynomial.DELTA);
        assertEquals(0, p.getDegree());
    }

    @Test
    public void checkInitWithZero() {
        double[] toInit = {1.0, 0.0, 3.0};
        double[] result = {1.0, 0.0, 3.0};

        Polynomial p = new Polynomial(toInit);

        assertArrayEquals(result, p.getCoeffs(), Polynomial.DELTA);
        assertEquals(2, p.getDegree());
    }

    @Test
    public void checkInitWithOnlyZero() {
        double[] toInit = {0.0};

        Polynomial p = new Polynomial(toInit);

        assertArrayEquals(toInit, p.getCoeffs(), Polynomial.DELTA);
        assertEquals(0, p.getDegree());
    }

    @Test
    public void checkInitWithOnlyZeros() {
        double[] toInit = {0.0, 0.0, 0.0};
        double[] result = {0.0};

        Polynomial p = new Polynomial(toInit);

        assertArrayEquals(result, p.getCoeffs(), Polynomial.DELTA);
        assertEquals(0, p.getDegree());
    }

    @Test
    public void canConvertToString() {
        double[] toInit = {1.0, -2.0, 3.0};

        Polynomial p = new Polynomial(toInit);

        assertEquals("1.0x^2 - 2.0x + 3.0", p.toString());
    }

    @Test
    public void canConvertToStringNull() {
        Polynomial p = new Polynomial();

        assertEquals("0", p.toString());
    }
    @Test
    public void canConvertToStringZero() {
        double[] toInit = {0.0};

        Polynomial p = new Polynomial(toInit);

        assertEquals("0.0", p.toString());
    }

    @Test
    public void canConvertToStringWithZero() {
        double[] toInit = {0.0, -2.0, 3.0, 0.0};

        Polynomial p = new Polynomial(toInit);

        assertEquals("-2.0x^2 + 3.0x", p.toString());
    }

    @Test
    public void canAddNumber() {
        double[] toInit = {1.0, -2.0, 3.0, -4.0};
        Polynomial p = new Polynomial(toInit);

        p.add(5);

        assertEquals("1.0x^3 - 2.0x^2 + 3.0x + 1.0", p.toString());
    }

    @Test
    public void canAddNumberForZeroCoefficient() {
        double[] toInit = {1.0, -2.0, 3.0, -4.0};
        Polynomial p = new Polynomial(toInit);

        p.add(4);

        assertEquals("1.0x^3 - 2.0x^2 + 3.0x", p.toString());
    }

    @Test
    public void canAddPolynomial() {
        double[] toInit = {1.0, -2.0, 3.0, -4.0};
        Polynomial p1 = new Polynomial(toInit);
        double[] toAdd = {-1.0, -2.0, 3.0, -4.0};
        Polynomial p2 = new Polynomial(toAdd);

        Polynomial res = p1.add(p2);

        assertEquals("-4.0x^2 + 6.0x - 8.0", res.toString());
    }

    @Test
    public void canAddPolynomialWithOtherLength() {
        double[] toInit = {1.0, -2.0, 3.0, -4.0};
        Polynomial p1 = new Polynomial(toInit);
        double[] toAdd = {2.0, 3.0, -4.0};
        Polynomial p2 = new Polynomial(toAdd);

        Polynomial res = p1.add(p2);

        assertEquals("1.0x^3 + 6.0x - 8.0", res.toString());
    }

    @Test
    public void canAddPolynomialWithZeroResult() {
        double[] toInit = {1.0, -2.0, 3.0, -4.0};
        Polynomial p1 = new Polynomial(toInit);
        double[] toAdd = {-1.0, 2.0, -3.0, 4.0};
        Polynomial p2 = new Polynomial(toAdd);

        Polynomial res = p1.add(p2);

        assertEquals("0.0", res.toString());
    }

    @Test
    public void canMultiplyByNumber() {
        double[] toInit = {1.0, -2.0, 3.0, -4.0};
        Polynomial p = new Polynomial(toInit);

        Polynomial res = p.multiply(5);

        assertEquals("5.0x^3 - 10.0x^2 + 15.0x - 20.0", res.toString());
    }

    @Test
    public void canMultiplyByZero() {
        double[] toInit = {1.0, -2.0, 3.0, -4.0};
        Polynomial p = new Polynomial(toInit);

        Polynomial res = p.multiply(0);

        assertEquals("0.0", res.toString());
    }

    @Test
    public void canMultiplyByPolynomial() {
        double[] toInit = {1.0, -2.0, 3.0, -4.0};
        Polynomial p1 = new Polynomial(toInit);
        double[] toMultiply = {2.0, 1.0};
        Polynomial p2 = new Polynomial(toMultiply);

        Polynomial res = p1.multiply(p2);

        assertEquals("2.0x^4 - 3.0x^3 + 4.0x^2 - 5.0x - 4.0", res.toString());
    }

    @Test
    public void canMultiplyByPolynomialWithZeroResultCoefficients() {
        double[] toInit = {1.0, 2.0, 4.0};
        Polynomial p1 = new Polynomial(toInit);
        double[] toMultiply = {1.0, -2.0};
        Polynomial p2 = new Polynomial(toMultiply);

        Polynomial res = p1.multiply(p2);

        assertEquals("1.0x^3 - 8.0", res.toString());
    }

    @Test
    public void canSubtractPolynomial() {
        double[] toInit = {1.0, 2.0, 4.0};
        Polynomial p1 = new Polynomial(toInit);
        double[] toSubtract = {1.0, -2.0, 3.0};
        Polynomial p2 = new Polynomial(toSubtract);

        Polynomial res = p1.subtract(p2);

        assertEquals("4.0x + 1.0", res.toString());
    }

    @Test
    public void canSubtractPolynomialWithZeroResult() {
        double[] toInit = {1.0, 2.0, 4.0};
        Polynomial p1 = new Polynomial(toInit);
        double[] toSubtract = {1.0, 2.0, 4.0};
        Polynomial p2 = new Polynomial(toSubtract);

        Polynomial res = p1.subtract(p2);

        assertEquals("0.0", res.toString());
    }

    @Test
    public void canSubtractPolynomialWithZeroResultCoefficients() {
        double[] toInit = {1.0, 2.0, 4.0};
        Polynomial p1 = new Polynomial(toInit);
        double[] toSubtract = {2.0, 4.0};
        Polynomial p2 = new Polynomial(toSubtract);

        Polynomial res = p1.subtract(p2);

        assertEquals("1.0x^2", res.toString());
    }

    @Test
    public void canSubtractPolynomialWithZeroFreeCoefficient() {
        double[] toInit = {1.0, 2.0, 4.0};
        Polynomial p1 = new Polynomial(toInit);
        double[] toSubtract = {1.0, 2.0, 3.0, 4.0};
        Polynomial p2 = new Polynomial(toSubtract);

        Polynomial res = p1.subtract(p2);

        assertEquals("-1.0x^3 - 1.0x^2 - 1.0x", res.toString());
    }

    @Test
    public void canSubtractNumber() {
        double[] toInit = {1.0, 2.0, 4.0};
        Polynomial p1 = new Polynomial(toInit);

        Polynomial res = p1.subtract(5);

        assertEquals("1.0x^2 + 2.0x - 1.0", res.toString());
    }

    @Test
    public void canSubtractNumberWithZeroPolynomial() {
        double[] toInit = {0.0};
        Polynomial p1 = new Polynomial(toInit);

        Polynomial res = p1.subtract(5);

        assertEquals("-5.0", res.toString());
    }

    @Test
    public void canComparePolynomialsWithDifferentLength() {
        double[] toInit = {1.0, 2.0, 3.0};
        Polynomial p1 = new Polynomial(toInit);
        double[] toCompare = {1.0, 2.0};
        Polynomial p2 = new Polynomial(toCompare);

        assertFalse(p1.isEqual(p2));
    }

    @Test
    public void canComparePolynomials() {
        double[] toInit = {1.0, 2.0, 3.0};
        Polynomial p1 = new Polynomial(toInit);
        double[] toCompare = {1.0, 2.0, 4.0};
        Polynomial p2 = new Polynomial(toCompare);

        assertFalse(p1.isEqual(p2));
    }

    @Test
    public void canComparePolynomialEquals() {
        double[] toInit = {1.0, 2.0, 3.0};
        Polynomial p1 = new Polynomial(toInit);
        double[] toCompare = {1.0, 2.0, 3.0};
        Polynomial p2 = new Polynomial(toCompare);

        assertTrue(p1.isEqual(p2));
    }

    @Test
    public void canCompareWithNullPolynomial() {
        double[] toInit = {1.0, 2.0, 3.0};
        Polynomial p1 = new Polynomial(toInit);
        Polynomial p2 = new Polynomial();

        assertFalse(p1.isEqual(p2));
    }
}
