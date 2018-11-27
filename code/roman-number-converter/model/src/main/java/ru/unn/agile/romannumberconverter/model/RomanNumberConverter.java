package ru.unn.agile.romannumberconverter.model;

import ru.unn.agile.romannumberconverter.model.errorhandling.ArabicOutOfRangeException;
import ru.unn.agile.romannumberconverter.model.errorhandling.RomanIncorrectValueExeption;

import java.util.Map;
import java.util.TreeMap;

public final class RomanNumberConverter {
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 3999;

    public static String convertToRoman(final int arabicNumber) {
        if (arabicNumber < MIN_VALUE || arabicNumber > MAX_VALUE) {
            throw new ArabicOutOfRangeException();
        }

        return doConvertToRoman(arabicNumber);
    }

    public static int convertToArabic(final String romanNumber) {
        int arabicNumber = 0;
        char[] inputValue = romanNumber.toCharArray();
        int inputValueLength = inputValue.length;
        int i = 0;
        while (i < inputValueLength) {
            int res = 0;
            int step = 1;

            String str = String.valueOf(inputValue[i]);
            res = getBaseNumberMapKey(str);

            // This needs to define numbers like IX instead of I and X.
            if (i + 1 < inputValueLength) {
                str += String.valueOf(inputValue[i + 1]);
                int key = getBaseNumberMapKey(str);
                if (key != 0) {
                    res = key;
                    step = 2;
                }
            }

            if (res == 0) {
                throw new RomanIncorrectValueExeption();
            }
            arabicNumber += res;
            i += step;
        }

        if (arabicNumber == 0) {
            throw new RomanIncorrectValueExeption();
        }
        checkTheNumberOfRomanSymbolsIsEqualsToArabicNumber(romanNumber, arabicNumber);

        return arabicNumber;
    }

    private RomanNumberConverter() {
        throw new UnsupportedOperationException();
    }

    private static final Map<Integer, String> BASE_NUMBER_MAP = new TreeMap<Integer, String>();
    static {
        for (BaseRomanNumbers baseNumber : BaseRomanNumbers.values()) {
            BASE_NUMBER_MAP.put(baseNumber.arabicValue(), baseNumber.romanValue());
        }
    }

    private static String doConvertToRoman(final int arabicNumber) {
        String romanNumber = "";
        int nearestKey = ((TreeMap<Integer, String>) BASE_NUMBER_MAP).floorKey(arabicNumber);
        if (nearestKey == arabicNumber) {
            romanNumber = BASE_NUMBER_MAP.get(arabicNumber);
        } else {
            romanNumber = BASE_NUMBER_MAP.get(nearestKey)
                    + doConvertToRoman(arabicNumber - nearestKey);
        }
        return romanNumber;
    }

    private static int getBaseNumberMapKey(final String value) {
        int res = 0;
        for (Map.Entry e : BASE_NUMBER_MAP.entrySet()) {
            if (((String) e.getValue()).equals(value)) {
                res = ((int) e.getKey());
            }
        }

        return res;
    }

    private static void checkTheNumberOfRomanSymbolsIsEqualsToArabicNumber(
            final String romanNumber, final int arabicNumber) {
        if (!romanNumber.equals(doConvertToRoman(arabicNumber))) {
            throw new RomanIncorrectValueExeption();
        }
    }
}
