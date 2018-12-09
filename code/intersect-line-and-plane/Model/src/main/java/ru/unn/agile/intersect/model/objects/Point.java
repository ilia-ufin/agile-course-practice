package ru.unn.agile.intersect.model.objects;

import java.util.Locale;

public final class Point {
    private double coordinateX, coordinateY, coordinateZ;

    public Point(final double x1, final double y1, final double z1) {
        this.coordinateX = x1;
        this.coordinateY = y1;
        this.coordinateZ = z1;
    }

    public double getX() {
        return this.coordinateX;
    }

    public double getY() {
        return this.coordinateY;
    }

    public double getZ() {
        return this.coordinateZ;
    }

    public Point createVector(final Point pointA) {
        double x = this.getX() - pointA.getX();
        double y = this.getY() - pointA.getY();
        double z = this.getZ() - pointA.getZ();
        return new Point(x, y, z);
    }

    public Point vectorProduct(final Point pointA) {
        double x = this.getY() * pointA.getZ() - this.getZ() * pointA.getY();
        double y = this.getZ() * pointA.getX() - this.getX() * pointA.getZ();
        double z = this.getX() * pointA.getY() - this.getY() * pointA.getX();
        return new Point(x, y, z);
    }

    public double scalarProduct(final Point pointA) {
        double scalarProduct = pointA.getX() * this.getX();
        scalarProduct += pointA.getY() * this.getY() + pointA.getZ() * this.getZ();
        return scalarProduct;
    }

    public Point normalizePoint() {
        double norm;
        norm = Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2);
        norm = Math.sqrt(norm);
        return new Point(this.getX() / norm, this.getY() / norm, this.getZ() / norm);
    }

    @Override
    public String toString() {
        String xStr = String.format(Locale.ENGLISH, "%(.2f", coordinateX);
        String yStr = String.format(Locale.ENGLISH, "%(.2f", coordinateY);
        String zStr = String.format(Locale.ENGLISH, "%(.2f", coordinateZ);
        return ("(" + xStr + ", " + yStr + ", " + zStr + ")");
    }
}
