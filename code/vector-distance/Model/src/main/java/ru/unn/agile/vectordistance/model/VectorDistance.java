package ru.unn.agile.vectordistance.model;

import java.security.InvalidParameterException;
import java.util.Objects;

public final class VectorDistance {
    public static final String FLOATING_OVERFLOW_EXCEPTION_MESSAGE =
            "Floating overflow";
    public static final String EXPECTED_VECTORS_OF_SAME_LENGTH_EXCEPTION_MESSAGE =
            "Expected vectors of same length";
    public static final String EXPECTED_NOT_NULL_PARAMETER_EXCEPTION_MESSAGE =
            "Expected not null parameter ";

    public static float computeL1(final FloatVector a, final FloatVector b) {
        final double p = 1.0;
        return computeLPdistance(a, b, p);
    }

    public static float computeL2(final FloatVector a, final FloatVector b) {
        final double p = 2.0;
        return computeLPdistance(a, b, p);
    }

    public static float computeL3(final FloatVector a, final FloatVector b) {
        final double p = 3.0;
        return computeLPdistance(a, b, p);
    }

    public static float computeL4(final FloatVector a, final FloatVector b) {
        final double p = 4.0;
        return computeLPdistance(a, b, p);
    }

    public static float computeLinf(final FloatVector a, final FloatVector b) {
        checkArgs(a, b);

        float distance = 0.0f;
        for (int i = 0; i < a.length(); ++i) {
            float currentDistance = Math.abs(a.get(i) - b.get(i));
            ensureIsFinite(currentDistance);
            if (distance < currentDistance) {
                distance = currentDistance;
            }
        }
        return distance;
    }

    public enum Distance {
        L1("L1") {
            public float apply(final FloatVector a, final FloatVector b) {
                return computeL1(a, b);
            }
        },
        L2("L2") {
            public float apply(final FloatVector a, final FloatVector b) {
                return computeL2(a, b);
            }
        },
        L3("L3") {
            public float apply(final FloatVector a, final FloatVector b) {
                return computeL3(a, b);
            }
        },
        L4("L4") {
            public float apply(final FloatVector a, final FloatVector b) {
                return computeL4(a, b);
            }
        },
        Linf("Linf") {
            public float apply(final FloatVector a, final FloatVector b) {
                return computeLinf(a, b);
            }
        };
        private final String name;
        Distance(final String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return name;
        }

        public abstract float apply(FloatVector a, FloatVector b);
    }

    private VectorDistance() { /* none */ }

    private static float computeLPdistance(
            final FloatVector a, final FloatVector b, final double p) {
        checkArgs(a, b);

        float distance = 0.0f;
        for (int i = 0; i < a.length(); ++i) {
            distance += Math.pow(Math.abs(a.get(i) - b.get(i)), p);
            ensureIsFinite(distance);
        }
        return (float) Math.pow(distance, 1.0 / p);
    }

    private static void ensureIsFinite(final float x) {
        if (!Float.isFinite(x)) {
            throw new FloatingOverflowException(FLOATING_OVERFLOW_EXCEPTION_MESSAGE);
        }
    }

    private static void checkArgs(final FloatVector a, final FloatVector b) {
        Objects.requireNonNull(a, EXPECTED_NOT_NULL_PARAMETER_EXCEPTION_MESSAGE + "a");
        Objects.requireNonNull(b, EXPECTED_NOT_NULL_PARAMETER_EXCEPTION_MESSAGE + "b");
        if (a.length() != b.length()) {
            throw new InvalidParameterException(
                    EXPECTED_VECTORS_OF_SAME_LENGTH_EXCEPTION_MESSAGE);
        }
    }
}
