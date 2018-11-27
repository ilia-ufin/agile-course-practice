package ru.unn.agile.numerical_integration.Model;

public final class BaseDefinition {
    private BaseDefinition() {
        throw new AssertionError("Don't make instance");
    }

    public static double calculate(
            final Expression function,
            final double begin,
            final double end,
            final int splitsNumber
    ) {
        if (splitsNumber < 1) {
            throw new IllegalArgumentException(
                "Expected positive integer number of splits, got: "
                + Integer.toString(splitsNumber));
        }

        double result = 0.0;

        final double dx = (end - begin) / splitsNumber;
        for (int i = 0; i < splitsNumber; ++i) {
            final double x = begin + dx * 0.5 + dx * i;
            result += function.execute(x) * dx;
        }
        return result;
    }
}
