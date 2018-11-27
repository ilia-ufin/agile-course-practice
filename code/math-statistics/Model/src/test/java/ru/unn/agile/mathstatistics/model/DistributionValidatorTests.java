package ru.unn.agile.mathstatistics.model;

import org.junit.Test;

public class DistributionValidatorTests {

    @Test(expected = Test.None.class)
    public void canDetectCorrectDistribution() {
        Number[] values = {7.3, 5.1, 9.3};
        Double[] probabilities = {0.3, 0.4, 0.3};

        DistributionValidator.validate(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenValuesIsNull() {
        Number[] values = null;
        Double[] probabilities = {1.0, null, 0.3};

        DistributionValidator.validate(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenProbabilitiesIsNull() {
        Number[] values = {7.3, 5.1, 9.3};
        Double[] probabilities = null;

        DistributionValidator.validate(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenValuesIsEmpty() {
        Number[] values = {};
        Double[] probabilities = {0.3, 0.4, 0.3};

        DistributionValidator.validate(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenProbabilitiesIsEmpty() {
        Number[] values = {7.3, 5.1, 9.3};
        Double[] probabilities = {};

        DistributionValidator.validate(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenSomeElementOfValuesIsNull() {
        Number[] values = {null, 5.1, 9.3};
        Double[] probabilities = {0.3, 0.4, 0.3};

        DistributionValidator.validate(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenElementOfProbabilitiesIsMoreThanOne() {
        Number[] values = {7.3, 5.1, 9.3};
        Double[] probabilities = {1.3, 0.4, 0.3};

        DistributionValidator.validate(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenElementOfProbabilitiesIsLessThanZero() {
        Number[] values = {7.3, 5.1, 9.3};
        Double[] probabilities = {-1.0, 0.4, 0.3};

        DistributionValidator.validate(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenSomeElementOfProbabilitiesIsNull() {
        Number[] values = {7.3, 5.1, 9.3};
        Double[] probabilities = {1.0, null, 0.3};

        DistributionValidator.validate(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenElementOfProbabilitiesSumIsNotEqualToOne() {
        Number[] values = {7.3, 5.1, 9.3};
        Double[] probabilities = {1.0, 0.4, 0.3};

        DistributionValidator.validate(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenSizesOfValuesAndProbabilitiesAreNotEqual() {
        Number[] values = {7.3, 5.1, 9.3, 1.5};
        Double[] probabilities = {0.3, 0.4, 0.3};

        DistributionValidator.validate(values, probabilities);
    }
}
