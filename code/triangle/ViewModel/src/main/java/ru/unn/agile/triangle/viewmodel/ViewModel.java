package ru.unn.agile.triangle.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.Triangle;
import ru.unn.agile.primitives.Point;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    public static final String EDITING_FINISHED = "Updated input."
            + " Input arguments are: %s, %s, %s, %s, %s, %s ";
    private StringProperty aX = new SimpleStringProperty();
    private StringProperty bX = new SimpleStringProperty();
    private StringProperty cX = new SimpleStringProperty();
    private StringProperty aY = new SimpleStringProperty();
    private StringProperty bY = new SimpleStringProperty();
    private StringProperty cY = new SimpleStringProperty();
    private StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty logs = new SimpleStringProperty();
    private ILogger logger;
    private List<ValueCachingChangeListener> valueChangedListeners;

    private final BooleanProperty btnDisabled = new SimpleBooleanProperty();

    public ViewModel() {
        initDefaultFields();
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);
        initDefaultFields();
    }

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
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

    public String getLogs() {
        return logs.get();
    }

    public void setLogs(final String logs) {
        this.logs.set(logs);
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
            }

        };
        btnDisabled.bind(couldCalculate.not());
        final List<StringProperty> values = new ArrayList<StringProperty>() {
            {
                add(aX);
                add(aY);
                add(bX);
                add(bY);
                add(cX);
                add(cY);
            }
        };
        valueChangedListeners = new ArrayList<>();
        for (StringProperty val : values) {
            final ValueCachingChangeListener listener = new ValueCachingChangeListener();
            val.addListener(listener);
            valueChangedListeners.add(listener);
        }

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

    public void checkOnFocusChanged(final Boolean oldValue, final Boolean newValue) {
        if (!oldValue && newValue) {
            return;
        }

        for (ValueCachingChangeListener listener : valueChangedListeners) {
            if (listener.isChanged()) {
                String messageString = String.format(EDITING_FINISHED,
                        aX.get(), aY.get(), bX.get(), bY.get(), cX.get(), cY.get());
                logger.log(messageString);
                updateLogs();
                listener.cache();
                break;
            }
        }
    }

    private void updateLogs() {
        List<String> fullLog = logger.getLogger();
        String record = new String("");
        for (String log : fullLog) {
            record += log + "\n";
        }
        logs.set(record);
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

    public class ValueCachingChangeListener implements ChangeListener<String> {
        private String prevValue = new String("");
        private String curValue = new String("");

        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            if (oldValue.equals(newValue)) {
                return;
            }
            status.set(getInputStatus().toString());
            curValue = newValue;
        }

        public boolean isChanged() {
            return !prevValue.equals(curValue);
        }

        public void cache() {
            prevValue = curValue;
        }
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
