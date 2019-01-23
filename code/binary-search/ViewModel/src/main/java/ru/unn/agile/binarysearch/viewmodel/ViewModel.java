package ru.unn.agile.binarysearch.viewmodel;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.binarysearch.model.BadArrayException;
import ru.unn.agile.binarysearch.model.BinarySearch;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {

    private ILogger logger;
    public static final String LOG_MESSAGE_INPUT_ARRAY = "Input array: ";
    public static final String LOG_MESSAGE_INPUT_VALUE = "Input value: ";
    public static final String LOG_MESSAGE_RESULT = "Result: ";

    private final StringProperty arrayInput = new SimpleStringProperty();
    private final StringProperty elementInput = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty log = new SimpleStringProperty();
    private final BooleanProperty searchDisabled = new SimpleBooleanProperty();
    private BinarySearch search;
    private int key;
    private boolean arrayCorrect;
    private boolean elementCorrect;
    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        arrayInput.set("");
        elementInput.set("");
        status.set("");
        result.set("");
        log.set("");
        arrayCorrect = false;
        elementCorrect = false;
        BooleanBinding couldSearch = new BooleanBinding() {
            {
                super.bind(arrayInput, elementInput);
            }
            @Override
            protected boolean computeValue() {
                return arrayCorrect && elementCorrect;
            }
        };
        searchDisabled.bind(couldSearch.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(arrayInput);
            add(elementInput);
        }};
        for (StringProperty field: fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getStatus().toString());
        }
    }
    public StringProperty arrayInputProperty() {
        return arrayInput;
    }
    public StringProperty elementInputProperty() {
        return elementInput;
    }
    public StringProperty statusProperty() {
        return status;
    }
    public StringProperty resultProperty() {
        return result;
    }
    public StringProperty logProperty() {
        return log;
    }


    public String getArrayInputProperty() {
        return arrayInput.get();
    }

    public String getElementInputProperty() {
        return elementInput.get();
    }

    public String getStatusProperty() {
        return status.get();
    }

    public void setArrayInputProperty(final String input) {
        logger.log(LOG_MESSAGE_INPUT_ARRAY + input);
        log.set(String.join(";", logger.getLog()));
        arrayInput.set(input);
    }

    public int[] getBinarySearchArray() {
        return search.getArray();
    }

    public void setElementInputProperty(final String input) {
        logger.log(LOG_MESSAGE_INPUT_VALUE + input);
        log.set(String.join(";", logger.getLog()));
        elementInput.set(input);
    }
    private void setResultProperty(final String res) {
        logger.log(LOG_MESSAGE_RESULT + res);
        log.set(String.join(";", logger.getLog()));
        result.set(res);
    }

    public void search() {
        if (searchDisabled.get()) {
            return;
        }
        int index = search.search(key);
        if (index == -1) {
            setResultProperty("Key not found");
        } else {
            setResultProperty("Found key, index " + Integer.toString(index));
        }
    }

    public String getResultProperty() {
        return result.get();
    }

    public Status getStatus() {
        Status status = Status.READY;
        try {
            String[] split = arrayInput.get().split(",");
            int[] arr = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                arr[i] = Integer.parseInt(split[i]);
            }
            arrayCorrect = true;
            search = new BinarySearch(arr);
        } catch (NumberFormatException nfe) {
            status = Status.BAD_ARRAY_FORMAT;
        } catch (BadArrayException bae) {
            status = Status.BAD_ARRAY_SORT;
        }
        try {
            key = Integer.parseInt(elementInput.get());
            elementCorrect = true;
        } catch (NumberFormatException nfe) {
            status = Status.BAD_ELEMENT_FORMAT;
        }
        return status;
    }
    public boolean isSearchDisabled() {
        return searchDisabled.get();
    }
}

enum Status {
    READY("Press Search"),
    BAD_ARRAY_FORMAT("Enter array of ints, separated by commas"),
    BAD_ARRAY_SORT("Array must be sorted"),
    BAD_ELEMENT_FORMAT("Enter key to be searched");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
