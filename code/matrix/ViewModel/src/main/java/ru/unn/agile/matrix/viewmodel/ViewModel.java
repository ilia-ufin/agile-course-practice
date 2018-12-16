package ru.unn.agile.matrix.viewmodel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.matrix.model.Matrix;

import java.util.List;

public class ViewModel {
    private final StringProperty matrixA = new SimpleStringProperty();
    private final StringProperty matrixB = new SimpleStringProperty();
    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty calculateButtonDisabled = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private ILogger logger;
    private final ListProperty<String> log = new SimpleListProperty<>();

    public ViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;

        matrixA.setValue("[1 2][3 4]");
        matrixB.setValue("[4 3][2 1]");
        operation.setValue(Operation.ADD);
        calculateButtonDisabled.setValue(false);
        result.setValue("");
        status.setValue(Status.READY.toString());

        StringValueChangeListener stringValueChangeListener =
                new StringValueChangeListener();
        matrixA.addListener(stringValueChangeListener);
        matrixB.addListener(stringValueChangeListener);

        OperationValueChangeListener operationValueChangeListener =
                new OperationValueChangeListener();
        operation.addListener(operationValueChangeListener);
    }

    public StringProperty matrixAProperty() {
        return matrixA;
    }

    public StringProperty matrixBProperty() {
        return matrixB;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public ObjectProperty<ObservableList<Operation>> operationsProperty() {
        return operations;
    }

    public ObjectProperty<Operation> operationProperty() {
        return operation;
    }

    public BooleanProperty calculateButtonDisabledProperty() {
        return calculateButtonDisabled;
    }

    public ListProperty<String> logProperty() {
        return log;
    }

    public Status getInputStatus() {
        if (matrixA.get().isEmpty() || matrixB.get().isEmpty()) {
            return Status.WAITING;
        }

        if (StringToMatrixConverter.isValidMatrix(matrixA.get())
                && StringToMatrixConverter.isValidMatrix(matrixB.get())) {
            switch (operation.get()) {
                case ADD:
                case SUBTRACT:
                    if (!StringToMatrixConverter.areSameSized(matrixA.get(), matrixB.get())) {
                        return Status.INCOMPATIBLE_SIZE;
                    }
                    break;
                case MULTIPLY:
                    if (!StringToMatrixConverter.areSizeCompatibleForMultiplication(
                            matrixA.get(), matrixB.get())) {
                        return Status.INCOMPATIBLE_SIZE;
                    }
                    break;
                default:
                    break;
            }
            return Status.READY;
        }
        return Status.BAD_FORMAT;
    }

    public void calculate() {
        if (!canCalculate()) {
            return;
        }

        Matrix mA = StringToMatrixConverter.convertToMatrix(matrixA.get());
        Matrix mB = StringToMatrixConverter.convertToMatrix(matrixB.get());
        Matrix res = operation.getValue().apply(mA, mB);

        result.setValue(res.toString());
        status.setValue(Status.SUCCESS.toString());

        logger.log(calculateLogMessage());
        updateLogProperty();
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    private boolean canCalculate() {
        return getInputStatus() == Status.READY;
    }

    private void updateStateWhenValuesChange() {
        status.set(getInputStatus().toString());
        calculateButtonDisabled.set(!canCalculate());
    }

    private class StringValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            updateStateWhenValuesChange();
            if (status.get() == Status.READY.toString()) {
                logger.log(LogMessages.EDITING_FINISHED
                        + "A = " + matrixA.get() + "; "
                        + "B = " + matrixB.get());
                updateLogProperty();
            }
        }
    }

    private class OperationValueChangeListener implements ChangeListener<Operation> {
        @Override
        public void changed(final ObservableValue<? extends Operation> observable,
                            final Operation oldValue, final Operation newValue) {
            updateStateWhenValuesChange();
            logger.log(LogMessages.OPERATION_CHANGED + operation.get().toString());
            updateLogProperty();
        }
    }

    private String calculateLogMessage() {
        return LogMessages.CALCULATE_PRESSED
                + matrixA.get()
                + operation.get().toString()
                + matrixB.get();
    }

    private void updateLogProperty() {
        log.set(FXCollections.observableArrayList(logger.getLog()));
    }

    public enum Operation {
        ADD("+") {
            public Matrix apply(final Matrix l, final Matrix r) {
                return l.add(r);
            }
        },
        SUBTRACT("-") {
            public Matrix apply(final Matrix l, final Matrix r) {
                return l.subtract(r);
            }
        },
        MULTIPLY("x") {
            public Matrix apply(final Matrix l, final Matrix r) {
                return l.multiply(r);
            }
        };

        Operation(final String name) {
            this.name = name;
        }

        public abstract Matrix apply(Matrix l, Matrix r);

        @Override
        public String toString() {
            return name;
        }

        private final String name;
    }
}

enum Status {
    WAITING("Waiting for input"),
    BAD_FORMAT("Bad format"),
    INCOMPATIBLE_SIZE("Matrices have incompatible size"),
    READY("Press 'Calculate' button"),
    SUCCESS("Success");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}

enum LogMessages {
    CALCULATE_PRESSED("Calculate "),
    OPERATION_CHANGED("Operation changed to "),
    EDITING_FINISHED("Input changed to ");

    private final String message;
    LogMessages(final String message) {
        this.message = message;
    }
    public String toString() {
        return message;
    }
}

