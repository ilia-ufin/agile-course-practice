package ru.unn.agile.complexnumber.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class MetelevEvgeniiTest {
    @Test
    public void canAddComplexNumbers() {
        // Arrange
        ComplexNumber z1 = new ComplexNumber(4, 0);
        ComplexNumber z2 = new ComplexNumber(0, 4);

        // Act
        ComplexNumber z = z1.add(z2);

        // Assert
        assertEquals(new ComplexNumber(4, 4), z);
    }

    @Test
    public void canMultiplyComplexNumbers() {
        // Arrange
        ComplexNumber z1 = new ComplexNumber(4, 1);
        ComplexNumber z2 = new ComplexNumber(1, 4);

        // Act
        ComplexNumber z = z1.multiply(z2);

        // Assert
        assertEquals(new ComplexNumber(0, 17), z);
    }

    @Test
    public void areComplexNumbersEqual() {
        // Arrange
        ComplexNumber z1 = new ComplexNumber(1, 1);
        ComplexNumber z2 = new ComplexNumber(1, 1);

        // Act
        // Assert
        assertTrue(z1.equals(z2));
    }

    @Test
    public void areComplexNumbersNotEqual() {
        // Arrange
        ComplexNumber z1 = new ComplexNumber(2, 1);
        ComplexNumber z2 = new ComplexNumber(1, 1);

        // Act
        // Assert
        assertFalse(z1.equals(z2));
    }
}
