package ru.unn.agile.calculator.model;

public final class NumeralSystemConverter {

    private static final String UNKNOWN_NUMBER_LENGTH = "Unknown number length";
    private static final String UNKNOWN_NUMBER_SYSTEM = "Unknown number system";
    private static final int BINARY_SYSTEM = 2;
    private static final int OCTAL_SYSTEM = 8;
    private static final int HEXADECIMAL_SYSTEM = 16;

    private enum LetterToSystem {
        b(BINARY_SYSTEM), o(OCTAL_SYSTEM), x(HEXADECIMAL_SYSTEM);

        private final int value;
        LetterToSystem(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static int parse(final String number) {
        if (number.length() < 2) {
            throw new IllegalArgumentException(UNKNOWN_NUMBER_LENGTH);
        }
        final String letterSystem = number.substring(0, 1);

        int system;
        try {
            system = LetterToSystem.valueOf(letterSystem).getValue();
        } catch (Exception ex) {
            throw new IllegalArgumentException(UNKNOWN_NUMBER_SYSTEM, ex);
        }
        return Integer.parseInt(number.substring(1), system);
    }

    public static Integer tryParse(final String number) {
        try {
            return parse(number);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String format(final int number, final NumeralSystem system) {
        final boolean isNegative = number < 0;
        final int numberModule = Math.abs(number);
        String result;

        switch (system) {
            case BINARY:
                result = "b" + Integer.toBinaryString(numberModule);
                break;
            case OCTAL:
                result = "o" + Integer.toOctalString(numberModule);
                break;
            case HEXADECIMAL:
                result = "x" + Integer.toHexString(numberModule);
                break;
            default:
                throw new IllegalArgumentException(UNKNOWN_NUMBER_SYSTEM);
        }

        if (isNegative) {
            result = result.substring(0, 1) + "-" + result.substring(1);
        }
        return result;
    }

    private NumeralSystemConverter() {
        // no instance for class
    }
}
