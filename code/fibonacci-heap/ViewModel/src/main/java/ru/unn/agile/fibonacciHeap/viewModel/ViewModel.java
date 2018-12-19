package ru.unn.agile.fibonacciHeap.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.fibonacciHeap.model.FibonacciHeap;

public class ViewModel {
    private FibonacciHeap model;
    private final StringProperty inputValue = new SimpleStringProperty();
    private final StringProperty minElem = new SimpleStringProperty();
    private final StringProperty heapLength = new SimpleStringProperty();
    private final StringProperty errorLabel = new SimpleStringProperty();

    public ViewModel() {
        model = new FibonacciHeap();
        heapLength.set("");
        inputValue.set("");
        minElem.set("");
        errorLabel.set("");
    }

    public void fetchMinElement() {
        String length = String.valueOf(model.getRootNode().getValue());

        minElem.set(length);
    }

    public void fetchHeapLength() {
        String length = String.valueOf(model.getRootAmount());

        heapLength.set(length);
    }

    public void fetchData() {
        fetchMinElement();
        fetchHeapLength();
    }

    public void clear() {
        inputValue.set("");
        errorLabel.set("");
    }

    public void addElem() {
        try {
            if (!inputValue.get().isEmpty()) {
                int elem = Integer.parseInt(inputValue.get());

                model.add(elem);

                fetchData();
                clear();
            }
        } catch (NumberFormatException nfe) {
            errorLabel.set("Ошибка ввода");
        }
    }

    public StringProperty errorProperty() {
        return errorLabel;
    }

    public StringProperty inputValueProperty() {
        return inputValue;
    }

    public StringProperty minElemProperty() {
        return minElem;
    }

    public StringProperty heapLengthProperty() {
        return heapLength;
    }
}
