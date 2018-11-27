package ru.unn.agile.mathstatistics.model;

public final class DistributionValidator {
    public static void validate(final Number[] values, final Double[] probabilities) {
        validateValues(values);
        validateProbabilities(probabilities);

        if (values.length != probabilities.length) {
            throw new IllegalArgumentException("Values and probabilities sizes aren't equal!");
        }
    }

    private DistributionValidator() {
    }

    private static void validateValues(final Number[] values) {
        ArrayValidator.validate(values);

        if (values.length < 1) {
            throw new IllegalArgumentException("Data array length should be positive!");
        }
    }

    private static void validateProbabilities(final Double[] probabilities) {
        ArrayValidator.validate(probabilities);

        if (probabilities.length < 1) {
            throw new IllegalArgumentException("Probability array length should be positive!");
        }

        for (int i = 0; i < probabilities.length; ++i) {
            ProbabilityValidator.validate(probabilities[i]);
        }

        if (NormalizationConditionChecker.check(probabilities)
                != NormalizationConditionChecker.Status.CONDITION_IS_MET) {
            throw new IllegalArgumentException("Normalization condition is not satisfied!");
        }
    }
}
