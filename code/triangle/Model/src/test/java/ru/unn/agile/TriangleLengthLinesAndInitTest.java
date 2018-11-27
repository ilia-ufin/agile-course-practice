package ru.unn.agile;

import org.junit.Test;
import ru.unn.agile.primitives.Point;

import static org.junit.Assert.*;

public class TriangleLengthLinesAndInitTest {
    private final double delta = 0.001;

    @Test
    public void getAFromSimpleTriangle() {
        Point pointA = new Point(0.0, 0.0);
        Point pointB = new Point(1.0, 0.0);
        Point pointC = new Point(0.0, 1.0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(1.0, triangle.getLengthA(), delta);
    }

    @Test
    public void getAFromRectangularTriangle() {
        Point pointA = new Point(0.0, 0.0);
        Point pointB = new Point(3.0, 4.0);
        Point pointC = new Point(0.0, 1.0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(5.0, triangle.getLengthA(), delta);
    }

    @Test
    public void getAFromTriangleWithNegativePoint() {
        Point pointA = new Point(-1.0, 0.0);
        Point pointB = new Point(2.0, 4.0);
        Point pointC = new Point(0.0, 1.0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(5.0, triangle.getLengthA(), delta);
    }

    @Test
    public void getAFromIsoscelesTriangle() {
        Point pointA = new Point(0.0, 0.0);
        Point pointB = new Point(3.0, 4.0);
        Point pointC = new Point(6.0, 0.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(5.0, triangle.getLengthA(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidTriangle() {
        Point pointA = new Point(0.0, 0.0);
        Point pointB = new Point(0.0, 0.0);
        Point pointC = new Point(6.0, 0.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(5.0, triangle.getLengthA(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidTriangleThreePointsOnOneLine() {
        Point pointA = new Point(0.0, 0.0);
        Point pointB = new Point(1.0, 1.0);
        Point pointC = new Point(2.0, 2.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(5.0, triangle.getLengthA(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidTriangleThreePointsOnOneLineWithZero() {
        Point pointA = new Point(0.0, 0.0);
        Point pointB = new Point(1.0, 0.0);
        Point pointC = new Point(2.0, 0.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(5.0, triangle.getLengthA(), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidTriangleWithNullPoint() {
        Point pointA = null;
        Point pointB = new Point(1.0, 0.0);
        Point pointC = new Point(2.0, 0.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(5.0, triangle.getLengthA(), delta);
    }

    @Test
    public void getBFromRectangularTriangle() {
        Point pointA = new Point(0.0, 0.0);
        Point pointB = new Point(3.0, 0.0);
        Point pointC = new Point(0.0, 4.0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(5.0, triangle.getLengthB(), delta);
    }

    @Test
    public void getCFromRectangularTriangle() {
        Point pointA = new Point(0.0, 0.0);
        Point pointB = new Point(3.0, 0.0);
        Point pointC = new Point(3.0, 4.0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(5.0, triangle.getLengthC(), delta);
    }



}
