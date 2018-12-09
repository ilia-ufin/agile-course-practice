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

    private final StringProperty log = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    private ILogger logger;

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

    public List<String> getLogList() {
        return logger.getLog();
    }

    public String getLog() {
        return log.get();
    }

    public StringProperty logProperty() {
        return log;
    }

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger can't be null");
        }
        this.logger = logger;
    }

    public ViewModel() {
        init();
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);
        init();
    }

    private void init() {
        setDefaultVector();
        setDefaultOtherVector();
        setDefaultMultCoeffAndResult();
        setDefaultOperationAndStatus();
        setDefaultLog();

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
            appendLog(String.format(LogMessages.CALCULATE_WAS_PRESSED,
                    vectorX.get(), vectorY.get(), vectorZ.get(),
                    otherVectorX.get(), otherVectorY.get(), otherVectorZ.get(),
                    multiplicationCoeff.get(),
                    operation.get()));
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

    private void setDefaultLog() {
        log.set(EMPTY_STRING);
    }

    private void appendLog(final String s) {
        logger.log(s);

        StringBuilder logMsg = new StringBuilder();
        for (String line : logger.getLog()) {
            logMsg.append(line).append("\n");
        }
        log.set(logMsg.toString());
    }

    private class ValueChangeListener implements ChangeListener<Object> {
        @Override
        public void changed(final ObservableValue<?> observable,
                            final Object oldValue, final Object newValue) {
            status.set(getInputStatus().toString());

            if (observable == operation) {
                appendLog(String.format(LogMessages.OPERATION_WAS_CHANGED, newValue));
            } else if (observable == multiplicationCoeff) {
                appendLog(String.format(LogMessages.MULT_COEF_WAS_CHANGED, newValue));
            } else {
                appendLog(String.format(LogMessages.VECTORS_WERE_CHANGED,
                        vectorX.get(), vectorY.get(), vectorZ.get(),
                        otherVectorX.get(), otherVectorY.get(), otherVectorZ.get()));
            }
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

    public static final class LogMessages {
        public static final String VECTORS_WERE_CHANGED =
                "Vectors were changed: (%s, %s, %s) - (%s, %s, %s)";
        public static final String MULT_COEF_WAS_CHANGED =
                "Multiplication coefficient was changed to %s";
        public static final String OPERATION_WAS_CHANGED = "Operation was changed to %s";
        public static final String CALCULATE_WAS_PRESSED =
                "Calculate. Arguments: (%s, %s, %s) - (%s, %s, %s), coef = %s, op = %s";
    }
}
