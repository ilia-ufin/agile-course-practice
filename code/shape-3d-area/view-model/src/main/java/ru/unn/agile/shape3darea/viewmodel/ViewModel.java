package ru.unn.agile.shape3darea.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import ru.unn.agile.shape3darea.model.Shape;
import ru.unn.agile.shape3darea.model.ShapeType;
import ru.unn.agile.shape3darea.model.Sphere;
import ru.unn.agile.shape3darea.model.SquarePyramid;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableList;

public final class ViewModel {
    private static final String SQUARE_SIDE = "squareSide";
    private static final String TRIANGLE_SIDE = "triangleSide";
    private static final String RADIUS = "radius";

    private final ShapeCreator shapeCreator = new ShapeCreator();

    private final ObservableList<ShapeType> shapes = observableList(asList(ShapeType.values()));

    private final ObjectProperty<ShapeType> selectedShape = new SimpleObjectProperty<>();
    private final ObservableList<ShapeParameter> parameters = observableArrayList();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    public ViewModel() {
        selectedShape.addListener((observable, oldValue, newValue) -> updateParameters(newValue));

        selectedShape.set(ShapeType.SQUARE_PYRAMID);
        result.set("");
        status.set(Status.OK.toString());
    }

    public ObservableList<ShapeType> getShapes() {
        return shapes;
    }

    public ObjectProperty<ShapeType> selectedShapeProperty() {
        return selectedShape;
    }

    public ObservableList<ShapeParameter> getParameters() {
        return parameters;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void calculate() {
        try {
            final Class<? extends Shape> shapeClass;
            switch (selectedShape.get()) {
                case SQUARE_PYRAMID:
                    shapeClass = SquarePyramid.class;
                    break;
                case SPHERE:
                    shapeClass = Sphere.class;
                    break;
                default:
                    throw new IllegalStateException("Invalid shape type: " + selectedShape.get());
            }
            Shape shape = shapeCreator.create(shapeClass, parameters);
            result.set(String.valueOf(shape.getArea()));
            status.set(Status.OK.toString());
        } catch (Exception e) {
            result.set("");
            status.set(Status.INVALID_INPUT.toString());
        }
    }

    private void updateParameters(final ShapeType shapeType) {
        switch (shapeType) {
            case SQUARE_PYRAMID:
                parameters.setAll(asList(
                        new ShapeParameter(double.class, SQUARE_SIDE),
                        new ShapeParameter(double.class, TRIANGLE_SIDE)
                ));
                break;
            case SPHERE:
                parameters.setAll(singletonList(new ShapeParameter(double.class, RADIUS)));
                break;
            default:
                throw new IllegalStateException("Invalid shape type: " + selectedShape.get());
        }
    }
}
