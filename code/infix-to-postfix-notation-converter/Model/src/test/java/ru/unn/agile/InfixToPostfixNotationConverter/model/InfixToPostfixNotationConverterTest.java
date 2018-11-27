package ru.unn.agile.InfixToPostfixNotationConverter.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class InfixToPostfixNotationConverterTest {
    @Test(expected = RuntimeException.class)
    public void conversionThrowsWhenInputHasTwoTokensInARow() {
        InfixToPostfixNotationConverter.convert("44++");
    }

    @Test(expected = RuntimeException.class)
    public void conversionThrowsWhenNumbersLeftBracketsNotEqualsRightBrackets() {
        String input = "5 / (7 - (1 + 1))) * 5";
        InfixToPostfixNotationConverter.convert(input);
    }

    @Test
    public void canConvertSimpleExpression() {
        String[] result = InfixToPostfixNotationConverter.convert("(14 + 3)*2");
        String[] expected = {"14", "3", "+", "2", "*"};

        assertArrayEquals(expected, result);
    }

    @Test
    public void canConvertExpressionWithBrackets() {
        String[] result = InfixToPostfixNotationConverter.convert("((5 / (7 - (1 + 1))) * 5) - 2");
        String[] expected = {"5", "7", "1", "1", "+", "-", "/", "5", "*", "2", "-"};

        assertArrayEquals(expected, result);
    }

    @Test
    public void canAddTwoNumbers() {
        int result = InfixToPostfixNotationConverter.calculateResult("4 + 3");

        assertEquals(7, result);
    }

    @Test
    public void canCalculateResultWithBrackets() {
        int result = InfixToPostfixNotationConverter.calculateResult("(4 + 3) * 2");

        assertEquals(14, result);
    }


    @Test
    public void canCalculateHardExpression() {
        String input = "((5 / (7 - (1 + 1))) * 5) - (2 + (1 + 1))";
        int result = InfixToPostfixNotationConverter.calculateResult(input);

        assertEquals(1, result);
    }

    @Test
    public void canSeparateExpressionWithWhitespace() {
        String[] result = InfixToPostfixNotationConverter.convert("14 + 3");
        String[] expected = {"14", "3", "+"};

        assertArrayEquals(expected, result);
    }

    @Test
    public void canCalculateMultipleOperationsInARow() {
        String expression = "1+1+1";
        String[] expected = {"1", "1", "+", "1", "+"};

        String[] result = InfixToPostfixNotationConverter.convert(expression);

        assertArrayEquals(expected, result);
    }
}
