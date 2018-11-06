package ru.unn.agile.complexnumber.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class YufinIliaTest {
    @Test
    public void canMatchComplexNumbers() {
        ComplexNumber z1 = new ComplexNumber(1.0, 1.0);
        ComplexNumber z2 = new ComplexNumber(1.0, 1.0);

        assertEquals(z1, z2);
    }

    @Test
    public void canMultiplyComplexNumbers() {
        ComplexNumber z1 = new ComplexNumber(2.0, 3.0);
        ComplexNumber z2 = new ComplexNumber(4.0, 5.0);

        ComplexNumber z = z1.multiply(z2);

        assertEquals(new ComplexNumber(-7.0, 22.0), z);
    }

    @Test
    public void canMultiplyZeroByComplexNumber() {
        ComplexNumber z1 = new ComplexNumber(0.0, 0.0);
        ComplexNumber z2 = new ComplexNumber(0.0, 2.0);

        ComplexNumber z = z1.multiply(z2);

        assertEquals(new ComplexNumber(0.0, 0.0), z);
    }
}
