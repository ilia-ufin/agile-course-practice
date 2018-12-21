package ru.unn.agile.shape3darea.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import ru.unn.agile.shape3darea.model.Shape;
import ru.unn.agile.shape3darea.model.ShapeType;
import ru.unn.agile.shape3darea.model.Sphere;
import ru.unn.agile.shape3darea.model.SquarePyramid;

import java.util.List;

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
    private final StringProperty logRepresentation = new SimpleStringProperty();

    private ILogger logger;
    private ListChangeListener<String> listener;

    public void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        if (this.logger != null) {
            this.logger.logProperty().removeListener(listener);
        }
        this.logger = logger;
        this.logger.logProperty().addListener(listener);
        for (ShapeParameter parameter : parameters) {
            parameter.setLogger(this.logger);
        }
    }

    public ViewModel(final ILogger logger) {
        listener = c -> updateLogRepresentation();
        setLogger(logger);

        selectedShape.set(ShapeType.SQUARE_PYRAMID);
        updateParameters();
        selectedShape.addListener((observable, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                updateParameters();
                this.logger.log(LogMessages.SHAPE_WAS_CHANGED + newValue);
            }
        });

        result.set("");
        logRepresentation.set("");
        status.set(Status.OK.toString());
    }

    public ViewModel() {
        this(new DummyLogger());
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

    public String getResult() {
        return result.get();
    }

    public String getStatus() {
        return status.get();
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
        logger.log(LogMessages.CALCULATE_WAS_PRESSED);
    }

    private void updateParameters() {
        final ShapeType shape = selectedShape.get();
        switch (shape) {
            case SQUARE_PYRAMID:
                parameters.setAll(asList(
                        new ShapeParameter(double.class, SQUARE_SIDE, logger),
                        new ShapeParameter(double.class, TRIANGLE_SIDE, logger)
                ));
                break;
            case SPHERE:
                parameters.setAll(singletonList(new ShapeParameter(double.class, RADIUS, logger)));
                break;
            default:
                throw new IllegalStateException("Invalid shape type: " + shape);
        }
    }

    public List<String> getLog() {
        return logger.getLog();
    }
    public StringProperty logRepresentationProperty() {
        return logRepresentation;
    }
    public String getLogRepresentation() {
        return logRepresentation.get();
    }

    private void updateLogRepresentation() {
        StringBuilder result = new StringBuilder();
        for (String logRecord : logger.getLog()) {
            result.append(logRecord).append("\n");
        }
        logRepresentation.set(result.toString());
    }
}

final class LogMessages {
    static final String CALCULATE_WAS_PRESSED = "Calculate was pressed.";
    static final String SHAPE_WAS_CHANGED = "Shape was changed to ";
    static final String PARAMETER_WAS_CHANGED = "Parameter %s was changed. from %s to %s";

    private LogMessages() { }
}
