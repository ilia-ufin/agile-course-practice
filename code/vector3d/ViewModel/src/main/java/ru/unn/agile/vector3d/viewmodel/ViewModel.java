package ru.unn.agile.vector3d.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.vector3d.viewmodel.actions.ActionFactory;
import ru.unn.agile.vector3d.viewmodel.actions.IAction;
import ru.unn.agile.vector3d.viewmodel.validators.IValidator;
import ru.unn.agile.vector3d.viewmodel.validators.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private static final String EMPTY_STRING = "";

    private final StringProperty vectorX = new SimpleStringProperty();
    private final StringProperty vectorY = new SimpleStringProperty();
    private final StringProperty vectorZ = new SimpleStringProperty();

    private final StringProperty otherVectorX = new SimpleStringProperty();
    private final StringProperty otherVectorY = new SimpleStringProperty();
    private final StringProperty otherVectorZ = new SimpleStringProperty();

    private final StringProperty multiplicationCoeff = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(
                    Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();

    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public final ObservableList<Operation> getOperations() {
        return operations.get();
    }

    public StringProperty vectorXProperty() {
        return vectorX;
    }

    public StringProperty vectorYProperty() {
        return vectorY;
    }

    public StringProperty vectorZProperty() {
        return vectorZ;
    }

    public StringProperty otherVectorXProperty() {
        return otherVectorX;
    }

    public StringProperty otherVectorYProperty() {
        return otherVectorY;
    }

    public StringProperty otherVectorZProperty() {
        return otherVectorZ;
    }

    public String getVectorX() {
        return vectorX.get();
    }

    public String getVectorY() {
        return vectorY.get();
    }

    public String getVectorZ() {
        return vectorZ.get();
    }

    public String getOtherVectorX() {
        return otherVectorX.get();
    }

    public String getOtherVectorY() {
        return otherVectorY.get();
    }

    public String getOtherVectorZ() {
        return otherVectorZ.get();
    }

    public String getMultiplicationCoeff() {
        return multiplicationCoeff.get();
    }

    public Operation getOperation() {
        return operation.get();
    }

    public StringProperty multiplicationCoeffProperty() {
        return multiplicationCoeff;
    }

    public ObjectProperty<Operation> operationProperty() {
        return operation;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getResult() {
        return result.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    public boolean isCalculationDisabled() {
        return calculationDisabled.get();
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public ViewModel() {
        setDefaultVector();
        setDefaultOtherVector();
        setDefaultMultCoeffAndResult();
        setDefaultOperationAndStatus();

        initCalculateStateListener();
        initFieldsListeners();
    }

    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }

        IAction action = ActionFactory.getAction(getOperation());
        if (action != null) {
            action.execute(this);
        }
    }

    private void initFieldsListeners() {
        final List<StringProperty> fields = new ArrayList<StringProperty>() {
            {
                add(vectorX);
                add(vectorY);
                add(vectorZ);
                add(otherVectorX);
                add(otherVectorY);
                add(otherVectorZ);
                add(multiplicationCoeff);
            }
        };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }

        final ValueChangeListener listener = new ValueChangeListener();
        operation.addListener(listener);
        valueChangedListeners.add(listener);
    }

    private void initCalculateStateListener() {
        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(vectorX, vectorY, vectorZ, otherVectorX,
                        otherVectorY, otherVectorZ, multiplicationCoeff, operation);
            }

            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());
    }

    private Status getInputStatus() {
        IValidator validator = ValidatorFactory.getValidator(getOperation());
        if (validator != null) {
            validator.validate(this);
        }

        return Status.getByName(getStatus());
    }

    private void setDefaultVector() {
        vectorX.set(EMPTY_STRING);
        vectorY.set(EMPTY_STRING);
        vectorZ.set(EMPTY_STRING);
    }

    private void setDefaultOtherVector() {
        otherVectorX.set(EMPTY_STRING);
        otherVectorY.set(EMPTY_STRING);
        otherVectorZ.set(EMPTY_STRING);
    }

    private void setDefaultMultCoeffAndResult() {
        multiplicationCoeff.set(EMPTY_STRING);
        result.set(EMPTY_STRING);
    }

    private void setDefaultOperationAndStatus() {
        operation.set(Operation.ADD);
        status.set(Status.WAITING.toString());
    }

    private class ValueChangeListener implements ChangeListener<Object> {
        @Override
        public void changed(final ObservableValue<?> observable,
                            final Object oldValue, final Object newValue) {
            status.set(getInputStatus().toString());
        }
    }

    public enum Status {
        WAITING("Please provide input data"),
        READY("Press 'Calculate' or Enter"),
        BAD_FORMAT("Bad format"),
        SUCCESS("Success");

        private final String name;

        Status(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }

        public static Status getByName(final String name) {
            for (Status status : Status.values()) {
                if (status.name.equals(name)) {
                    return status;
                }
            }
            return null;
        }
    }
}
