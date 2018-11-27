package ru.unn.agile.romannumberconverter.model;

import ru.unn.agile.romannumberconverter.model.errorhandling.RomanIncorrectValueExeption;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RomanNumberConverterToArabicTest {
    @Test
    public void convertToArabicTheNumber1Converted() {
        // Arrange
        String romanNumber = "I";

        // Act
        int arabicNumber = RomanNumberConverter.convertToArabic(romanNumber);

        // Assert
        assertEquals(1, arabicNumber);
    }

    @Test
    public void convertToArabicTheNumber3Converted() {
        // Arrange
        String romanNumber = "III";

        // Act
        int arabicNumber = RomanNumberConverter.convertToArabic(romanNumber);

        // Assert
        assertEquals(3, arabicNumber);
    }

    @Test
    public void convertToArabicTheNumber4Converted() {
        // Arrange
        String romanNumber = "IV";

        // Act
        int arabicNumber = RomanNumberConverter.convertToArabic(romanNumber);

        // Assert
        assertEquals(4, arabicNumber);
    }

    @Test
    public void convertToArabicTheNumber5Converted() {
        // Arrange
        String romanNumber = "V";

        // Act
        int arabicNumber = RomanNumberConverter.convertToArabic(romanNumber);

        // Assert
        assertEquals(5, arabicNumber);
    }

    @Test
    public void convertToArabicTheNumber9Converted() {
        // Arrange
        String romanNumber = "IX";

        // Act
        int arabicNumber = RomanNumberConverter.convertToArabic(romanNumber);

        // Assert
        assertEquals(9, arabicNumber);
    }

    @Test
    public void convertToArabicTheNumber17Converted() {
        // Arrange
        String romanNumber = "XVII";

        // Act
        int arabicNumber = RomanNumberConverter.convertToArabic(romanNumber);

        // Assert
        assertEquals(17, arabicNumber);
    }

    @Test
    public void convertToArabicTheNumber76Converted() {
        // Arrange
        String romanNumber = "LXXVI";

        // Act
        int arabicNumber = RomanNumberConverter.convertToArabic(romanNumber);

        // Assert
        assertEquals(76, arabicNumber);
    }

    @Test
    public void convertToArabicTheNumber280Converted() {
        // Arrange
        String romanNumber = "CCLXXX";

        // Act
        int arabicNumber = RomanNumberConverter.convertToArabic(romanNumber);

        // Assert
        assertEquals(280, arabicNumber);
    }

    @Test
    public void convertToArabicTheNumber3999Converted() {
        // Arrange
        String romanNumber = "MMMCMXCIX";

        // Act
        int arabicNumber = RomanNumberConverter.convertToArabic(romanNumber);

        // Assert
        assertEquals(3999, arabicNumber);
    }

    @Test(expected = RomanIncorrectValueExeption.class)
    public void convertToArabicIncorrectStringWithNotOnlyRomanSymbolsEqualsToIncorrectRomanValue() {
        // Arrange
        String romanNumber = "IX89";

        // Act & Assert
        int arabicNumber = RomanNumberConverter.convertToArabic(romanNumber);
    }

    @Test(expected = RomanIncorrectValueExeption.class)
    public void convertToArabicIncorrectStringWithOnlyRomanSymbolsEqualsToIncorrectRomanValue() {
        // Arrange
        String romanNumber = "IXII";

        // Act & Assert
        int arabicNumber = RomanNumberConverter.convertToArabic(romanNumber);
    }
}
