package ru.unn.agile.numerical_integration.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class BaseDefinitionTest {
    private static final double DEFAULT_PRECISION = 0.001;
    private static final Expression FUNCTION_CUBE = x -> Math.pow(x, 3);
    private static final Class TESTED_CLASS = BaseDefinition.class;

    @Test
    public void shouldBeFinal() {
        UtilityClassTest.testForFinal(TESTED_CLASS);
    }

    @Test
    public void shouldHaveOneConstructor() {
        UtilityClassTest.shouldHaveOneConstructor(TESTED_CLASS);
    }

    @Test
    public void constructorShouldBePrivate()
            throws IllegalArgumentException, NoSuchMethodException {
        UtilityClassTest.constructorShouldBePrivate(TESTED_CLASS);
    }

    @Test
    public void constructorShouldThrowError()
            throws IllegalArgumentException, NoSuchMethodException {
        final String defaultMessage = "Don't make instance";
        UtilityClassTest.constructWithExeption(TESTED_CLASS, defaultMessage);
    }

    @Test
    public void canComputeCubicPolynomial() {
        double result = BaseDefinition.calculate(FUNCTION_CUBE, 0, 1, 100);

        assertEquals(0.25, result, DEFAULT_PRECISION);
    }

    @Test
    public void canReturnZeroWhenIntervalIsEmpty() {
        double result = BaseDefinition.calculate(FUNCTION_CUBE, 5, 5, 100);

        assertEquals(0, result, DEFAULT_PRECISION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canReturnErrorWhenSplitsNegative() {
        BaseDefinition.calculate(FUNCTION_CUBE, 0, 1, -100);
    }

    @Test
    public void canComputeWithManySteps() {
        double result = BaseDefinition.calculate(FUNCTION_CUBE, 0, 1, 20000);

        assertEquals(0.25, result, DEFAULT_PRECISION);
    }

    @Test
    public void canComputeWithInverseBoundaries() {
        double result = BaseDefinition.calculate(FUNCTION_CUBE, -1, 0, 100);

        assertEquals(-0.25, result, DEFAULT_PRECISION);
    }

    @Test
    public void hasSecondOrderOfConvergence() {
        final int n1 = 100;
        final int n2 = 1000;
        final double expectedRate = Math.pow((double) n2 / (double) n1, 2.0);
        final double expectedResult = 0.25;

        double result1 = BaseDefinition.calculate(FUNCTION_CUBE, 0, 1, n1);
        double result2 = BaseDefinition.calculate(FUNCTION_CUBE, 0, 1, n2);
        double error1 = Math.abs(result1 - expectedResult);
        double error2 = Math.abs(result2 - expectedResult);
        double actualRate = error1 / error2;

        assertEquals(expectedRate, actualRate, 1e-4);
    }
}
