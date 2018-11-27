package ru.unn.agile.vectordistance.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import ru.unn.agile.vectordistance.model.FloatVector;
import ru.unn.agile.vectordistance.model.VectorDistance.Distance;

import java.util.Arrays;
import java.util.List;

public class ViewModel {
    private static final String DEFAULT_DELIMITER = " ";
    private static final String VALID_VECTOR_PATTERN = "^(-?\\d+(\\.\\d*)?)([ ]-?\\d+(\\.\\d*)?)*$";
    private final StringProperty vectorX = new SimpleStringProperty();
    private final StringProperty vectorY = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final ObjectProperty<ObservableList<Distance>> distances =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Distance.values()));
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    public final ObservableList<Distance> getDistances() {
        return distances.get();
    }

    private final ObjectProperty<Distance> distance = new SimpleObjectProperty<>();

    public Distance getDistanceProperty() {
        return distanceProperty().get();
    }

    public String getVectorXProperty() {
        return vectorXProperty().get();
    }

    public String getVectorYProperty() {
        return vectorYProperty().get();
    }

    public String getResultProperty() {
        return resultProperty().get();
    }

    public String getStatusProperty() {
        return statusProperty().get();
    }

    public boolean isCalculationDisabledProperty() {
        return calculationDisabledProperty().get();
    }

    public ViewModel() {
        vectorX.set("");
        vectorY.set("");
        result.set("");
        status.set(Status.WAITING.toString());
        distance.set(Distance.L1);
        setCouldCalculateBinding();
        addListenersToInputData();
    }

    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }

        FloatVector a = getVectorFromInputString(vectorX.get());
        FloatVector b = getVectorFromInputString(vectorY.get());
        try {
            result.set(String.valueOf(distance.get().apply(a, b)));
        } catch (Exception e) {
            result.set(e.getMessage());
        }
        status.set(Status.SUCCESS.toString());
    }

    public ObjectProperty<Distance> distanceProperty() {
        return distance;
    }

    public StringProperty vectorXProperty() {
        return vectorX;
    }

    public StringProperty vectorYProperty() {
        return vectorY;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public final boolean isCalculationDisabled() {
        return calculationDisabled.get();
    }

    private void setCouldCalculateBinding() {
        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(vectorX, vectorY);
            }

            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());
    }

    private void addListenersToInputData() {
        final List<StringProperty> fields = Arrays.asList(vectorX, vectorY);
        final VectorValueChangeListener listener = new VectorValueChangeListener();
        for (StringProperty field : fields) {
            field.addListener(listener);
        }
    }

    private static List<String> getNumbersArrayFromString(final String numbersString) {
        return Arrays.asList(numbersString.split(DEFAULT_DELIMITER));
    }

    private float[] getFloatArrayFromStringList(final List<String> stringList) {
        float[] floatArray = new float[stringList.size()];

        int i = 0;

        for (String number : stringList) {
            floatArray[i++] = Float.parseFloat(number);
        }
        return floatArray;
    }

    private FloatVector getVectorFromInputString(final String inputString) {
        List<String> singleNumbers = getNumbersArrayFromString(inputString);
        return new FloatVector(getFloatArrayFromStringList(singleNumbers));
    }

    private boolean checkInputIsValid(final String vectorInput) {
        return vectorInput.matches(VALID_VECTOR_PATTERN);
    }

    private class VectorValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }

    private Status getInputStatus() {
        if (vectorX.get().isEmpty() || vectorY.get().isEmpty()) {
            return Status.WAITING;
        }
        if (!checkInputIsValid(vectorX.get()) || !checkInputIsValid(vectorY.get())) {
            return Status.BAD_FORMAT;
        }

        return Status.READY;
    }

}

enum Status {
    WAITING("Please set Vectors"),
    READY("Input data correct. Press calculate."),
    BAD_FORMAT("Bad data inserted"),
    SUCCESS("Success");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
