package ru.unn.agile.mathstatistics.model;

public final class NormalizationConditionChecker {
    public static final Double EPSILON = 1e-3;

    public static final Double EXPECTED_PROBABILITIES_SUM = 1.0;

    public enum Status {
        CONDITION_IS_MET,
        LESS_THAN_ZERO,
        BETWEEN_ZERO_AND_ONE,
        MORE_THAN_ONE
    }

    public static Status check(final Double[] probabilities) {
        ArrayValidator.validate(probabilities);

        Double sum = 0.0;
        for (int i = 0; i < probabilities.length; ++i) {
            sum += probabilities[i];
        }

        if (sum < 0.0) {
            return Status.LESS_THAN_ZERO;
        }

        if ((sum - EXPECTED_PROBABILITIES_SUM) > EPSILON) {
            return Status.MORE_THAN_ONE;
        }

        if ((EXPECTED_PROBABILITIES_SUM - sum) > EPSILON) {
            return Status.BETWEEN_ZERO_AND_ONE;
        }

        return Status.CONDITION_IS_MET;
    }

    private NormalizationConditionChecker() {
    }
}
