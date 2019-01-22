package ru.unn.agile.complexnumber.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class MashaevArtemTest {
    @Test
    public void plusCmlxNmbrs() 
    {
        // Arrange
        ComplexNumber z1 = new ComplexNumber(6, 0);
        ComplexNumber z2 = new ComplexNumber(0, 6);

        // Act
        ComplexNumber z = z1.add(z2);

        // Assert
        assertEquals(new ComplexNumber(6, 6), z);
    }

    @Test
    public void MultiplyCmplxNmbrs() 
    {
        // Arrange
        ComplexNumber z1 = new ComplexNumber(6, 2);
        ComplexNumber z2 = new ComplexNumber(2, 6);

        // Act
        ComplexNumber z = z1.multiply(z2);

        // Assert
        assertEquals(new ComplexNumber(0, 40), z);
    }

    @Test
    public void EqualCmplxNmbrs() 
    {
        // Arrange
        ComplexNumber z1 = new ComplexNumber(2, 2);
        ComplexNumber z2 = new ComplexNumber(2, 2);

        // Act
        // Assert
        assertTrue(z1.equals(z2));
    }

    @Test
    public void NotEqialCmplxNmbrs() 
    {
        // Arrange
        ComplexNumber z1 = new ComplexNumber(4, 2);
        ComplexNumber z2 = new ComplexNumber(2, 2);

        // Act
        // Assert
        assertFalse(z1.equals(z2));
    }
}
