package ru.unn.agile.vector3d.model;

import ru.unn.agile.vector3d.model.errorhandling.NullVectorNormalizing;

import java.util.Locale;
import java.util.Objects;

public class Vector3D {
    public static final double EPSILON = 0.000001;

    private double x;
    private double y;
    private double z;

    public Vector3D(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public void setZ(final double z) {
        this.z = z;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Vector3D) {
            Vector3D otherVector = (Vector3D) obj;

            return Math.abs(x - otherVector.x) < EPSILON
                    && Math.abs(y - otherVector.y) < EPSILON
                    && Math.abs(z - otherVector.z) < EPSILON;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public Vector3D add(final Vector3D other) {
        return new Vector3D(x + other.x, y + other.y, z + other.z);
    }

    public Vector3D subtract(final Vector3D other) {
        return new Vector3D(x - other.x, y - other.y, z - other.z);
    }

    public Vector3D multiply(final double coef) {
        return new Vector3D(x * coef, y * coef, z * coef);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3D normalize() {
        double mgn = magnitude();

        if (Math.abs(mgn) < EPSILON) {
            throw new NullVectorNormalizing();
        }

        return multiply(1.0 / mgn);
    }

    public double dot(final Vector3D other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public Vector3D cross(final Vector3D other) {
        return new Vector3D(y * other.z - z * other.y,
                z * other.x - x * other.z,
                x * other.y - y * other.x);
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "(%s, %s, %s)", x, y, z);
    }
}
