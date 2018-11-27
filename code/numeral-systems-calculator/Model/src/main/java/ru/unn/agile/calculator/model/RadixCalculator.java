package ru.unn.agile.calculator.model;

public final class RadixCalculator {

    private static final String INVALID_ARGUMENTS = "Invalid arguments";

    private static void checkIntegersNotNull(final Integer ... integers) {
        for (Integer integer : integers) {
            if (integer == null) {
                throw new IllegalArgumentException(INVALID_ARGUMENTS);
            }
        }
    }

    public static String add(final String n1, final String n2, final NumeralSystem outputFormat) {
        final Integer firstAddArgument = NumeralSystemConverter.tryParse(n1);
        final Integer secondAddArgument = NumeralSystemConverter.tryParse(n2);
        checkIntegersNotNull(firstAddArgument, secondAddArgument);
        int result = firstAddArgument + secondAddArgument;
        return NumeralSystemConverter.format(result, outputFormat);
    }

    public static String multiply(final String n1, final String n2,
                                  final NumeralSystem outputFormat) {
        final Integer firstMulArgument = NumeralSystemConverter.tryParse(n2);
        final Integer secondMulArgument = NumeralSystemConverter.tryParse(n1);
        checkIntegersNotNull(firstMulArgument, secondMulArgument);
        int result = firstMulArgument * secondMulArgument;
        return NumeralSystemConverter.format(result, outputFormat);
    }

    public static String unaryMinus(final String number, final NumeralSystem outputFormat) {
        final Integer value = NumeralSystemConverter.tryParse(number);
        checkIntegersNotNull(value);
        int result = -value;
        return NumeralSystemConverter.format(result, outputFormat);
    }


    private RadixCalculator() {
        // no instance for class
    }
}
