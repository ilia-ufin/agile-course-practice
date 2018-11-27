package ru.unn.agile.mathstatistics.model;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class MathStatisticsTests {

    public static final Double EPSILON = 1e-3;

    private Integer[] getMonotonicArray(final int start, final int end) {
        Integer[] data = new Integer[end - start];
        for (int i = 0; i < data.length; ++i) {
            data[i] = start + i;
        }
        return data;
    }

    @Test
    public void canCalculateExpectedValueForOneValue() {
        Integer[] values = {10};
        Double[]  probabilities = {1.0};

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);

        assertEquals(new Double(10), expectedValue);
    }

    @Test
    public void canCalculateExpectedValueForOneNegativeValue() {
        Integer[] values = {-23};
        Double[]  probabilities = {1.0};

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);

        assertEquals(new Double(-23), expectedValue);
    }

    @Test
    public void canCalculateExpectedValueForPositiveValues() {
        Integer[] values = {10, 5, 25, 30};
        Double[]  probabilities = {0.25, 0.25, 0.25, 0.25};

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);

        assertEquals(new Double(17.5), expectedValue);
    }

    @Test
    public void canCalculateExpectedValueForDoubleValues() {
        Double[] values = {1.2, 3.3, 0.2, 8.1, 0.9};
        Double[] probability = {0.2, 0.2, 0.2, 0.2, 0.2};

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probability);

        assertEquals(new Double(2.74), expectedValue);
    }

    @Test
    public void canCalculateExpectedValueForIntegerAndDoubleValues() {
        Number[] values = {1.2, 3.3, 11, 2, 8.9, 2, 36, 7.3};
        Double[] probabilities = {0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125};

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);

        assertEquals(new Double(8.9625), expectedValue);
    }

    @Test
    public void canCalculateExpectedValueForSomeNegativeValues() {
        Number[] values = {-3, 8.3, 86.2, -3.5};
        Double[] probabilities = {0.25, 0.25, 0.25, 0.25};

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);

        assertEquals(new Double(22.0), expectedValue);
    }

    @Test
    public void canCalculateExpectedValueForBigDistribution() {
        Integer[] values = getMonotonicArray(0, 100);
        Double[]  probabilities = new Double[100];
        Arrays.fill(probabilities, 0.01);

        Double expected = new Double((0 + 99) * 100 / 2) * 0.01;

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);

        assertEquals(expected, expectedValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateExpectedValueForValuesWithSomeNullValue() {
        Number[] values = {1.2, 2, -5, null, 7};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        MathStatistics.calculateExpectedValue(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateExpectedValueForNullValue() {
        Double[] values = null;
        Double[] probabilities = {1.0};

        MathStatistics.calculateExpectedValue(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateExpectedValueForEmptyValues() {
        Number[] values = {};
        Double[] probabilities = {1.0};

        MathStatistics.calculateExpectedValue(values, probabilities);
    }

    @Test
    public void canCalculateDispersionForOneValue() {
        Integer[] values = {8};
        Double[]  probabilities = {1.0};

        Double dispersion = MathStatistics.calculateDispersion(values, probabilities);

        assertEquals(new Double(0), dispersion);
    }

    @Test
    public void canCalculateDispersionForOneNegativeValue() {
        Integer[] values = {-23};
        Double[]  probabilities = {1.0};

        Double dispersion = MathStatistics.calculateDispersion(values, probabilities);

        assertEquals(new Double(0), dispersion);
    }

    @Test
    public void canCalculateDispersionForNegativeValues() {
        Number[] values = {9, 3, -1, 5, -8};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        Double dispersion = MathStatistics.calculateDispersion(values, probabilities);

        assertEquals(new Double(33.44), dispersion);
    }

    @Test
    public void canCalculateDispersionForSimilarValues() {
        Number[] values = {0, 0, 0, 0};
        Double[] probabilities = {0.25, 0.25, 0.25, 0.25};

        Double dispersion = MathStatistics.calculateDispersion(values, probabilities);

        assertEquals(new Double(0.0), dispersion);
    }

    @Test
    public void canCalculateDispersionForDoubleValues() {
        Number[] values = {7.2, 64.3, -13.8, 0.6, 23.1};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        Double dispersion = MathStatistics.calculateDispersion(values, probabilities);

        assertEquals(new Double(717.1096), dispersion);
    }

    @Test
    public void canCalculateDispersionForBigDistribution() {
        Integer[] values = getMonotonicArray(0, 100);
        Double[]  probabilities = new Double[100];
        Arrays.fill(probabilities, 0.01);

        Double dispersion = MathStatistics.calculateDispersion(values, probabilities);

        assertTrue(Math.abs(833.2499 - dispersion) <= EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateDispersionForValuesWithSomeNullValue() {
        Number[] values = {2, -3, 5, null, 8};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        MathStatistics.calculateDispersion(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateDispersionForNullValue() {
        Double[] values = null;
        Double[] probabilities = {1.0};

        MathStatistics.calculateDispersion(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateDispersionForEmptyValues() {
        Number[] values = {};
        Double[] probabilities = {1.0};

        MathStatistics.calculateDispersion(values, probabilities);
    }

    @Test
    public void canCalculateCentralMomentForOneValue() {
        Integer[] values = {3};
        Double[]  probabilities = {1.0};

        Double moment = MathStatistics.calculateCentralMoment(values, probabilities, 3, 46);

        assertEquals(new Double(-79507.0), moment);
    }

    @Test
    public void canCalculateCentralMomentForOneNegativeValue() {
        Integer[] values = {-11};
        Double[]  probabilities = {1.0};

        Double moment = MathStatistics.calculateCentralMoment(values, probabilities, 1, 44);

        assertEquals(new Double(-55), moment);
    }

    @Test
    public void canCalculateCentralMomentForSomeNegativeValues() {
        Number[] values = {9, 3, -1, 9, -8};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        Double moment = MathStatistics.calculateCentralMoment(values, probabilities, 4, 9);

        assertEquals(new Double(18963.4), moment);
    }

    @Test
    public void canCalculateCentralMomentForSimilarValues() {
        Number[] values = {0, 0, 0, 0};
        Double[] probabilities = {0.25, 0.25, 0.25, 0.25};

        Double moment = MathStatistics.calculateCentralMoment(values, probabilities, 5, 2);

        assertEquals(new Double(-32.0), moment);
    }

    @Test
    public void canCalculateCentralMomentForDoubleValues() {
        Number[] values = {7.1, 6.3, 11.8, 0.8, 23.2};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        Double moment = MathStatistics.calculateCentralMoment(values, probabilities, 3, 3);

        assertTrue(Math.abs(1803.618 - moment) <= EPSILON);
    }

    @Test
    public void canCalculateCentralMomentForBigDistribution() {
        Integer[] values = getMonotonicArray(-400, 400);
        Double[]  probabilities = new Double[800];
        Arrays.fill(probabilities, 0.00125);

        Double moment = MathStatistics.calculateCentralMoment(values, probabilities, 3, 1);

        assertTrue(Math.abs(-240003.0 - moment) <= EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateCentralMomentForSomeNullValue() {
        Number[] values = {1, null, 7, 2, 2};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        MathStatistics.calculateCentralMoment(values, probabilities, 3, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateCentralMomentForEmptyValues() {
        Number[] values = {};
        Double[] probabilities = {1.0};

        MathStatistics.calculateCentralMoment(values, probabilities, 2, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateCentralMomentWithNullOffset() {
        Number[] values = {};
        Double[] probabilities = {1.0};

        MathStatistics.calculateCentralMoment(values, probabilities, 2, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateCentralMomentOfZeroOrder() {
        Number[] values = {0.1, 9.3, 1.8, 0.8, 2.2};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        MathStatistics.calculateCentralMoment(values, probabilities, 0, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateCentralMomentOfNegativeOrder() {
        Number[] values = {0.1, 0.3, 6.8, -6.8, 2.2};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        MathStatistics.calculateCentralMoment(values, probabilities, -5, 3);
    }

    @Test
    public void isExpectedValueEqualToInitialMomentOfFirstOrder() {
        Number[] values = {0, -3, 0.1, 0, 1.9};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);
        Double moment = MathStatistics.calculateInitialMoment(
                values, probabilities, MathStatistics.EXPECTED_VALUE_ORDER);

        assertEquals(expectedValue, moment);
    }

    @Test
    public void isDispersionEqualToCentralMomentOfSecondOrder() {
        Number[] values = {2.1, 1.2, -3, 0, 4.9};
        Double[] probabilities = {0.2, 0.2, 0.2, 0.2, 0.2};

        Double dispersion = MathStatistics.calculateDispersion(values, probabilities);
        Double expectedValue = MathStatistics.calculateExpectedValue(values, probabilities);
        Double moment = MathStatistics.calculateCentralMoment(
                values, probabilities, MathStatistics.DISPERSION_ORDER, expectedValue);

        assertEquals(dispersion, moment);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenCalculateInitialMomentForValuesWithEmptyProbabilities() {
        Number[] values = {0, -3, 0.1, 0, 1.9};
        Double[] probabilities = {};

        MathStatistics.calculateInitialMoment(values, probabilities, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenValuesAndProbabilitiesSizesAreNotEqual() {
        Number[] values = {7.3, -5.1, 9.3, 5.5};
        Double[] probability = {0.1, 0.2, 0.7};

        MathStatistics.calculateDispersion(values, probability);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenSumOfProbabilitiesIsLessThanOne() {
        Number[] values = {1, 3, 7, 4, 2};
        Double[] probabilities = {0.2, 0.2, 0.13, 0.2, 0.2};

        MathStatistics.calculateExpectedValue(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenProbabilitiesContainSomeNegativeElement() {
        Number[] values = {7.3, 5.1, 9.3};
        Double[] probabilities = {0.1, -0.2, 0.7};

        MathStatistics.calculateDispersion(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenSumOfProbabilitiesIsMoreThanOne() {
        Number[] values = {7.3, 5.1, 9.3};
        Double[] probabilities = {1.2, 0.5, 0.7};

        MathStatistics.calculateDispersion(values, probabilities);
    }
}
