package ru.unn.agile.segment2d.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import ru.unn.agile.segment2d.model.Segment2D;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private static final String SEGMENT_ERROR_STATUS = "Segment is incorrect!";
    private static final String SEGMENT_CORRECT_STATUS = "Segment is correct!";

    private static final String RESULT_ERROR_STATUS = "Can't resolve intersection! Check segments.";
    private static final String SEGMENT_INTERSECT_STATUS = "Segments intersect in point (%s, %s)";
    private static final String SEGMENT_NOT_INTERSECT_STATUS = "Segments don't intersect!";

    private StringProperty firstSegmentFirstPointCoordX = new SimpleStringProperty();
    private StringProperty firstSegmentFirstPointCoordY = new SimpleStringProperty();
    private StringProperty firstSegmentSecondPointCoordX = new SimpleStringProperty();
    private StringProperty firstSegmentSecondPointCoordY = new SimpleStringProperty();

    private StringProperty secondSegmentFirstPointCoordX = new SimpleStringProperty();
    private StringProperty secondSegmentFirstPointCoordY = new SimpleStringProperty();
    private StringProperty secondSegmentSecondPointCoordX = new SimpleStringProperty();
    private StringProperty secondSegmentSecondPointCoordY = new SimpleStringProperty();

    private StringProperty firstSegmentStatus = new SimpleStringProperty();
    private StringProperty secondSegmentStatus = new SimpleStringProperty();

    private StringProperty result = new SimpleStringProperty();

    public StringProperty firstSegmentFirstPointCoordXProperty() {
        return firstSegmentFirstPointCoordX;
    }
    public String getFirstSegmentFirstPointCoordX() {
        return firstSegmentFirstPointCoordX.get();
    }
    public void setFirstSegmentFirstPointCoordX(final String coordX) {
        firstSegmentFirstPointCoordX.set(coordX);
    }

    public StringProperty firstSegmentFirstPointCoordYProperty() {
        return firstSegmentFirstPointCoordY;
    }
    public String getFirstSegmentFirstPointCoordY() {
        return firstSegmentFirstPointCoordY.get();
    }
    public void setFirstSegmentFirstPointCoordY(final String coordY) {
        firstSegmentFirstPointCoordY.set(coordY);
    }

    public StringProperty firstSegmentSecondPointCoordXProperty() {
        return firstSegmentSecondPointCoordX;
    }
    public String getFirstSegmentSecondPointCoordX() {
        return firstSegmentSecondPointCoordX.get();
    }
    public void setFirstSegmentSecondPointCoordX(final String coordX) {
        firstSegmentSecondPointCoordX.set(coordX);
    }

    public StringProperty firstSegmentSecondPointCoordYProperty() {
        return firstSegmentSecondPointCoordY;
    }
    public String getFirstSegmentSecondPointCoordY() {
        return firstSegmentSecondPointCoordY.get();
    }
    public void setFirstSegmentSecondPointCoordY(final String coordY) {
        firstSegmentSecondPointCoordY.set(coordY);
    }

    public StringProperty secondSegmentFirstPointCoordXProperty() {
        return secondSegmentFirstPointCoordX;
    }
    public String getSecondSegmentFirstPointCoordX() {
        return secondSegmentFirstPointCoordX.get();
    }
    public void setSecondSegmentFirstPointCoordX(final String coordX) {
        secondSegmentFirstPointCoordX.set(coordX);
    }

    public StringProperty secondSegmentFirstPointCoordYProperty() {
        return secondSegmentFirstPointCoordY;
    }
    public String getSecondSegmentFirstPointCoordY() {
        return secondSegmentFirstPointCoordY.get();
    }
    public void setSecondSegmentFirstPointCoordY(final String coordY) {
        secondSegmentFirstPointCoordY.set(coordY);
    }

    public StringProperty secondSegmentSecondPointCoordXProperty() {
        return secondSegmentSecondPointCoordX;
    }
    public String getSecondSegmentSecondPointCoordX() {
        return secondSegmentSecondPointCoordX.get();
    }
    public void setSecondSegmentSecondPointCoordX(final String coordX) {
        secondSegmentSecondPointCoordX.set(coordX);
    }

    public StringProperty secondSegmentSecondPointCoordYProperty() {
        return secondSegmentSecondPointCoordY;
    }
    public String getSecondSegmentSecondPointCoordY() {
        return secondSegmentSecondPointCoordY.get();
    }
    public void setSecondSegmentSecondPointCoordY(final String coordY) {
        secondSegmentSecondPointCoordY.set(coordY);
    }

    public StringProperty firstSegmentStatusProperty() {
        return firstSegmentStatus;
    }
    public String getFirstSegmentStatus() {
        return firstSegmentStatus.get();
    }

    public StringProperty secondSegmentStatusProperty() {
        return secondSegmentStatus;
    }
    public String getSecondSegmentStatus() {
        return secondSegmentStatus.get();
    }

    public StringProperty resultProperty() {
        return result;
    }
    public String getResult() {
        return result.get();
    }

    public ViewModel() {
        init();
    }

    public void checkIntersection() {
        checkIsSegmentsValid();
        if (!firstSegmentStatus.get().equals(SEGMENT_CORRECT_STATUS)
                || !secondSegmentStatus.get().equals(SEGMENT_CORRECT_STATUS)) {
            result.set(RESULT_ERROR_STATUS);
            return;
        }

        final List<String> firstSegmentCoords = createFirstSegmentCoordsList();
        Segment2D firstSegment = createSegment(firstSegmentCoords);

        final List<String> secondSegmentCoords = createSecondSegmentCoordsList();
        Segment2D secondSegment = createSegment(secondSegmentCoords);

        Point2D inttersectionPoint = firstSegment.intersection(secondSegment);
        if (inttersectionPoint != null) {
            String coordX = Double.toString(inttersectionPoint.getX());
            String coordY = Double.toString(inttersectionPoint.getY());
            result.set(String.format(SEGMENT_INTERSECT_STATUS, coordX, coordY));
        } else {
            result.set(SEGMENT_NOT_INTERSECT_STATUS);
        }
    }

    private void init() {
        firstSegmentFirstPointCoordX.set("");
        firstSegmentFirstPointCoordY.set("");
        firstSegmentSecondPointCoordX.set("");
        firstSegmentSecondPointCoordY.set("");
        secondSegmentFirstPointCoordX.set("");
        secondSegmentFirstPointCoordY.set("");
        secondSegmentSecondPointCoordX.set("");
        secondSegmentSecondPointCoordY.set("");
        firstSegmentStatus.set("");
        secondSegmentStatus.set("");
        result.set("");
    }

    private List<String> createFirstSegmentCoordsList() {
        return new ArrayList<String>() { {
            add(firstSegmentFirstPointCoordX.get());
            add(firstSegmentFirstPointCoordY.get());
            add(firstSegmentSecondPointCoordX.get());
            add(firstSegmentSecondPointCoordY.get());
        } };
    }

    private List<String> createSecondSegmentCoordsList() {
        return new ArrayList<String>() { {
            add(secondSegmentFirstPointCoordX.get());
            add(secondSegmentFirstPointCoordY.get());
            add(secondSegmentSecondPointCoordX.get());
            add(secondSegmentSecondPointCoordY.get());
        } };
    }

    private Segment2D createSegment(final List<String> coords) {
        double firstPointCoordX = Double.parseDouble(coords.get(0));
        double firstPointCoordY = Double.parseDouble(coords.get(1));
        double secondPointCoordX = Double.parseDouble(coords.get(2));
        double secondPointCoordY = Double.parseDouble(coords.get(coords.size() - 1));

        return new Segment2D(firstPointCoordX, firstPointCoordY,
                secondPointCoordX, secondPointCoordY);
    }

    private void checkIsSegmentValid(final List<String> coords,
                                     final StringProperty status) {
        try {
            createSegment(coords);
            status.set(SEGMENT_CORRECT_STATUS);
        } catch (NumberFormatException e) {
            status.set(SEGMENT_ERROR_STATUS);
        } catch (ArithmeticException e) {
            status.set(SEGMENT_ERROR_STATUS + " " + e.getMessage());
        }
    }

    private void checkIsFirstSegmentValid() {
        final List<String> firstSegmentCoords = createFirstSegmentCoordsList();
        checkIsSegmentValid(firstSegmentCoords, firstSegmentStatus);
    }

    private void checkIsSecondSegmentValid() {
        final List<String> secondSegmentCoords = createSecondSegmentCoordsList();
        checkIsSegmentValid(secondSegmentCoords, secondSegmentStatus);
    }

    private void checkIsSegmentsValid() {
        checkIsFirstSegmentValid();
        checkIsSecondSegmentValid();
    }
}
