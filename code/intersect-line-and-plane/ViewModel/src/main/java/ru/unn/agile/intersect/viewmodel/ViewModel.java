package ru.unn.agile.intersect.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ru.unn.agile.intersect.model.LineIntersectPlane;
import ru.unn.agile.intersect.model.objects.*;

public class ViewModel {
    private static final String WAITING = "Waiting for input";
    private static final String OK = "Correct input";
    private static final String ERROR = "Input error";

    private static final String LINE_PREFIX = "Line ";
    private static final String PLANE_PREFIX = "Plane ";

    private static final String INTERSECT = "Intersect";
    private static final String NOT_INTERSECT = "Do not intersect";

    private StringProperty coordXFirstPlanePoint = new SimpleStringProperty();
    private StringProperty coordYFirstPlanePoint = new SimpleStringProperty();
    private StringProperty coordZFirstPlanePoint = new SimpleStringProperty();

    private StringProperty coordXSecondPlanePoint = new SimpleStringProperty();
    private StringProperty coordYSecondPlanePoint = new SimpleStringProperty();
    private StringProperty coordZSecondPlanePoint = new SimpleStringProperty();

    private StringProperty coordXThirdPlanePoint = new SimpleStringProperty();
    private StringProperty coordYThirdPlanePoint = new SimpleStringProperty();
    private StringProperty coordZThirdPlanePoint = new SimpleStringProperty();

    private StringProperty coordXFirstLinePoint = new SimpleStringProperty();
    private StringProperty coordYFirstLinePoint = new SimpleStringProperty();
    private StringProperty coordZFirstLinePoint = new SimpleStringProperty();

    private StringProperty coordXSecondLinePoint = new SimpleStringProperty();
    private StringProperty coordYSecondLinePoint = new SimpleStringProperty();
    private StringProperty coordZSecondLinePoint = new SimpleStringProperty();

    private StringProperty planeStatus = new SimpleStringProperty();
    private StringProperty lineStatus = new SimpleStringProperty();
    private StringProperty result = new SimpleStringProperty();

    private ILogger logger;

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    public ViewModel() {
        initCoordinatesAndResult();
        initStatuses();
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);

        initCoordinatesAndResult();
        initStatuses();
    }

    private void initCoordinatesAndResult() {
        coordXFirstPlanePoint.set("");
        coordYFirstPlanePoint.set("");
        coordZFirstPlanePoint.set("");

        coordXSecondPlanePoint.set("");
        coordYSecondPlanePoint.set("");
        coordZSecondPlanePoint.set("");

        coordXThirdPlanePoint.set("");
        coordYThirdPlanePoint.set("");
        coordZThirdPlanePoint.set("");

        coordXFirstLinePoint.set("");
        coordYFirstLinePoint.set("");
        coordZFirstLinePoint.set("");

        coordXSecondLinePoint.set("");
        coordYSecondLinePoint.set("");
        coordZSecondLinePoint.set("");

        result.set("");
    }

    private void initStatuses() {
        planeStatus.set(WAITING);
        lineStatus.set(WAITING);
    }

    public void setCoordXFirstPlanePoint(final String coordXFirstPlanePoint) {
        this.coordXFirstPlanePoint.set(coordXFirstPlanePoint);
    }

    public void setCoordYFirstPlanePoint(final String coordYFirstPlanePoint) {
        this.coordYFirstPlanePoint.set(coordYFirstPlanePoint);
    }

    public void setCoordZFirstPlanePoint(final String coordZFirstPlanePoint) {
        this.coordZFirstPlanePoint.set(coordZFirstPlanePoint);
    }

    public void setCoordXSecondPlanePoint(final String coordXSecondPlanePoint) {
        this.coordXSecondPlanePoint.set(coordXSecondPlanePoint);
    }

    public void setCoordYSecondPlanePoint(final String coordYSecondPlanePoint) {
        this.coordYSecondPlanePoint.set(coordYSecondPlanePoint);
    }

    public void setCoordZSecondPlanePoint(final String coordZSecondPlanePoint) {
        this.coordZSecondPlanePoint.set(coordZSecondPlanePoint);
    }

    public void setCoordXThirdPlanePoint(final String coordXThirdPlanePoint) {
        this.coordXThirdPlanePoint.set(coordXThirdPlanePoint);
    }

    public void setCoordYThirdPlanePoint(final String coordYThirdPlanePoint) {
        this.coordYThirdPlanePoint.set(coordYThirdPlanePoint);
    }

    public void setCoordZThirdPlanePoint(final String coordZThirdPlanePoint) {
        this.coordZThirdPlanePoint.set(coordZThirdPlanePoint);
    }

    public void setCoordXFirstLinePoint(final String coordXFirstLinePoint) {
        this.coordXFirstLinePoint.set(coordXFirstLinePoint);
    }

    public void setCoordYFirstLinePoint(final String coordYFirstLinePoint) {
        this.coordYFirstLinePoint.set(coordYFirstLinePoint);
    }

    public void setCoordZFirstLinePoint(final String coordZFirstLinePoint) {
        this.coordZFirstLinePoint.set(coordZFirstLinePoint);
    }

    public void setCoordXSecondLinePoint(final String coordXSecondLinePoint) {
        this.coordXSecondLinePoint.set(coordXSecondLinePoint);
    }

    public void setCoordYSecondLinePoint(final String coordYSecondLinePoint) {
        this.coordYSecondLinePoint.set(coordYSecondLinePoint);
    }

    public void setCoordZSecondLinePoint(final String coordZSecondLinePoint) {
        this.coordZSecondLinePoint.set(coordZSecondLinePoint);
    }

    public String getCoordXFirstPlanePoint() {
        return coordXFirstPlanePoint.get();
    }

    public String getCoordYFirstPlanePoint() {
        return coordYFirstPlanePoint.get();
    }

    public String getCoordZFirstPlanePoint() {
        return coordZFirstPlanePoint.get();
    }

    public String getCoordXSecondPlanePoint() {
        return coordXSecondPlanePoint.get();
    }

    public String getCoordYSecondPlanePoint() {
        return coordYSecondPlanePoint.get();
    }

    public String getCoordZSecondPlanePoint() {
        return coordZSecondPlanePoint.get();
    }

    public String getCoordXThirdPlanePoint() {
        return coordXThirdPlanePoint.get();
    }

    public String getCoordYThirdPlanePoint() {
        return coordYThirdPlanePoint.get();
    }

    public String getCoordZThirdPlanePoint() {
        return coordZThirdPlanePoint.get();
    }

    public String getCoordXFirstLinePoint() {
        return coordXFirstLinePoint.get();
    }

    public String getCoordYFirstLinePoint() {
        return coordYFirstLinePoint.get();
    }

    public String getCoordZFirstLinePoint() {
        return coordZFirstLinePoint.get();
    }

    public String getCoordXSecondLinePoint() {
        return coordXSecondLinePoint.get();
    }

    public String getCoordYSecondLinePoint() {
        return coordYSecondLinePoint.get();
    }

    public String getCoordZSecondLinePoint() {
        return coordZSecondLinePoint.get();
    }

    public String getPlaneStatus() {
        return planeStatus.get();
    }

    public String getLineStatus() {
        return lineStatus.get();
    }

    public String getResult() {
        return result.get();
    }

    public StringProperty coordXFirstPlanePointProperty() {
        return coordXFirstPlanePoint;
    }

    public StringProperty coordYFirstPlanePointProperty() {
        return coordYFirstPlanePoint;
    }

    public StringProperty coordZFirstPlanePointProperty() {
        return coordZFirstPlanePoint;
    }

    public StringProperty coordXSecondPlanePointProperty() {
        return coordXSecondPlanePoint;
    }

    public StringProperty coordYSecondPlanePointProperty() {
        return coordYSecondPlanePoint;
    }

    public StringProperty coordZSecondPlanePointProperty() {
        return coordZSecondPlanePoint;
    }

    public StringProperty coordXThirdPlanePointProperty() {
        return coordXThirdPlanePoint;
    }

    public StringProperty coordYThirdPlanePointProperty() {
        return coordYThirdPlanePoint;
    }

    public StringProperty coordZThirdPlanePointProperty() {
        return coordZThirdPlanePoint;
    }

    public StringProperty coordXFirstLinePointProperty() {
        return coordXFirstLinePoint;
    }

    public StringProperty coordYFirstLinePointProperty() {
        return coordYFirstLinePoint;
    }

    public StringProperty coordZFirstLinePointProperty() {
        return coordZFirstLinePoint;
    }

    public StringProperty coordXSecondLinePointProperty() {
        return coordXSecondLinePoint;
    }

    public StringProperty coordYSecondLinePointProperty() {
        return coordYSecondLinePoint;
    }

    public StringProperty coordZSecondLinePointProperty() {
        return coordZSecondLinePoint;
    }

    public StringProperty planeStatusProperty() {
        return planeStatus;
    }

    public StringProperty lineStatusProperty() {
        return lineStatus;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public boolean checkStatusValidity() {
        return (getLineStatus().equals(OK) && getPlaneStatus().equals(OK));
    }

    public List<Point> formPlaneCoordinateList() {
        List<Point> planeCoordinates = new ArrayList<Point>();
        double x, y, z;

        x = Double.valueOf(getCoordXFirstPlanePoint());
        y = Double.valueOf(getCoordYFirstPlanePoint());
        z = Double.valueOf(getCoordZFirstPlanePoint());
        planeCoordinates.add(new Point(x, y, z));

        x = Double.valueOf(getCoordXSecondPlanePoint());
        y = Double.valueOf(getCoordYSecondPlanePoint());
        z = Double.valueOf(getCoordZSecondPlanePoint());
        planeCoordinates.add(new Point(x, y, z));

        x = Double.valueOf(getCoordXThirdPlanePoint());
        y = Double.valueOf(getCoordYThirdPlanePoint());
        z = Double.valueOf(getCoordZThirdPlanePoint());
        planeCoordinates.add(new Point(x, y, z));

        return planeCoordinates;
    }

    public List<Point> formLineCoordinateList() {
        List<Point> lineCoordinates = new ArrayList<Point>();
        double x, y, z;

        x = Double.valueOf(getCoordXFirstLinePoint());
        y = Double.valueOf(getCoordYFirstLinePoint());
        z = Double.valueOf(getCoordZFirstLinePoint());
        lineCoordinates.add(new Point(x, y, z));

        x = Double.valueOf(getCoordXSecondLinePoint());
        y = Double.valueOf(getCoordYSecondLinePoint());
        z = Double.valueOf(getCoordZSecondLinePoint());
        lineCoordinates.add(new Point(x, y, z));

        return lineCoordinates;
    }

    public Plane createPlane() {
        try {
            List<Point> coordinates = formPlaneCoordinateList();
            Plane plane = new Plane(coordinates.get(0), coordinates.get(1), coordinates.get(2));
            planeStatus.set(OK);

            logger.writeLog(PLANE_PREFIX + planeStatus.get());

            return plane;
        } catch (NumberFormatException | ArithmeticException ex) {
            planeStatus.set(ERROR + ": " + ex.getMessage().toLowerCase(Locale.ENGLISH));
            result.set(ERROR);

            logger.writeLog(PLANE_PREFIX + planeStatus.get());

            return null;
        }
    }

    public Line createLine() {
        try {
            List<Point> coordinates = formLineCoordinateList();
            Line line = new Line(coordinates.get(0), coordinates.get(1));
            lineStatus.set(OK);

            logger.writeLog(LINE_PREFIX + lineStatus.get());

            return line;
        } catch (NumberFormatException | ArithmeticException ex) {
            lineStatus.set(ERROR + ": " + ex.getMessage().toLowerCase(Locale.ENGLISH));
            result.set(ERROR);

            logger.writeLog(LINE_PREFIX + lineStatus.get());

            return null;
        }
    }

    public void checkLineAndPlaneIntersection() {
        Plane plane = createPlane();
        Line line = createLine();
        if (checkStatusValidity()) {
            LineIntersectPlane intersection = new LineIntersectPlane(plane, line);
            if (intersection.checkIntersection()) {
                result.set(INTERSECT + ": " + intersection.getPointO());
            } else {
                result.set(NOT_INTERSECT);
            }

            logger.writeLog(result.get());
        }
    }

    public final List<String> getLog() {
        return logger.readLog();
    }
}
