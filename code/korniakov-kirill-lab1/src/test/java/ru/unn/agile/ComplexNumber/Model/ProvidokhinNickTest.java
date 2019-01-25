package ru.unn.agile.complexnumber.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ProvidokhinNickTest {

    @Test
    public void canAddAndMultiplyComplexNumbersEqualsZero() {
        ComplexNumber x = new ComplexNumber(0, 0);
        ComplexNumber y = new ComplexNumber(0, 0);

        ComplexNumber xymult = x.multiply(y);
        ComplexNumber xyadd = x.add(y);

        assertEquals(xymult, xyadd);
    }

    @Test
    public void canSquareComplexNumbers() {
        ComplexNumber x = new ComplexNumber(1, 1);

        ComplexNumber square = x.multiply(x);

        assertEquals(square, new ComplexNumber(0, 2));
    }
    @Test
    public void addComplexNumbers() {
        ComplexNumber x = new ComplexNumber(100, 0);
        ComplexNumber y = new ComplexNumber(0, 100);

        ComplexNumber xyadd = x.add(y);

        assertEquals(xyadd, new ComplexNumber(100, 100));
    }
}

