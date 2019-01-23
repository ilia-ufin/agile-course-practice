package ru.unn.agile.intersect.model.objects;

public final class Line {
    public static final double EPSILON = 0.000001;
    private Point pointX, pointY;

    public Line(final Point pointA1, final Point pointB1) {
        if (calculateDistance(pointA1, pointB1) > EPSILON) {
            this.pointX = pointA1;
            this.pointY = pointB1;
        } else {
            throw new ArithmeticException("Points must not have identical coordinates");
        }
    }

    public Point getX() {
        return this.pointX;
    }

    public Point getY() {
        return this.pointY;
    }

    public double calculateDistance(final Point pointA, final Point pointB) {
        double x = pointA.getX() - pointB.getX();
        double y = pointA.getY() - pointB.getY();
        double z = pointA.getZ() - pointB.getZ();
        double norm = Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
        return Math.sqrt(norm);
    }
}
