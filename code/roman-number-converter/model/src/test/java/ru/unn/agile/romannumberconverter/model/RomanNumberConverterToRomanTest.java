package ru.unn.agile.romannumberconverter.model;

import ru.unn.agile.romannumberconverter.model.errorhandling.ArabicOutOfRangeException;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RomanNumberConverterToRomanTest {
    @Test
    public void convertToRomanTheNumber1Converted() {
        // Arrange
        int arabicNumber = 1;

        // Act
        String romanNumber = RomanNumberConverter.convertToRoman(arabicNumber);

        // Assert
        assertEquals("I", romanNumber);
    }

    @Test
    public void convertToRomanTheNumber2Converted() {
        // Arrange
        int arabicNumber = 2;

        // Act
        String romanNumber = RomanNumberConverter.convertToRoman(arabicNumber);

        // Assert
        assertEquals("II", romanNumber);
    }

    @Test
    public void convertToRomanTheNumber4Converted() {
        // Arrange
        int arabicNumber = 4;

        // Act
        String romanNumber = RomanNumberConverter.convertToRoman(arabicNumber);

        // Assert
        assertEquals("IV", romanNumber);
    }

    @Test
    public void convertToRomanTheNumber5Converted() {
        // Arrange
        int arabicNumber = 5;

        // Act
        String romanNumber = RomanNumberConverter.convertToRoman(arabicNumber);

        // Assert
        assertEquals("V", romanNumber);
    }

    @Test
    public void convertToRomanTheNumber7Converted() {
        // Arrange
        int arabicNumber = 7;

        // Act
        String romanNumber = RomanNumberConverter.convertToRoman(arabicNumber);

        // Assert
        assertEquals("VII", romanNumber);
    }

    @Test
    public void convertToRomanTheNumber9Converted() {
        // Arrange
        int arabicNumber = 9;

        // Act
        String romanNumber = RomanNumberConverter.convertToRoman(arabicNumber);

        // Assert
        assertEquals("IX", romanNumber);
    }

    @Test
    public void convertToRomanTheNumber34Converted() {
        // Arrange
        int arabicNumber = 34;

        // Act
        String romanNumber = RomanNumberConverter.convertToRoman(arabicNumber);

        // Assert
        assertEquals("XXXIV", romanNumber);
    }

    @Test
    public void convertToRomanTheNumber759Converted() {
        // Arrange
        int arabicNumber = 759;

        // Act
        String romanNumber = RomanNumberConverter.convertToRoman(arabicNumber);

        // Assert
        assertEquals("DCCLIX", romanNumber);
    }

    @Test
    public void convertToRomanTheNumber3999Converted() {
        // Arrange
        int arabicNumber = 3999;

        // Act
        String romanNumber = RomanNumberConverter.convertToRoman(arabicNumber);

        // Assert
        assertEquals("MMMCMXCIX", romanNumber);
    }

    @Test(expected = ArabicOutOfRangeException.class)
    public void convertToRomanTheNumber0EqualsToOutOfRangeValue() {
        // Arrange
        int arabicNumber = 0;

        // Act & Assert
        String romanNumber = RomanNumberConverter.convertToRoman(arabicNumber);
    }

    @Test(expected = ArabicOutOfRangeException.class)
    public void convertToRomanTheNumber4000EqualsToOutOfRangeValue() {
        // Arrange
        int arabicNumber = 4000;

        // Act & Assert
        String romanNumber = RomanNumberConverter.convertToRoman(arabicNumber);
    }
}
