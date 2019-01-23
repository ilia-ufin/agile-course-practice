package ru.unn.agile.binarytree.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.binarytree.model.BinarySearchTree;
import ru.unn.agile.binarytree.model.node.BinaryNode;
import ru.unn.agile.binarytree.model.BinarySearchTree.Operation;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final BinarySearchTree<String, Double> tree = new BinarySearchTree<String, Double>();

    private final StringProperty key = new SimpleStringProperty();
    private final StringProperty value = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty executionDisabled = new SimpleBooleanProperty();

    private final StringProperty status = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    private Status getStatusOfInput() {
        Status inputStatus = Status.READY;
        if (key.get().isEmpty() || value.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!value.get().isEmpty()) {
                Double.parseDouble(value.get());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    // FXML needs default c-tor for binding
    public ViewModel() {
        key.set("");
        value.set("");
        operation.set(Operation.ADD);
        status.set(Status.WAITING.toString());
        executionDisabled.set(true);


        BooleanBinding couldExecute = new BooleanBinding() {
            {
                super.bind(key, value);
            }
            @Override
            protected boolean computeValue() {
                return getStatusOfInput() == Status.READY;
            }
        };
        executionDisabled.bind(couldExecute.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(key);
            add(value);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void execute() {
        if (executionDisabled.get()) {
            return;
        }
        BinaryNode<String, Double> node = new BinaryNode<String, Double>(key.get(),
                Double.parseDouble(value.get()));
        if (operation.get().apply(tree, node)) {
            status.set(Status.SUCCESS.toString());
        } else {
            status.set(Status.UNSUCCESS.toString());
        }
    }

    public StringProperty keyProperty() {
        return key;
    }

    public StringProperty valueProperty() {
        return value;
    }

    public ObjectProperty<ObservableList<Operation>> operationsProperty() {
        return operations;
    }

    public final ObservableList<Operation> getOperations() {
        return operations.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public ObjectProperty<Operation> operationProperty() {
        return operation;
    }

    public BooleanProperty executionDisabledProperty() {
        return executionDisabled;
    }

    public final boolean isExecutionDisabled() {
        return executionDisabled.get();
    }

    public final String getStatus() {
        return status.get();
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getStatusOfInput().toString());
        }
    }
}

enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Execute' to perform command"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Operation performed successfully"),
    UNSUCCESS("Operation performed unsuccessfully");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
