package ru.unn.agile.InfixToPostfixNotationConverter.model;

import java.util.*;

public final class InfixToPostfixNotationConverter {

    private static final String OPERATORS = "-+*/";
    private static final String WHITESPACE_PATTERN = "\\s+";

    private InfixToPostfixNotationConverter() { }

    public static boolean isValidExpression(final String expression) {
        final String positiveNumber = "\\d+";
        final String negativeNumber = "[(]-\\d+[)]";
        final String number = "(" + positiveNumber + "|" + negativeNumber + ")";
        final String arithmeticOperation = "[" + OPERATORS + "]";
        final String expressionPattern = number + "(" + arithmeticOperation + number + "){0,}";

        String stripped = expression.replaceAll(WHITESPACE_PATTERN, "");
        if (!isValidParenthesesPlacement(stripped)) {
            return false;
        }
        stripped = stripped.replaceAll(negativeNumber, "0");
        stripped = stripped.replaceAll("[(]", "").replaceAll("[)]", "");

        stripped = stripped.replaceAll(expressionPattern, "");
        stripped = stripped.replaceAll("[(]", "").replaceAll("[)]", "");
        System.out.println(stripped);

        return stripped.isEmpty();
    }

    public static String[] convert(final String input) throws RuntimeException {
        if (!isValidExpression(input)) {
            throw new RuntimeException("Bad Input");
        }

        String output = "";
        Stack<String> stack = new Stack<>();
            String[] expression = splitToStringArray(input);

        for (String str : expression) {
            if ("(".equals(str)) {
                stack.push(str);
            } else if (isNumber(str)) {
                output += str.concat(" ");
            } else if (")".equals(str)) {
                while (!stack.peek().equals("(")) {
                    output += stack.pop().concat(" ");
                }
                stack.pop();
            } else if (isOperator(str)) {
                while (!stack.isEmpty() && getPriority(str) <= getPriority(stack.peek())) {
                    output += stack.pop().concat(" ");
                }
                stack.push(str);
            }
        }
        while (!stack.isEmpty()) {
            output += stack.pop().concat(" ");
        }

        return output.split(WHITESPACE_PATTERN);
    }

    public static int calculateResult(final String input) {
        String[] postfix = convert(input);
        Stack<Integer> stack = new Stack<>();
        for (String str : postfix) {
            if (isNumber(str)) {
                stack.push(Integer.parseInt(str));
            } else if (OPERATORS.contains(str)) {
                int firstOperand = stack.pop();
                int secondOperand = stack.pop();
                switch (str) {
                    case "+":
                        stack.push(secondOperand + firstOperand);
                        break;
                    case "-":
                        stack.push(secondOperand - firstOperand);
                        break;
                    case "*":
                        stack.push(secondOperand * firstOperand);
                        break;
                    case "/":
                        stack.push(secondOperand / firstOperand);
                        break;
                    default:
                        break;
                }
            }
        }
        return stack.pop();
    }

    private static boolean isValidParenthesesPlacement(final String expression) {
        Stack<Integer> parenthesesStack = new Stack<>();

        try {
            for (char c : expression.toCharArray()) {
                if (c == '(') {
                    parenthesesStack.push(0);
                } else if (c == ')') {
                    parenthesesStack.pop();
                }
            }
        } catch (EmptyStackException exc) {
            return false;
        }

        return parenthesesStack.empty();
    }

    private static int getPriority(final String operator) {
        switch (operator) {
            case "(":
            case ")":
                return 0;
            case "+":
            case "-":
                return 1;
            case "/":
            case "*":
                return 2;
            default:
                return -1;
        }
    }

    private static boolean isNumber(final String str) {
        final String numberPattern = "^-?[0-9]+";
        return str.matches(numberPattern);
    }

    private static boolean isOperator(final String str) {
        return OPERATORS.contains(str);
    }

    private static String[] splitToStringArray(final String input) {
        String[] result = input.replaceAll(WHITESPACE_PATTERN, "").split("");
        String output = "";
        for (int i = 0; i < result.length; i++) {
            if (isNumber(result[i])) {
                output += result[i];
            } else if (isOperator(result[i]) || "(".equals(result[i])
                    || ")".equals(result[i])) {
                output += " ".concat(result[i]);
                output += " ";
            }
        }
        return output.split(WHITESPACE_PATTERN);
    }
}
