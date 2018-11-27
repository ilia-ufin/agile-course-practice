package ru.unn.agile.calculator.model;

import org.junit.Test;
import static org.junit.Assert.*;

public final class RadixCalculatorTest {
    @Test
    public void canAddTwoAndTwoBinary() {
        String n1 = "b10";
        String n2 = "b10";

        String expected = "b100";
        String result = RadixCalculator.add(n1, n2, NumeralSystem.BINARY);

        assertEquals(expected, result);
    }

    @Test
    public void canAddThreeAndTwoBinary() {
        String n1 = "b11";
        String n2 = "b10";

        String expected = "b101";
        String result = RadixCalculator.add(n1, n2, NumeralSystem.BINARY);

        assertEquals(expected, result);
    }

    @Test
    public void canAddTwoAndTwoOctalToBinary() {
        String n1 = "o2";
        String n2 = "o2";

        String expected = "b100";
        String result = RadixCalculator.add(n1, n2, NumeralSystem.BINARY);

        assertEquals(expected, result);
    }

    @Test
    public void canAddInDifferentSystems() {
        String n1 = "x10";
        String n2 = "b1111";

        String expected = "o37";
        String result = RadixCalculator.add(n1, n2, NumeralSystem.OCTAL);

        assertEquals(expected, result);
    }

    @Test
    public void checkCommutativityOfAddiction() {
        String n1 = "x10";
        String n2 = "b1111";

        String x = RadixCalculator.add(n1, n2, NumeralSystem.OCTAL);
        String y = RadixCalculator.add(n2, n1, NumeralSystem.OCTAL);

        assertEquals(x, y);
    }

    @Test
    public void checkAssociativityOfAddiction() {
        String n1 = "x10";
        String n2 = "b1111";
        String n3 = "o12";

        String x = RadixCalculator.add(
                (RadixCalculator.add(n1, n2, NumeralSystem.BINARY)), n3, NumeralSystem.OCTAL);
        String y = RadixCalculator.add(
                n1, (RadixCalculator.add(n2, n3, NumeralSystem.BINARY)), NumeralSystem.OCTAL);

        assertEquals(x, y);
    }

    @Test
    public void canMultiplyTwoAndTwoBinary() {
        String n1 = "b10";
        String n2 = "b10";

        String expected = "b100";
        String result = RadixCalculator.multiply(n1, n2, NumeralSystem.BINARY);

        assertEquals(expected, result);
    }

    @Test
    public void canMultiplyThreeAndTwoBinary() {
        String n1 = "b11";
        String n2 = "b10";

        String expected = "b110";
        String result = RadixCalculator.multiply(n1, n2, NumeralSystem.BINARY);

        assertEquals(expected, result);
    }

    @Test
    public void canMultiplyTwoAndTwoOctalToBinary() {
        String n1 = "o2";
        String n2 = "o2";

        String expected = "b100";
        String result = RadixCalculator.multiply(n1, n2, NumeralSystem.BINARY);

        assertEquals(expected, result);
    }

    @Test
    public void checkCommutativityOfMultiplication() {
        String n1 = "x10";
        String n2 = "b1111";

        String x = RadixCalculator.multiply(n1, n2, NumeralSystem.OCTAL);
        String y = RadixCalculator.multiply(n2, n1, NumeralSystem.OCTAL);

        assertEquals(x, y);
    }

    @Test
    public void checkAssociativityOfMultiplication() {
        String n1 = "x10";
        String n2 = "b1111";
        String n3 = "o12";

        String x = RadixCalculator.multiply(
                (RadixCalculator.multiply(n1, n2, NumeralSystem.BINARY)), n3, NumeralSystem.OCTAL);
        String y = RadixCalculator.multiply(
                n1, (RadixCalculator.multiply(n2, n3, NumeralSystem.BINARY)), NumeralSystem.OCTAL);

        assertEquals(x, y);
    }

    @Test
    public void canUnaryMinusTwoBinary() {
        String number = "b10";

        String expected = "b-10";
        String result = RadixCalculator.unaryMinus(number, NumeralSystem.BINARY);

        assertEquals(expected, result);
    }

    @Test
    public void canMultiplyNegative() {
        String n1 = "x-10";
        String n2 = "b-1111";

        String expected = "o360";
        String result = RadixCalculator.multiply(n1, n2, NumeralSystem.OCTAL);

        assertEquals(expected, result);
    }

    @Test
    public void canUnaryMinusThreeBinary() {
        String number = "b11";

        String expected = "b-11";
        String result = RadixCalculator.unaryMinus(number, NumeralSystem.BINARY);

        assertEquals(expected, result);
    }

    @Test
    public void canUnaryMinusZeroHexadecimal() {
        String number = "x-0";

        String expected = "o0";
        String result = RadixCalculator.unaryMinus(number, NumeralSystem.OCTAL);

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotAddWithInvalidArgument() {
        String n1 = "10";
        String n2 = "b1111";
        RadixCalculator.add(n1, n2, NumeralSystem.OCTAL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotMultiplyWithInvalidArgument() {
        String n1 = "10";
        String n2 = "b1111";
        RadixCalculator.multiply(n1, n2, NumeralSystem.OCTAL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotUnaryMinusInvalidArgument() {
        String number = "10";
        RadixCalculator.unaryMinus(number, NumeralSystem.OCTAL);
    }
}
