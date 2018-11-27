package ru.unn.agile.calculator.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumeralSystemConverterTest {
    @Test
    public void canParseZeroFromBinary() {
        int expected = 0;
        int result = NumeralSystemConverter.parse("b0");
        assertEquals(expected, result);
    }

    @Test
    public void canParseOneFromBinary() {
        int expected = 1;
        int result = NumeralSystemConverter.parse("b1");
        assertEquals(expected, result);
    }


    @Test
    public void canParseTwoFromBinary() {
        int expected = 2;
        int result = NumeralSystemConverter.parse("b10");
        assertEquals(expected, result);
    }

    @Test
    public void canParseZeroFromOctal() {
        int expected = 0;
        int result = NumeralSystemConverter.parse("o0");
        assertEquals(expected, result);
    }

    @Test
    public void canParseOneFromOctal() {
        int expected = 1;
        int result = NumeralSystemConverter.parse("o1");
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotParseEmptyNumber() {
        NumeralSystemConverter.parse("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotParseFromInvalidBinaryNumber() {
        NumeralSystemConverter.parse("oba");
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotParseFromInvalidOctalNumber() {
        NumeralSystemConverter.parse("oba");
    }

    @Test
    public void canParseTenFromHexadecimalNumber() {
        int expected = 10;
        int result = NumeralSystemConverter.parse("xa");
        assertEquals(expected, result);
    }

    @Test
    public void checkTryParseFiveFromBinary() {
        Integer expected = 5;
        Integer result = NumeralSystemConverter.tryParse("b101");
        assertEquals(expected, result);
    }

    @Test
    public void checkTryParseNoException() {
        Integer result = NumeralSystemConverter.tryParse("b1021");
        assertNull(result);
    }

    @Test
    public void canParseMinIntValue() {
        int expected = Integer.MIN_VALUE;
        int result = NumeralSystemConverter.parse("x-80000000");
        assertEquals(expected, result);
    }

    @Test
    public void canParseMaxIntValue() {
        int expected = Integer.MAX_VALUE;
        int result = NumeralSystemConverter.parse("x7fffffff");
        assertEquals(expected, result);
    }

    @Test (expected = NumberFormatException.class)
    public void checkIntegerOverflow() {
        NumeralSystemConverter.parse("x80000000");
    }

    @Test
    public void canConvertFiveToBinary() {
        String expected = "b101";
        String result = NumeralSystemConverter.format(5, NumeralSystem.BINARY);
        assertEquals(expected, result);
    }

    @Test
    public void canConverEightToBinary() {
        String expected = "b1000";
        String result = NumeralSystemConverter.format(8, NumeralSystem.BINARY);
        assertEquals(expected, result);
    }

    @Test
    public void canConvertFiveToOctal() {
        String expected = "o5";
        String result = NumeralSystemConverter.format(5, NumeralSystem.OCTAL);
        assertEquals(expected, result);
    }

    @Test
    public void canConvertTwelveToHexadecimal() {
        String expected = "xc";
        String result = NumeralSystemConverter.format(12, NumeralSystem.HEXADECIMAL);
        assertEquals(expected, result);
    }

    @Test
    public void canConvertMaxIntToHexadecimal() {
        String expected = "x7fffffff";
        String result = NumeralSystemConverter.format(Integer.MAX_VALUE, NumeralSystem.HEXADECIMAL);
        assertEquals(expected, result);
    }

    @Test
    public void canParseNegative() {
        int expected = -10;
        int result = NumeralSystemConverter.parse("b-1010");
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotFormatUnknownNumberSystem() {
        NumeralSystemConverter.format(5, NumeralSystem.UNKNOWN);
    }
}
