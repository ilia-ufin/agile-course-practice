package ru.unn.agile.mathstatistics.model;

import org.junit.Test;

public class ProbabilityValidatorTests {

    @Test(expected = Test.None.class)
    public void canDetectCorrectProbability() {
        Double probability = (ProbabilityValidator.UPPER_PROBABILITY_BOUND
                + ProbabilityValidator.LOWER_PROBABILITY_BOUND) / 2.0;

        ProbabilityValidator.validate(probability);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canDetectProbabilityWhichIsHigherThanUpperBound() {
        Double probability = ProbabilityValidator.UPPER_PROBABILITY_BOUND + 1.0;

        ProbabilityValidator.validate(probability);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canDetectProbabilityWhichIsLessThanLowerBound() {
        Double probability = ProbabilityValidator.LOWER_PROBABILITY_BOUND - 1.0;

        ProbabilityValidator.validate(probability);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canDetectNullProbability() {
        Double probability = null;

        ProbabilityValidator.validate(probability);
    }
}
