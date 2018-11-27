package ru.unn.agile.vector3d.model;

import org.junit.Test;
import ru.unn.agile.vector3d.model.errorhandling.NullVectorNormalizing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Vector3DTest {
    public static final double EPSILON = Vector3D.EPSILON;

    @Test
    public void canGetXPositiveDouble() {
        Vector3D v = new Vector3D(1.0, 2, 3);

        assertEquals(1.0, v.getX(), Vector3D.EPSILON);
    }

    @Test
    public void canGetXNegativeDouble() {
        Vector3D v = new Vector3D(-1.3, 0, 0);

        assertEquals(-1.3, v.getX(), Vector3D.EPSILON);
    }

    @Test
    public void canGetYPositiveDouble() {
        Vector3D v = new Vector3D(0, 4.5, 1);

        assertEquals(4.5, v.getY(), Vector3D.EPSILON);
    }

    @Test
    public void canGetYNegativeDouble() {
        Vector3D v = new Vector3D(1, -10, 0);

        assertEquals(-10.0, v.getY(), Vector3D.EPSILON);
    }

    @Test
    public void canGetZPositiveDouble() {
        Vector3D v = new Vector3D(0, 0, 100.3);

        assertEquals(100.3, v.getZ(), Vector3D.EPSILON);
    }

    @Test
    public void canGetZNegativeDouble() {
        Vector3D v = new Vector3D(2, 2, -1337.228);

        assertEquals(-1337.228, v.getZ(), Vector3D.EPSILON);
    }

    @Test
    public void canSetXPositiveDouble() {
        Vector3D v = new Vector3D(0, 0, 0);

        v.setX(0.5);

        assertEquals(0.5, v.getX(), Vector3D.EPSILON);
    }

    @Test
    public void canSetXNegativeDouble() {
        Vector3D v = new Vector3D(0, 0, 0);

        v.setX(-3.3);

        assertEquals(-3.3, v.getX(), Vector3D.EPSILON);
    }

    @Test
    public void canSetYPositiveDouble() {
        Vector3D v = new Vector3D(0, 0, 0);

        v.setY(0.144);

        assertEquals(0.144, v.getY(), Vector3D.EPSILON);
    }

    @Test
    public void canSetYNegativeDouble() {
        Vector3D v = new Vector3D(0, 0, 0);

        v.setY(-3.1415);

        assertEquals(-3.1415, v.getY(), Vector3D.EPSILON);
    }

    @Test
    public void canSetZPositiveDouble() {
        Vector3D v = new Vector3D(0, 0, 0);

        v.setZ(43.5);

        assertEquals(43.5, v.getZ(), Vector3D.EPSILON);
    }

    @Test
    public void canSetZNegativeDouble() {
        Vector3D v = new Vector3D(0, 0, 0);

        v.setZ(-25.4);

        assertEquals(-25.4, v.getZ(), Vector3D.EPSILON);
    }

    @Test
    public void canCompareVectors() {
        Vector3D v1 = new Vector3D(1.1, 2.2, 3.3);
        Vector3D v2 = new Vector3D(1.1, 2.2, 3.3);

        assertEquals(v2, v1);
    }

    @Test
    public void canCompareToItself() {
        Vector3D v = new Vector3D(7, 32, 5);

        assertEquals(v, v);
    }

    @Test
    public void cantCompareToNonVector() {
        Vector3D v = new Vector3D(4.3, 45.1, -5.1);

        assertNotEquals(v, "Vector3D");
    }

    @Test
    public void compareVectorsDifferentX() {
        Vector3D v1 = new Vector3D(7.4, -4.5, 1.2);
        Vector3D v2 = new Vector3D(10.2, -4.5, 1.2);

        assertNotEquals(v2, v1);
    }

    @Test
    public void compareVectorsDifferentY() {
        Vector3D v1 = new Vector3D(1, 3, 1);
        Vector3D v2 = new Vector3D(1, -3, 1);

        assertNotEquals(v2, v1);
    }

    @Test
    public void compareVectorsDifferentZ() {
        Vector3D v1 = new Vector3D(0, 0, -1);
        Vector3D v2 = new Vector3D(0, 0, -2);

        assertNotEquals(v2, v1);
    }

    @Test
    public void compareVectorsDifferentXLessEps() {
        Vector3D v1 = new Vector3D(3, 3, 3);
        Vector3D v2 = new Vector3D(3 + Vector3D.EPSILON * 10.0, 3, 3);

        assertNotEquals(v2, v1);
    }

    @Test
    public void compareVectorsDifferentYLessEps() {
        Vector3D v1 = new Vector3D(-1.2, 0.0, 6.7);
        Vector3D v2 = new Vector3D(-1.2, -Vector3D.EPSILON * 2, 6.7);

        assertNotEquals(v2, v1);
    }

    @Test
    public void compareVectorsDifferentZLessEps() {
        Vector3D v1 = new Vector3D(0, 0, 1000000);
        Vector3D v2 = new Vector3D(0, 0, 1000000 - Vector3D.EPSILON * 1.1);

        assertNotEquals(v2, v1);
    }

    @Test
    public void canAddVectorsWithPositiveCoords() {
        Vector3D v1 = new Vector3D(1, 2, 3);
        Vector3D v2 = new Vector3D(3, 2, 1);
        Vector3D expectedRes = new Vector3D(4, 4, 4);

        Vector3D res = v1.add(v2);

        assertEquals(expectedRes, res);
    }

    @Test
    public void canAddVectorsWithNegativeCoords() {
        Vector3D v1 = new Vector3D(-1, -2, -3);
        Vector3D v2 = new Vector3D(3, 2, 1);
        Vector3D expectedRes = new Vector3D(2, 0, -2);

        Vector3D res = v1.add(v2);

        assertEquals(expectedRes, res);
    }

    @Test
    public void canSubtractVectorsWithPositiveCoords() {
        Vector3D v1 = new Vector3D(10, 20, 30);
        Vector3D v2 = new Vector3D(5, 2, 30);
        Vector3D expectedRes = new Vector3D(5, 18, 0);

        Vector3D res = v1.subtract(v2);

        assertEquals(expectedRes, res);
    }

    @Test
    public void canSubtractVectorsWithNegativeCoords() {
        Vector3D v1 = new Vector3D(4, -5, 10);
        Vector3D v2 = new Vector3D(14, 22, -40);
        Vector3D expectedRes = new Vector3D(-10, -27, 50);

        Vector3D res = v1.subtract(v2);

        assertEquals(expectedRes, res);
    }

    @Test
    public void canMultiplyByPositiveScalar() {
        Vector3D v = new Vector3D(4, 5, 6);
        Vector3D expectedRes = new Vector3D(40, 50, 60);

        Vector3D res = v.multiply(10);

        assertEquals(expectedRes, res);
    }

    @Test
    public void canMultiplyByNegativeScalar() {
        Vector3D v = new Vector3D(0, 7, -16);
        Vector3D expectedRes = new Vector3D(0, -7, 16);

        Vector3D res = v.multiply(-1);

        assertEquals(expectedRes, res);
    }

    @Test
    public void canMultiplyByZeroScalar() {
        Vector3D v = new Vector3D(1674.3, 0, -5602.6);
        Vector3D expectedRes = new Vector3D(0, 0, 0);

        Vector3D res = v.multiply(0);

        assertEquals(expectedRes, res);
    }

    @Test
    public void canCalculateMagnitudeWithPositiveCoords() {
        Vector3D v = new Vector3D(2.0, 6.0, 9.0);

        double mgn = v.magnitude();

        assertEquals(11.0, mgn, EPSILON);
    }

    @Test
    public void canCalculateMagnitudeWithNegativeCoords() {
        Vector3D v = new Vector3D(-1.1, 1.2, -2.4);

        double mgn = v.magnitude();

        assertEquals(2.9, mgn, EPSILON);
    }

    @Test
    public void canCalculateMagnitudeOfZeroVector() {
        Vector3D v = new Vector3D(0, 0, 0);

        double mgn = v.magnitude();

        assertEquals(0.0, mgn, EPSILON);
    }

    @Test
    public void canNormalizeVectorWithPositiveCoords() {
        Vector3D v = new Vector3D(7.2, 9.6, 0);
        Vector3D expectedRes = new Vector3D(0.6, 0.8, 0);

        Vector3D res = v.normalize();

        assertEquals(expectedRes, res);
    }

    @Test
    public void canNormalizeVectorWithNegativeCoords() {
        Vector3D v = new Vector3D(-80, 0, 60);
        Vector3D expectedRes = new Vector3D(-0.8, 0, 0.6);

        Vector3D res = v.normalize();

        assertEquals(expectedRes, res);
    }

    @Test(expected = NullVectorNormalizing.class)
    public void normalizeZeroVectorThrowsException() {
        Vector3D v = new Vector3D(0, 0, 0);

        v.normalize();
    }

    @Test
    public void canCalculateDotProduct() {
        Vector3D v1 = new Vector3D(-1.0, 4.0, -2.0);
        Vector3D v2 = new Vector3D(7.0, 3.0, -0.5);

        double dot = v1.dot(v2);

        assertEquals(6.0, dot, EPSILON);
    }

    @Test
    public void dotProductOfOrthogonalVectorsIsZero() {
        Vector3D v1 = new Vector3D(10.0, 0.0, 0.0);
        Vector3D v2 = new Vector3D(0.0, -2.0, 0.0);

        double dot = v1.dot(v2);

        assertEquals(0.0, dot, EPSILON);
    }

    @Test
    public void canCalculateCrossProduct() {
        Vector3D v1 = new Vector3D(1.0, 0.0, 0.0);
        Vector3D v2 = new Vector3D(0.0, 1.0, 0.0);
        Vector3D expectedRes = new Vector3D(0.0, 0.0, 1.0);

        Vector3D res = v1.cross(v2);

        assertEquals(expectedRes, res);
    }

    @Test
    public void crossProductOfParallelVectorsIsNullVector() {
        Vector3D v1 = new Vector3D(2.0, 5.0, 7.0);
        Vector3D v2 = new Vector3D(6.0, 15.0, 21.0);
        Vector3D expectedRes = new Vector3D(0.0, 0.0, 0.0);

        Vector3D res = v1.cross(v2);

        assertEquals(expectedRes, res);
    }

    @Test
    public void twoCrossProductsAreOppositeVectors() {
        Vector3D v1 = new Vector3D(-7.0, -17.0, 1.0);
        Vector3D v2 = new Vector3D(-2.0, 8.0, 9.0);

        Vector3D cross12 = v1.cross(v2);
        Vector3D cross21 = v2.cross(v1);

        assertEquals(cross21.multiply(-1.0), cross12);
    }

    @Test
    public void crossProductIsOrthogonalToVectors() {
        Vector3D v1 = new Vector3D(22.0, -9.0, -16.0);
        Vector3D v2 = new Vector3D(26.0, 2.0, -3.0);

        Vector3D cross = v1.cross(v2);

        assertEquals(0.0, cross.dot(v1), EPSILON);
        assertEquals(0.0, cross.dot(v2), EPSILON);
    }

    @Test
    public void sameVectorsHasTheSameHash() {
        Vector3D v1 = new Vector3D(5.345, -12.755, 3.1415);
        Vector3D v2 = new Vector3D(5.345, -12.755, 3.1415);

        int v1Hash = v1.hashCode();
        int v2Hash = v2.hashCode();

        assertEquals(v2Hash, v1Hash);
    }

    @Test
    public void canConvertVectorToString() {
        Vector3D v = new Vector3D(5.01, -12, 3.1415);

        String vStringValue = v.toString();

        assertEquals("(5.01, -12.0, 3.1415)", vStringValue);
    }
}
