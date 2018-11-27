package ru.unn.agile.mathstatistics.model;

import org.junit.Test;

public class ArrayValidatorTests {
    @Test(expected = Test.None.class)
    public void canDetectCorrectArray() {
        Double[] array = {0.0, -0.1};

        ArrayValidator.validate(array);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenArrayIsNotInitialized() {
        Double[] array = null;

        ArrayValidator.validate(array);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenElementOfArrayIsNotInitialized() {
        Double[] array = {0.0, null, -0.1};

        ArrayValidator.validate(array);
    }
}
