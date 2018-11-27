package ru.unn.agile.numbersinwords.model;

import java.text.DecimalFormat;

public final class NumbersInWords {
    private static final String[] TENSNAMES = {
            "", " Ten", " Twenty", " Thirty", " Forty", " Fifty",
            " Sixty", " Seventy", " Eighty", " Ninety"
    };
    private static final String[] NUMNAMES = {
            "", " One", " Two", " Three", " Four", " Five", " Six",
            " Seven", " Eight", " Nine", " Ten",
            " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen",
            " Sixteen", " Seventeen", " Eighteen", " Nineteen"
    };
    private static final String[] DIGIT = {
            "", " thousand", " million", " billion"
    };

    private static final int TEN = 10;
    private static final int HUNDRED = 100;
    private static final int ZERO = 0;
    private static final int THREE = 3;
    private static final int SIX = 6;
    private static final int NINE = 9;
    private static final int TWELVE = 12;
    private static final int TWENTY = 20;
    private static final int ONE = 1;
    private static final int TWO = 2;

    private static final String MASK = "000000000000";

    private NumbersInWords() {
    }

    private static String convertLessThanOneThousand(final int number) {
        String partNumber;
        int newNumber = number;
        if (number % HUNDRED < TWENTY) {
            partNumber = NUMNAMES[number % HUNDRED];
            newNumber = number / HUNDRED;
        } else {
            partNumber = NUMNAMES[number % TEN];
            newNumber = number / TEN;
            partNumber = TENSNAMES[newNumber % TEN] + partNumber;
            newNumber = newNumber / TEN;
        }
        if (number == 0) {
            return partNumber;
        }
        if (newNumber == 0) {
            return partNumber;
        } else {
            return NUMNAMES[newNumber] + " hundred" + partNumber;
        }
    }

    private static String collectsNumber(final int number, final int index) {
        String result = "";
        if (number != 0) {
            result = convertLessThanOneThousand(number) + DIGIT[index];
        }
        return result;
    }

    public static String convert(final long number) {
        if (number == 0) {
            return "zero";
        }
        String snumber = Long.toString(number);
        DecimalFormat df = new DecimalFormat(MASK);
        snumber = df.format(number);

        int billions = Integer.parseInt(snumber.substring(ZERO, THREE));
        int millions  = Integer.parseInt(snumber.substring(THREE, SIX));
        int hundredThousands = Integer.parseInt(snumber.substring(SIX, NINE));
        int thousands = Integer.parseInt(snumber.substring(NINE, TWELVE));

        String result = collectsNumber(billions, THREE);
        result = result + collectsNumber(millions, TWO);
        result = result + collectsNumber(hundredThousands, ONE);

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        result =  result + tradThousand;
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }
}
