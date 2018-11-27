package ru.unn.agile.segment2d.model;

import java.util.Objects;
import java.awt.geom.Point2D;

public class Segment2D {

    public static final double EPSILON = 0.000001;

    private Point2D p1;
    private Point2D p2;

    private double paramA1, paramB1, paramC1;
    private double paramA2, paramB2, paramC2;

    public Segment2D(final Point2D p1, final Point2D p2) {
        if (isValidSegment(p1, p2)) {
            this.p1 = p1;
            this.p2 = p2;
        } else {
            throw new ArithmeticException("both Point2D can not be the same");
        }
    }

    public Segment2D(final double x1, final double y1, final double x2, final double y2) {
        Point2D tp1 = new Point2D.Double(x1, y1);
        Point2D tp2 = new Point2D.Double(x2, y2);
        if (isValidSegment(tp1, tp2)) {
            this.p1 = tp1;
            this.p2 = tp2;
        } else {
            throw new ArithmeticException("both Point2D can not be the same");
        }
    }

    public Point2D getP1() {
        return p1;
    }

    public Point2D getP2() {
        return p2;
    }

    public void setP1(final Point2D p1) {
        this.p1 = p1;
    }

    public void setP2(final Point2D p2) {
        this.p2 = p2;
    }

    @Override
    public String toString() {
        return "Segment2D{"
                + "p1=" + p1.toString()
                + ", p2=" + p2.toString()
                + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Segment2D) {
            Segment2D seg = (Segment2D) obj;
            return (p1.distance(seg.p1) < EPSILON && p2.distance(seg.p2) < EPSILON)
                    || (p1.distance(seg.p2) < EPSILON && p2.distance(seg.p1) < EPSILON);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2);
    }

    public boolean isParallel(final Segment2D targetSegment) {
        initParameters(targetSegment);

        return det(paramA1, paramB1, paramA2, paramB2) == 0;
    }

    public boolean isMatched(final Segment2D targetSegment) {
        initParameters(targetSegment);

        if (det(paramA1, paramB1, paramA2, paramB2) != 0) {
            return false;
        } else {
            Point2D tp1 = targetSegment.getP1();
            Point2D tp2 = targetSegment.getP2();

            return det(paramA1, paramC1, paramA2, paramC2) == 0
                    && det(paramB1, paramC1, paramB2, paramC2) == 0
                    && intersection1D(p1.getX(), p2.getX(), tp1.getX(), tp2.getX())
                    && intersection1D(p1.getY(), p2.getY(), tp1.getY(), tp2.getY());
        }
    }

    public Point2D intersection(final Segment2D targetSegment) {
        initParameters(targetSegment);
        double base = det(paramA1, paramB1, paramA2, paramB2);

        if (base != 0) {
            double x = -det(paramC1, paramB1, paramC2, paramB2) * 1. / base;
            double y = -det(paramA1, paramC1, paramA2, paramC2) * 1. / base;
            Point2D tp1 = targetSegment.getP1();
            Point2D tp2 = targetSegment.getP2();
            if (between(p1.getX(), p2.getX(), x)
                    && between(p1.getY(), p2.getY(), y)
                    && between(tp1.getX(), tp2.getX(), x)
                    && between(tp1.getY(), tp2.getY(), y)) {
                return new Point2D.Double(x, y);
            }
        }
        return null;
    }

    private boolean isValidSegment() {
        return p1.distance(p2) > EPSILON;
    }

    private boolean isValidSegment(final Point2D tp1, final Point2D tp2) {
        return tp1.distance(tp2) > EPSILON;
    }

    private double det(final double a11, final double a12, final double a21, final double a22) {
        return a11 * a22 - a12 * a21;
    }

    private boolean between(final double coordStart,
                            final double coordEnd,
                            final double coordTarget) {
        return Math.min(coordStart, coordEnd) <= coordTarget + EPSILON
                && coordTarget <= Math.max(coordStart, coordEnd) + EPSILON;
    }

    private void initParameters(final Segment2D targetSegment) {
        paramA1 = p1.getY() - p2.getY();
        paramB1 = p2.getX() - p1.getX();
        paramC1 = -paramA1 * p1.getX() - paramB1 * p1.getY();

        paramA2 = targetSegment.p1.getY() - targetSegment.p2.getY();
        paramB2 = targetSegment.p2.getX() - targetSegment.p1.getX();
        paramC2 = -paramA2 * targetSegment.p1.getX() - paramB2 * targetSegment.p1.getY();
    }

    private boolean intersection1D(final double segment1p1coord,
                                   final double segment1p2coord,
                                   final double segment2p1coord,
                                   final double segment2p2coord) {
        double segment1p1coordCurrent = segment1p1coord;
        double segment1p2coordCurrent = segment1p2coord;
        double segment2p1coordCurrent = segment2p1coord;
        double segment2p2coordCurrent = segment2p2coord;

        if (segment1p1coord > segment1p2coord) {
            segment1p1coordCurrent = segment1p2coord;
            segment1p2coordCurrent = segment1p1coord;
        }
        if (segment2p1coord > segment2p2coord) {
            segment2p1coordCurrent = segment2p2coord;
            segment2p2coordCurrent = segment2p1coord;
        }

        return Math.max(segment1p1coordCurrent, segment2p1coordCurrent)
                <= Math.min(segment1p2coordCurrent, segment2p2coordCurrent);
    }

}
