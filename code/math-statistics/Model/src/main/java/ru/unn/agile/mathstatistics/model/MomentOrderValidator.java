package ru.unn.agile.mathstatistics.model;

public final class MomentOrderValidator {
    public static final int MIN_MOMENT_ORDER = 1;

    public static void validate(final int order) {
        if (order < MIN_MOMENT_ORDER) {
            String errorMessage = "Moment order should be greater than " + MIN_MOMENT_ORDER + "!";
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private MomentOrderValidator() {
    }
}
