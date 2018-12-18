package ru.unn.agile.MyAbstractSet.viewmodel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import ru.unn.agile.MyAbstractSet.model.MyAbstractSet;

import java.util.List;

public class ViewModel {
    private final StringProperty firstSetTextArea = new SimpleStringProperty();
    private final StringProperty secondSetTextArea = new SimpleStringProperty();
    private final StringProperty resultTextArea = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final BooleanProperty executeButtonDisabled = new SimpleBooleanProperty();
    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private static final String WHITESPACE_PATTERN = "\\s+";
    private static final String VALID_INPUT_PATTERN = "^-?[a-z A-Z0-9,]+";
    private static final String LETTERS_PATTERN = "[a-zA-Z]{2,}";
    private ILogger logger;
    private final ListProperty<String> logList = new SimpleListProperty<>();

    public ViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
        firstSetTextArea.setValue("");
        secondSetTextArea.setValue("");
        operation.setValue(Operation.UNITE);
        resultTextArea.setValue("");
        status.setValue(Status.WAITING.toString());
        executeButtonDisabled.setValue(true);

        StringValueChangeListener stringValueChangeListener = new StringValueChangeListener();
        firstSetTextArea.addListener(stringValueChangeListener);
        secondSetTextArea.addListener(stringValueChangeListener);

        OperationValueChangeListener operationValueChangeListener =
                new OperationValueChangeListener();
        operation.addListener(operationValueChangeListener);
    }

    public StringProperty firstSetTextAreaProperty() {
        return firstSetTextArea;
    }

    public StringProperty secondSetTextAreaProperty() {
        return secondSetTextArea;
    }

    public StringProperty resultTextAreaProperty() {
        return resultTextArea;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public BooleanProperty executeButtonDisabledProperty() {
        return executeButtonDisabled;
    }

    public ObjectProperty<ObservableList<Operation>> operationsProperty() {
        return operations;
    }

    public ObjectProperty<Operation> operationProperty() {
        return operation;
    }

    public final List<String> getLog() {
        return logger.getLog();
    }
    public final ListProperty<String> logProperty() {
        return logList;
    }

    private void updateLog() {
        logList.set(FXCollections.observableArrayList(logger.getLog()));
    }

    public void execute() {
        if (executeButtonDisabled.get()) {
            return;
        }

        Object[] firstSet = firstSetTextArea.get().replaceAll(WHITESPACE_PATTERN, "").split(",");
        Object[] secondSet = secondSetTextArea.get().replaceAll(WHITESPACE_PATTERN, "").split(",");

        MyAbstractSet set1 = new MyAbstractSet(firstSet);
        MyAbstractSet set2 = new MyAbstractSet(secondSet);

        MyAbstractSet res = operation.get().apply(set1, set2);
        status.set(Status.SUCCESS.toString());
        resultTextArea.setValue(res.toString());

        String message = String.format(LogMessages.EXECUTE_PRESSED, firstSetTextArea.get(),
                secondSetTextArea.get(), operation.get());
        logger.log(message);
        updateLog();
    }

    private boolean checkValidInput(final String input) {
        return !input.matches(LETTERS_PATTERN) && input.matches(VALID_INPUT_PATTERN);
    }

    public Status getInputStatus() {
        String firstSet = firstSetTextArea.get();
        String secondSet = secondSetTextArea.get();
        if (firstSet.isEmpty() || firstSet.matches(WHITESPACE_PATTERN)
                || secondSet.isEmpty() || secondSet.matches(WHITESPACE_PATTERN)) {
            return Status.WAITING;
        } else if (!checkValidInput(firstSet) || !checkValidInput(secondSet)) {
            return Status.BAD_FORMAT;
        } else {
            return Status.READY;
        }
    }

    private boolean canNotExecuteOperation() {
        return getInputStatus() != Status.READY;
    }

    private class StringValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
            executeButtonDisabled.set(canNotExecuteOperation());
            if (status.get().equals(Status.READY.toString())) {
                String message = String.format(LogMessages.EDITING_FINISHED, firstSetTextArea.get(),
                        secondSetTextArea.get());
                logger.log(message);
                updateLog();
            }
        }
    }

    private class OperationValueChangeListener implements ChangeListener<Operation> {
        @Override
        public void changed(final ObservableValue<? extends Operation> observableValue,
                            final Operation oldValue, final Operation newValue) {
            status.set(getInputStatus().toString());
            executeButtonDisabled.set(canNotExecuteOperation());
            logger.log(LogMessages.OPERATION_CHANGED + operation.get().toString());
            updateLog();
        }
    }

    public enum Operation {
        UNITE("Unite") {
            public MyAbstractSet apply(final MyAbstractSet set1, final MyAbstractSet set2) {
                return set1.unite(set2);
            }
        },
        INTERSECT("Intersect") {
            public MyAbstractSet apply(final MyAbstractSet set1, final MyAbstractSet set2) {
                return set1.intersect(set2);
            }
        };

        private final String name;
        Operation(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public abstract MyAbstractSet apply(MyAbstractSet set1, MyAbstractSet set2);
    }
}

enum Status {
    WAITING("Waiting for input"),
    BAD_FORMAT("Bad format"),
    READY("Press 'result' button"),
    SUCCESS("Success");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}

final class LogMessages {
    public static final String EXECUTE_PRESSED = "Execute. SetA : { %s } ; SetB : { %s }. "
            + "Operation : %s .";
    public static final String OPERATION_CHANGED = "Operation was changed to ";
    public static final String EDITING_FINISHED = "Updated input. SetA : { %s }; SetB : { %s }";

    private LogMessages() { }
}
