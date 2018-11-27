package ru.unn.agile;

import org.junit.Test;
import ru.unn.agile.primitives.Point;

import static org.junit.Assert.assertEquals;

public class TriangleAnglesTest {
    private final double delta = 0.001;

    @Test
    public void getAngleBetweenABFromRectangularTriangleTwoCathetus() {
        Point pointA = new Point(0.0, 0.0);
        Point pointB = new Point(4.0, 0.0);
        Point pointC = new Point(4.0, 3.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);
        double expectedAngleDegreesRadians = 1.57;

        assertEquals(expectedAngleDegreesRadians, triangle.getAngleAB(), delta);
    }

    @Test
    public void getAngleBetweenABFromRectangularTriangle() {
        Point pointA = new Point(0.0, 4.0);
        Point pointB = new Point(0.0, 0.0);
        Point pointC = new Point(3.0, 4.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);
        double expectedAngleDegreesRadians = 0.643;

        assertEquals(expectedAngleDegreesRadians, triangle.getAngleAB(), delta);
    }

    @Test
    public void getAngleBetweenABFromIsoscelesTriangleApex() {
        Point pointA = new Point(0.0, 0.0);
        Point pointB = new Point(3.0, 4.0);
        Point pointC = new Point(6.0, 0.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);
        double expectedAngleDegreesRadians = 1.287;

        assertEquals(expectedAngleDegreesRadians, triangle.getAngleAB(), delta);
    }

    @Test
    public void getAngleBetweenABFromIsoscelesTriangleFooting() {
        Point pointA = new Point(3.0, 4.0);
        Point pointB = new Point(0.0, 0.0);
        Point pointC = new Point(6.0, 0.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);
        double expectedAngleDegreesRadians = 0.927;

        assertEquals(expectedAngleDegreesRadians, triangle.getAngleAB(), delta);
    }

    @Test
    public void getAngleBetweenABFromTriangle() {
        Point pointA = new Point(3.0, 4.0);
        Point pointB = new Point(1.0, -1.0);
        Point pointC = new Point(-4.0, -3.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);
        double expectedAngleDegreesRadians = 2.331;

        assertEquals(expectedAngleDegreesRadians, triangle.getAngleAB(), delta);
    }

    @Test
    public void getAngleBetweenBCFromTriangle() {
        Point pointA = new Point(-4.0, -3.0);
        Point pointB = new Point(3.0, 4.0);
        Point pointC = new Point(1.0, -1.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);
        double expectedAngleDegreesRadians = 2.331;

        assertEquals(expectedAngleDegreesRadians, triangle.getAngleBC(), delta);
    }

    @Test
    public void getAngleBetweenCAFromTriangle() {
        Point pointA = new Point(1.0, -1.0);
        Point pointB = new Point(-4.0, -3.0);
        Point pointC = new Point(3.0, 4.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);
        double expectedAngleDegreesRadians = 2.331;

        assertEquals(expectedAngleDegreesRadians, triangle.getAngleCA(), delta);
    }

}
