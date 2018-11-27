package ru.unn.agile.mathstatistics.model;

public final class ProbabilityValidator {
    public static final Double LOWER_PROBABILITY_BOUND = 0.0;
    public static final Double UPPER_PROBABILITY_BOUND = 1.0;

    public static void validate(final Double probability) {
        if (probability == null) {
           throw new IllegalArgumentException("Probability should be initialized!");
        }

        if (probability < LOWER_PROBABILITY_BOUND
                || probability > UPPER_PROBABILITY_BOUND) {
            String errorMessage = "Probability should be less than "
                    + UPPER_PROBABILITY_BOUND + " and more than "
                    + LOWER_PROBABILITY_BOUND;

            throw new IllegalArgumentException(errorMessage);
        }
    }

    private ProbabilityValidator() {
    }
}
