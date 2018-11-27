package ru.unn.agile;

import org.junit.Test;
import ru.unn.agile.primitives.Point;

import static org.junit.Assert.assertEquals;

public class TrianglePerimeterAndSquareTest {
    private final double delta = 0.001;

    @Test
    public void getPerimeterFromTriangle() {
        Point pointA = new Point(0.0, 0.0);
        Point point2 = new Point(3.0, 0.0);
        Point pointC = new Point(3.0, 4.0);

        Triangle triangle = new Triangle(pointA, point2, pointC);

        assertEquals(12, triangle.getPerimeter(), delta);
    }

    @Test
    public void getPerimeterFromRectangularTriangle() {
        Point pointA = new Point(0.0, 0.0);
        Point pointB = new Point(5.0, 0.0);
        Point pointC = new Point(5.0, 12.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(30, triangle.getPerimeter(), delta);
    }


    @Test
    public void getSquareFromTriangle() {
        Point pointA = new Point(0.0, 0.0);
        Point pointB = new Point(3.0, 0.0);
        Point pointC = new Point(3.0, 4.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(6, triangle.getSquare(), delta);
    }

    @Test
    public void getSquareFromRectangularTriangle() {
        Point pointA = new Point(0.0, 0.0);
        Point pointB = new Point(8.0, 0.0);
        Point pointC = new Point(8.0, 15.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(60, triangle.getSquare(), delta);
    }

    @Test
    public void getSquareFromRectangularTriangleWithAIsHypotenuse() {
        Point pointA = new Point(4.0, 3.0);
        Point pointB = new Point(0.0, 0.0);
        Point pointC = new Point(4.0, 0.0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(6, triangle.getSquare(), delta);
    }
}
