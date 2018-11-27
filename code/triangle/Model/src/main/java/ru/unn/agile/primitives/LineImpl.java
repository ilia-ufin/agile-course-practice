package ru.unn.agile.primitives;

import static java.lang.Math.*;

public class LineImpl implements Line {
    private Point point1;
    private Point point2;


    public LineImpl(final Point point1, final Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    @Override
    public double getLength() {
        Point point = point2.subtract(point1);
        return sqrt(pow(point.getY(), 2)
                + pow(point.getX(), 2));
    }

    @Override
    public String toString() {
        return "LineImpl{"
                + "point1=" + point1
                + ", point2=" + point2
                + ", length=" + getLength()
                + '}';
    }
}
