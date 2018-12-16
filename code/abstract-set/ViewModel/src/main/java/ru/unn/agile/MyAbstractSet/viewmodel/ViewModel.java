package ru.unn.agile.MyAbstractSet.viewmodel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import ru.unn.agile.MyAbstractSet.model.MyAbstractSet;

public class ViewModel {
    private final StringProperty firstSetTextArea = new SimpleStringProperty();
    private final StringProperty secondSetTextArea = new SimpleStringProperty();
    private final StringProperty resultTextArea = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final BooleanProperty executeButtonDisabled = new SimpleBooleanProperty();
    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final ValueChangeListener valueChangeListener = new ValueChangeListener();
    private static final String WHITESPACE_PATTERN = "\\s+";
    private static final String VALID_INPUT_PATTERN = "^-?[a-z A-Z0-9,]+";
    private static final String LETTERS_PATTERN = "[a-zA-Z]{2,}";


    public ViewModel() {
        firstSetTextArea.setValue("");
        secondSetTextArea.setValue("");
        operation.setValue(Operation.UNITE);
        resultTextArea.setValue("");
        status.setValue(Status.WAITING.toString());
        executeButtonDisabled.setValue(true);

        firstSetTextArea.addListener(valueChangeListener);
        secondSetTextArea.addListener(valueChangeListener);
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

    public void execute() {
        Object[] firstSet = firstSetTextArea.get().replaceAll(WHITESPACE_PATTERN, "").split(",");
        Object[] secondSet = secondSetTextArea.get().replaceAll(WHITESPACE_PATTERN, "").split(",");

        MyAbstractSet set1 = new MyAbstractSet(firstSet);
        MyAbstractSet set2 = new MyAbstractSet(secondSet);

        MyAbstractSet res = operation.get().apply(set1, set2);
        status.set(Status.SUCCESS.toString());
        resultTextArea.setValue(res.toString());
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

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
            executeButtonDisabled.set(canNotExecuteOperation());
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
