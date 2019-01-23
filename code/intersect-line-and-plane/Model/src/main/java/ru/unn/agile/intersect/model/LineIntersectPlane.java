package ru.unn.agile.intersect.model;

import ru.unn.agile.intersect.model.objects.*;

public class LineIntersectPlane {
    private boolean intersection = false;
    private Point pointO;

    private void setPointO(final double coordX, final double coordY, final double coordZ) {
        this.pointO = new Point(coordX, coordY, coordZ);
    }

    public Point getPointO() {
        return this.pointO;
    }

    private void setIntersectionTrue() {
        this.intersection = true;
    }

    private void setIntersectionFalse() {
        this.intersection = false;
    }

    public boolean checkIntersection() {
        return this.intersection;
    }

    public LineIntersectPlane(final Plane planeP, final Line lineL) throws ArithmeticException {
        Point normalN = planeP.countNormalToPlane();
        double distance = planeP.countToNormalDistance(lineL);
        Point vectorW = lineL.getY().createVector(lineL.getX());
        double epsilonE = normalN.scalarProduct(vectorW);

        if (epsilonE != 0) {
            double coordinateX = lineL.getX().getX() + vectorW.getX() * distance / epsilonE;
            double coordinateY = lineL.getX().getY() + vectorW.getY() * distance / epsilonE;
            double coordinateZ = lineL.getX().getZ() + vectorW.getZ() * distance / epsilonE;

            this.setPointO(coordinateX, coordinateY, coordinateZ);

            this.setIntersectionTrue();
        } else if (distance == 0) {
            double coordinateX = planeP.getA().getX();
            double coordinateY = planeP.getA().getY();
            double coordinateZ = planeP.getA().getZ();

            this.setPointO(coordinateX, coordinateY, coordinateZ);

            this.setIntersectionTrue();
        } else {
            this.setIntersectionFalse();
        }
    }
}
