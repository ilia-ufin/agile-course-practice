package ru.unn.agile.vectordistance.model;

import java.security.InvalidParameterException;
import org.junit.Test;
import static org.junit.Assert.*;

public class VectorDistanceTest {
    private static final float REASONABLE_PRECISION = 1e-7f;

    @Test
    public void canComputeL1Distance() {
        FloatVector a = new FloatVector(new float[]{1.0f, 2.0f, 3.0f});
        FloatVector b = new FloatVector(new float[]{4.0f, 5.0f, 6.0f});

        float result = VectorDistance.computeL1(a, b);

        assertEquals(9.0f, result, REASONABLE_PRECISION);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotComputeL1DistanceOfDifferentLengthVectors()
            throws InvalidParameterException {
        FloatVector a = new FloatVector(new float[]{1.0f});
        FloatVector b = new FloatVector(new float[]{4.0f, 5.0f});

        VectorDistance.computeL1(a, b);
    }

    @Test(expected = NullPointerException.class)
    public void canNotComputeL1DistanceOfNullVector()
            throws NullPointerException {
        VectorDistance.computeL1(null, null);
    }

    @Test
    public void canComputeL2Distance() {
        FloatVector a = new FloatVector(new float[]{1.0f, 2.0f, 3.0f, 4.0f});
        FloatVector b = new FloatVector(new float[]{4.0f, 5.0f, 6.0f, 7.0f});

        float result = VectorDistance.computeL2(a, b);

        assertEquals(6.0f, result, REASONABLE_PRECISION);
    }

    @Test
    public void canComputeL2DistanceWithNegatives() {
        FloatVector a = new FloatVector(new float[]{-9.0f, 8.0f, -7.0f, 6.0f});
        FloatVector b = new FloatVector(new float[]{1.0f, -2.0f, 3.0f, -4.0f});

        float result = VectorDistance.computeL2(a, b);

        assertEquals(20.0f, result, REASONABLE_PRECISION);
    }

    @Test(expected = NullPointerException.class)
    public void canNotComputeL2DistanceOfNullVector()
            throws NullPointerException {
        VectorDistance.computeL2(null, null);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotComputeL2DistanceOfDifferentLengthVectors()
            throws InvalidParameterException {
        FloatVector a = new FloatVector(new float[]{1.0f});
        FloatVector b = new FloatVector(new float[]{4.0f, 5.0f});

        VectorDistance.computeL2(a, b);
    }

    @Test
    public void canComputeL3Distance() {
        FloatVector a = new FloatVector(
            new float[]{-9.0f, 8.0f, -7.0f, 6.0f, -5.0f, 4.0f, -3.0f, 2.0f});
        FloatVector b = new FloatVector(
            new float[]{1.0f, -2.0f, 3.0f, -4.0f, 5.0f, -6.0f, 7.0f, -8.0f});

        float result = VectorDistance.computeL3(a, b);

        assertEquals(20.0f, result, REASONABLE_PRECISION);
    }

    @Test(expected = NullPointerException.class)
    public void canNotComputeL3DistanceOfNullVector()
            throws NullPointerException {
        VectorDistance.computeL3(null, null);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotComputeL3DistanceOfDifferentLengthVectors()
            throws InvalidParameterException {
        FloatVector a = new FloatVector(new float[]{1.0f});
        FloatVector b = new FloatVector(new float[]{4.0f, 5.0f});

        VectorDistance.computeL3(a, b);
    }

    @Test
    public void canComputeL4Distance() {
        FloatVector a = new FloatVector(new float[]{
            -9.0f, 8.0f, -7.0f, 6.0f, -5.0f, 4.0f, -3.0f, 2.0f,
            -1.0f, 0.0f, -1.0f, 2.0f, -3.0f, 4.0f, -5.0f, 6.0f});
        FloatVector b = new FloatVector(new float[]{
            1.0f, -2.0f, 3.0f, -4.0f, 5.0f, -6.0f, 7.0f, -8.0f,
            9.0f, -10.0f, -11.0f, 12.0f, -13.0f, 14.0f, -15.0f, 16.0f});

        float result = VectorDistance.computeL4(a, b);

        assertEquals(20.0f, result, REASONABLE_PRECISION);
    }

    @Test(expected = NullPointerException.class)
    public void canNotComputeL4DistanceOfNullVector()
            throws NullPointerException {
        VectorDistance.computeL4(null, null);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotComputeL4DistanceOfDifferentLengthVectors()
            throws InvalidParameterException {
        FloatVector a = new FloatVector(new float[]{1.0f});
        FloatVector b = new FloatVector(new float[]{4.0f, 5.0f});

        VectorDistance.computeL4(a, b);
    }

    @Test(expected = FloatingOverflowException.class)
    public void canNotComputeL4DistanceWithWhenOverflow()
            throws FloatingOverflowException {
        FloatVector a = new FloatVector(new float[]{Float.MAX_VALUE});
        FloatVector b = new FloatVector(new float[]{-1.0f});

        VectorDistance.computeL4(a, b);
    }

    @Test
    public void canComputeLinfDistance() {
        FloatVector a = new FloatVector(new float[]{-9.0f, 18.0f, -107.0f});
        FloatVector b = new FloatVector(new float[]{1.0f, -2.0f, 3.0f});

        float result = VectorDistance.computeLinf(a, b);

        assertEquals(110.0f, result, REASONABLE_PRECISION);
    }

    @Test(expected = NullPointerException.class)
    public void canNotComputeLinfDistanceOfNullVector()
            throws NullPointerException {
        VectorDistance.computeLinf(null, null);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotComputeLinfDistanceOfDifferentLengthVectors()
            throws InvalidParameterException {
        FloatVector a = new FloatVector(new float[]{1.0f});
        FloatVector b = new FloatVector(new float[]{4.0f, 5.0f});

        VectorDistance.computeLinf(a, b);
    }

    @Test(expected = FloatingOverflowException.class)
    public void canNotComputeLinfDistanceWithWhenOverflow()
            throws FloatingOverflowException {
        FloatVector a = new FloatVector(new float[]{Float.MAX_VALUE});
        FloatVector b = new FloatVector(new float[]{-Float.MAX_VALUE});

        VectorDistance.computeLinf(a, b);
    }
}
