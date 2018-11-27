package ru.unn.agile.primitives;

public class Point {

    private double x;
    private double y;

    public Point(final double x, final double y) {
         this.x = x;
         this.y = y;
    }

    private Point() {
    }

    public double getX() {
        return x;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public Point subtract(final Point point) {
        Point result = new Point();

        result.setX(this.getX() - point.getX());
        result.setY(this.getY() - point.getY());

        return result;
    }

    @Override
    public String toString() {
        return "Point{"
                + "x=" + x
                + ", y=" + y
                + '}';
    }

    public Point(final String x, final String y) {
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
    }
}
