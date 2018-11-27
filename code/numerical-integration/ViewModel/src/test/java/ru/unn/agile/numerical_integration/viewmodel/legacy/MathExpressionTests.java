package ru.unn.agile.numerical_integration.ViewModel.legacy;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class MathExpressionTests {
    private static final double DOUBLE_PRECISION = 1e-15;

    @Test
    public void canComputeNumber() {
        MathExpression parser = new MathExpression("10");

        boolean status = parser.eval(0);
        double result = parser.getResult();

        assertTrue(status);
        assertEquals(10.0, result, DOUBLE_PRECISION);
    }

    @Test
    public void canComputeNegativeNumber() {
        MathExpression parser = new MathExpression("-10");

        boolean status = parser.eval(0);
        double result = parser.getResult();

        assertTrue(status);
        assertEquals(-10.0, result, DOUBLE_PRECISION);
    }

    @Test
    public void canComputeNothing() {
        MathExpression parser = new MathExpression("");

        boolean status = parser.eval(0);
        double result = parser.getResult();

        assertTrue(status);
        assertEquals(0.0, result, DOUBLE_PRECISION);
    }

    @Test
    public void canComputeVariable() {
        MathExpression parser = new MathExpression("x");

        boolean status = parser.eval(10);
        double result = parser.getResult();

        assertTrue(status);
        assertEquals(10.0, result, DOUBLE_PRECISION);
    }

    @Test
    public void canComputeNegatedVariable() {
        MathExpression parser = new MathExpression("-x");

        boolean status = parser.eval(10);
        double result = parser.getResult();

        assertTrue(status);
        assertEquals(-10.0, result, DOUBLE_PRECISION);
    }

    @Test
    public void canComputeWhitespaces() {
        MathExpression parser = new MathExpression("   ");

        boolean status = parser.eval(10);
        double result = parser.getResult();

        assertTrue(status);
        assertEquals(0.0, result, DOUBLE_PRECISION);
    }

    @Test
    public void canComputeNumberWithinWhitespaces() {
        MathExpression parser = new MathExpression("   5   ");

        boolean status = parser.eval(10);
        double result = parser.getResult();

        assertTrue(status);
        assertEquals(5.0, result, DOUBLE_PRECISION);
    }

    @Test
    public void canComputeSum() {
        MathExpression parser = new MathExpression(" 5 + 2  ");

        boolean status = parser.eval(10);
        double result = parser.getResult();

        assertTrue(status);
        assertEquals(7.0, result, DOUBLE_PRECISION);
    }

    @Test
    public void canComputeSumOfNumbers() {
        MathExpression parser = new MathExpression(" 5 + 2  ");

        boolean status = parser.eval(10);
        double result = parser.getResult();

        assertTrue(status);
        assertEquals(7.0, result, DOUBLE_PRECISION);
    }

    @Test
    public void canComputeSumOfVariables() {
        MathExpression parser = new MathExpression(" x + x  ");

        boolean status = parser.eval(10);
        double result = parser.getResult();

        assertTrue(status);
        assertEquals(20.0, result, DOUBLE_PRECISION);
    }

    @Test
    public void canComputeProduct() {
        MathExpression parser = new MathExpression(" 5 * x  ");

        boolean status = parser.eval(10);
        double result = parser.getResult();

        assertTrue(status);
        assertEquals(50.0, result, DOUBLE_PRECISION);
    }

    @Test
    public void canComputeDifference() {
        MathExpression parser = new MathExpression(" 5 - x  ");

        boolean status = parser.eval(10);
        double result = parser.getResult();

        assertTrue(status);
        assertEquals(-5.0, result, DOUBLE_PRECISION);
    }

    @Test
    public void canComputeRatio() {
        MathExpression parser = new MathExpression(" -5 / x  ");

        boolean status = parser.eval(10);
        double result = parser.getResult();

        assertTrue(status);
        assertEquals(-0.5, result, DOUBLE_PRECISION);
    }

    @Test
    public void canComputeInRightOrder() {
        MathExpression parser1 = new MathExpression(" 2 + 2 * 2  ");
        MathExpression parser2 = new MathExpression(" 2 * 2 + 2  ");

        parser1.eval(10);
        double result1 = parser1.getResult();

        parser2.eval(10);
        double result2 = parser2.getResult();

        assertEquals(result1, result2, DOUBLE_PRECISION);
    }

    @Test
    public void canComputeTwice() {
        MathExpression parser = new MathExpression(" 2 * x - 7  ");
        parser.eval(10);

        parser.eval(20);
        double result = parser.getResult();

        assertEquals(33.0, result, DOUBLE_PRECISION);
    }

    @Test
    public void voidHasErrorWhenComputingIncorrectExpression() {
        MathExpression parser = new MathExpression("x1");

        boolean success = parser.eval(0);

        assertFalse(success);
    }
}
