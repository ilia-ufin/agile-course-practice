package ru.unn.agile.fibonacciHeap.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.fibonacciHeap.model.FibonacciHeap;

import java.util.List;

public class ViewModel {
    private FibonacciHeap model;
    private static final String INPUT_ERROR = "Ошибка ввода";
    private final StringProperty inputValue = new SimpleStringProperty();
    private final StringProperty minElem = new SimpleStringProperty();
    private final StringProperty heapLength = new SimpleStringProperty();
    private final StringProperty errorLabel = new SimpleStringProperty();
    private ILogger logger;

    public ViewModel(final ILogger logger) {
        this.logger = logger;
        model = new FibonacciHeap();
        heapLength.set("");
        inputValue.set("");
        minElem.set("");
        errorLabel.set("");
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    public void fetchMinElement() {
        String value = String.valueOf(model.getRootNode().getValue());

        logger.log("Минимальный элемент стал " + value);

        minElem.set(value);
    }

    public void fetchHeapLength() {
        String length = String.valueOf(model.getRootAmount());

        logger.log("Кол-во узлов в куче стало " + length);

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

                logger.log("Добавлен элемент " + inputValue.get());

                fetchData();
                clear();
            }
        } catch (NumberFormatException nfe) {
            logger.log(INPUT_ERROR);
            errorLabel.set(INPUT_ERROR);
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
