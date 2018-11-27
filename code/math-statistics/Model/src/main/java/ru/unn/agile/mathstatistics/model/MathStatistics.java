package ru.unn.agile.mathstatistics.model;

public final class MathStatistics {
    public static final int EXPECTED_VALUE_ORDER = 1;
    public static final int DISPERSION_ORDER = 2;

    public static Double calculateExpectedValue(final Number[] values,
                                                final Double[] probabilities) {
        DistributionValidator.validate(values, probabilities);

        Double expectedValue = 0.0;
        for (int i = 0; i < values.length; ++i) {
            expectedValue += probabilities[i] * values[i].doubleValue();
        }
        return expectedValue;
    }

    public static Double calculateDispersion(final Number[] values,
                                             final Double[] probabilities) {
        final Double expectedValue = calculateExpectedValue(values, probabilities);

        Double dispersion = 0.0;
        for (int i = 0; i < values.length; ++i) {
            dispersion += probabilities[i]
                    * Math.pow((values[i].doubleValue() - expectedValue), DISPERSION_ORDER);
        }

        return dispersion;
    }

    public static Double calculateInitialMoment(final Number[] values,
                                                final Double[] probabilities,
                                                final int order) {
        return calculateMoment(values, probabilities, order, 0);
    }

    public static Double calculateCentralMoment(final Number[] values,
                                                final Double[] probabilities,
                                                final int order,
                                                final Number offset) {
        return calculateMoment(values, probabilities, order, offset);
    }

    private MathStatistics() {
    }

    private static Double calculateMoment(final Number[] values,
                                          final Double[] probabilities,
                                          final int order,
                                          final Number offset) {
        DistributionValidator.validate(values, probabilities);
        MomentOrderValidator.validate(order);

        Double moment = 0.0;
        for (int i = 0; i < values.length; ++i) {
            moment += probabilities[i]
                    * Math.pow((values[i].doubleValue() - offset.doubleValue()), order);
        }

        return moment;
    }
}
