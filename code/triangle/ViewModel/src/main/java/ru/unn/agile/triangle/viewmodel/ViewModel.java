package ru.unn.agile.triangle.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import ru.unn.agile.Triangle;
import ru.unn.agile.primitives.Point;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private StringProperty aX = new SimpleStringProperty();
    private StringProperty bX = new SimpleStringProperty();
    private StringProperty cX = new SimpleStringProperty();
    private StringProperty aY = new SimpleStringProperty();
    private StringProperty bY = new SimpleStringProperty();
    private StringProperty cY = new SimpleStringProperty();
    private StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final BooleanProperty btnDisabled = new SimpleBooleanProperty();

    public ViewModel() {
        initDefaultFields();
    }

    public String getAx() {
        return aX.get();
    }

    public StringProperty aXProperty() {
        return aX;
    }

    public String getBx() {
        return bX.get();
    }

    public StringProperty bXProperty() {
        return bX;
    }

    public String getCx() {
        return cX.get();
    }

    public StringProperty cXProperty() {
        return cX;
    }

    public String getAy() {
        return aY.get();
    }

    public StringProperty aYProperty() {
        return aY;
    }

    public String getBy() {
        return bY.get();
    }

    public StringProperty bYProperty() {
        return bY;
    }

    public String getCy() {
        return cY.get();
    }

    public StringProperty cYProperty() {
        return cY;
    }

    public String getResult() {
        return result.get();
    }

    public String getStatus() {
        return status.get();
    }

    public BooleanProperty btnDisabledProperty() {
        return btnDisabled;
    }

    public final boolean isBtnDisabled() {
        return btnDisabled.get();
    }

    private void initDefaultFields() {
        aX.set("");
        bX.set("");
        cX.set("");
        aY.set("");
        bY.set("");
        cY.set("");
        result.set("");
        status.set(Status.READY.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(aX, aY, bX, bY, cX, cY);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            };
        };
        btnDisabled.bind(couldCalculate.not());
    }

    public Point getPointA() {
        return new Point(getAx(), getAy());
    }

    public Point getPointB() {
        return new Point(getBx(), getBy());
    }

    public Point getPointC() {
        return new Point(getCx(), getCy());
    }

    public void perimeter() {
        if (!btnDisabled.get()) {
            try {
                Triangle triangle;
                triangle = new Triangle(getPointA(), getPointB(), getPointC());
                result.set(String.valueOf(triangle.getPerimeter()));
            } catch (IllegalArgumentException nfe) {
                result.set("Invalid triangle");
            }
        }
    }

    public void square() {
        if (!btnDisabled.get()) {
            try {
                Triangle triangle;
                triangle = new Triangle(getPointA(), getPointB(), getPointC());
                result.set(String.valueOf(triangle.getSquare()));
            } catch (IllegalArgumentException nfe) {
                result.set("Invalid triangle");
            }
        }
    }

    public void getLengthAB() {
        if (!btnDisabled.get()) {
            try {
                Triangle triangle;
                triangle = new Triangle(getPointA(), getPointB(), getPointC());
                result.set(String.valueOf(triangle.getLengthA()));
            } catch (IllegalArgumentException nfe) {
                result.set("Invalid triangle");
            }
        }
    }

    public void getLengthBC() {
        if (!btnDisabled.get()) {
            try {
                Triangle triangle;
                triangle = new Triangle(getPointA(), getPointB(), getPointC());
                result.set(String.valueOf(triangle.getLengthB()));
            } catch (IllegalArgumentException nfe) {
                result.set("Invalid triangle");
            }
        }
    }

    public void getLengthCA() {
        if (!btnDisabled.get()) {
            try {
                Triangle triangle;
                triangle = new Triangle(getPointA(), getPointB(), getPointC());
                result.set(String.valueOf(triangle.getLengthC()));
            } catch (IllegalArgumentException nfe) {
                result.set("Invalid triangle");
            }
        }
    }

    public void getCABAngle() {
        if (!btnDisabled.get()) {
            try {
                Triangle triangle;
                triangle = new Triangle(getPointA(), getPointB(), getPointC());
                result.set(String.valueOf(triangle.getAngleBC()));
            } catch (IllegalArgumentException nfe) {
                result.set("Invalid triangle");
            }
        }
    }

    public void getABCAngle() {
        if (!btnDisabled.get()) {
            try {
                Triangle triangle;
                triangle = new Triangle(getPointA(), getPointB(), getPointC());
                result.set(String.valueOf(triangle.getAngleCA()));
            } catch (IllegalArgumentException nfe) {
                result.set("Invalid triangle");
            }
        }
    }

    public void getBCAAngle() {
        if (!btnDisabled.get()) {
            try {
                Triangle triangle;
                triangle = new Triangle(getPointA(), getPointB(), getPointC());
                result.set(String.valueOf(triangle.getAngleAB()));
            } catch (IllegalArgumentException nfe) {
                result.set("Invalid triangle");
            }
        }
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        List<StringProperty> pointArray = new ArrayList<>();
        pointArray.add(aY);
        pointArray.add(aX);
        pointArray.add(bX);
        pointArray.add(bY);
        pointArray.add(cX);
        pointArray.add(cY);

        for (StringProperty field : pointArray) {

            try {
                if (field.get().isEmpty()) {
                    Double.parseDouble(field.get());
                }
            } catch (NumberFormatException nfe) {
                result.set("Invalid triangle");
            }
        }

        return inputStatus;
    }
}

enum Status {
    READY("Press 'Calculate' or Enter");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
