package ru.unn.agile.mathstatistics.model;

import org.junit.Test;

public class MomentOrderValidatorTests {

    @Test(expected = Test.None.class)
    public void canDetectCorrectMomentOrder() {
        final int order = MomentOrderValidator.MIN_MOMENT_ORDER;

        MomentOrderValidator.validate(order);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canDetectIncorrectMomentOrder() {
        final int order = MomentOrderValidator.MIN_MOMENT_ORDER - 1;

        MomentOrderValidator.validate(order);
    }
}
