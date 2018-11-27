package ru.unn.agile.stringcalculator.model;

import ru.unn.agile.stringcalculator.model.errorhandling.NegativeNumberException;
import ru.unn.agile.stringcalculator.model.errorhandling.NotANumberException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringCalculator {
    private static final String DELIMITER_PATTERN = "(?s)^([^0-9])\\n.*";
    private static final String VALID_NUMBER_PATTERN = "^-?[0-9]+";
    private static final String DEFAULT_DELIMITER = ",";
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final char MINUS_SIGN = '-';

    private StringCalculator() {
        /* none */
    }

    public static int add(final String numbers) {
        int numbersSum = 0;
        if ("".equals(numbers)) {
            return numbersSum;
        }

        List<String> singleNumbers = getSeparatedNumbersFromString(numbers);
        checkAllNumbersAreValid(singleNumbers);

        for (String number : singleNumbers) {
            numbersSum += Integer.parseInt(number);
        }
        return numbersSum;
    }

    private static String normalizeDelimiters(final String inputString) {
        String delimiter = DEFAULT_DELIMITER;
        String modifiedNumbers = inputString;
        if (isNonDefaultDelimiterProvided(modifiedNumbers)) {
            delimiter = getDelimiter(modifiedNumbers);
            modifiedNumbers = removeDelimiterFromString(modifiedNumbers, delimiter);
        }
        modifiedNumbers = replaceDelimiterToDefault(modifiedNumbers, NEW_LINE_DELIMITER);
        modifiedNumbers = replaceDelimiterToDefault(modifiedNumbers, delimiter);
        return modifiedNumbers;
    }

    private static List<String> getNumbersArrayFromString(final String numbersString) {
        return Arrays.asList(numbersString.split(DEFAULT_DELIMITER));
    }

    private static boolean isNonDefaultDelimiterProvided(final String s) {
        return s.matches(DELIMITER_PATTERN);
    }

    private static String getDelimiter(final String s) {
        Pattern compiledDelimiterPattern = Pattern.compile(DELIMITER_PATTERN);
        Matcher patternMatch = compiledDelimiterPattern.matcher(s);
        patternMatch.find();
        return patternMatch.group(1);
    }

    private static String removeDelimiterFromString(final String s, final String delimiter) {
        return s.replace(delimiter + NEW_LINE_DELIMITER, "");
    }

    private static String replaceDelimiterToDefault(final String s, final String delimiter) {
        return s.replace(delimiter, DEFAULT_DELIMITER);
    }

    private static boolean isNumber(final String number) {
        return number.matches(VALID_NUMBER_PATTERN);
    }

    public static void checkAllNumbersAreValid(final List<String> numbers) {
        List<String> negativeNumbersList = new ArrayList<>();
        for (String number : numbers) {
            if (!isNumber(number)) {
                throw new NotANumberException("Not a number: " + number);
            }
            if (number.charAt(0) == MINUS_SIGN) {
                negativeNumbersList.add(number);
            }
        }
        if (!negativeNumbersList.isEmpty()) {
            String negativeNumberString = String.join(DEFAULT_DELIMITER, negativeNumbersList);
            throw new NegativeNumberException("Negative not allowed: " + negativeNumberString);
        }
    }

    public static List<String> getSeparatedNumbersFromString(final String numbers) {
        String commaSeparatedNumbers = normalizeDelimiters(numbers);
        return getNumbersArrayFromString(commaSeparatedNumbers);
    }
}
