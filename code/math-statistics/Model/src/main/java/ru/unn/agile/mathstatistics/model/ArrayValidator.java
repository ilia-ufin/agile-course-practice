package ru.unn.agile.mathstatistics.model;

public final class ArrayValidator {
    public static <T> void validate(final T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array should be initialized!");
        }

        for (int i = 0; i < array.length; ++i) {
            if (array[i] == null) {
                throw new IllegalArgumentException(
                        "Each array element should be initialized!");
            }
        }
    }

    private ArrayValidator() {
    }
}
