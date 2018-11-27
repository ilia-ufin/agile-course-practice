package ru.unn.agile.mathstatistics.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NormalizationConditionCheckerTests {

    @Test
    public void isNormalConditionSatisfiedForCorrectProbabilities() {
        Double[] probabilities = {0.25, 0.25, 0.25, 0.25};

        assertEquals(NormalizationConditionChecker.Status.CONDITION_IS_MET,
                NormalizationConditionChecker.check(probabilities));
    }

    @Test
    public void isNormalConditionNotSatisfiedForNegativeProbabilities() {
        Double[] probabilities = {-0.25, -0.25, -0.25, -0.25};

        assertEquals(NormalizationConditionChecker.Status.LESS_THAN_ZERO,
                NormalizationConditionChecker.check(probabilities));
    }

    @Test
    public void isNormalConditionNotSatisfiedForProbabilitiesWithInsufficientSum() {
        Double[] probabilities = {0.25, 0.25, 0.25};

        assertEquals(NormalizationConditionChecker.Status.BETWEEN_ZERO_AND_ONE,
                NormalizationConditionChecker.check(probabilities));
    }

    @Test
    public void isNormalConditionNotSatisfiedForProbabilitiesWithGreaterSum() {
        Double[] probabilities = {0.25, 0.25, 0.25, 0.25, 0.25};

        assertEquals(NormalizationConditionChecker.Status.MORE_THAN_ONE,
                NormalizationConditionChecker.check(probabilities));
    }
}
