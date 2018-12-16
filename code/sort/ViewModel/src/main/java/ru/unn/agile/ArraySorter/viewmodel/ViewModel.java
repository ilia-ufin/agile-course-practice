package ru.unn.agile.ArraySorter.viewmodel;

import ru.unn.agile.arraysorter.model.ArraySorter;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private String inputValue;
    private String sortedArrayStringRepresentation;
    private String inputArrayStringRepresentation;
    private String status;

    private boolean isAddButtonEnabled;
    private boolean isClearButtonEnabled;
    private boolean isSortButtonEnabled;

    private List<Double> sortedArray = new ArrayList<>();
    private List<Double> inputArray = new ArrayList<>();
    private ILogger logger;
    public static final String ADD_LOG = "Added new element to array. The element: ";
    public static final String CLEAR_LOG = "Now input array is clean";

    public ViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }

        this.logger = logger;

        inputValue = "";
        sortedArrayStringRepresentation = "";
        inputArrayStringRepresentation = "";
        status = Status.WAITING;

        isAddButtonEnabled = false;
        isClearButtonEnabled = false;
        isSortButtonEnabled = false;
    }

    public String getSortedArrayStringRepresentation() {
        return sortedArrayStringRepresentation;
    }

    public String getInputArrayStringRepresentation() {
        return inputArrayStringRepresentation;
    }

    public String getElemArray() {
        return inputValue;
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    public void setInputValue(final String inputValue) {
        if (inputValue.equals(this.inputValue)) {
            return;
        }

        this.inputValue = inputValue;
    }

    public void addProcess() {
        if (!parseInput()) {
            return;
        }

        double value = Double.parseDouble(inputValue);
        sortedArray.add(value);
        inputArray.add(value);
        changeButtonsEnabling();

        inputArrayStringRepresentation = sortedArray.toString();
        logger.log(ADD_LOG + inputValue);
    }

    public void clearProcess() {
        sortedArray.clear();
        inputArray.clear();
        changeButtonsEnabling();

        sortedArrayStringRepresentation = sortedArray.toString();
        inputArrayStringRepresentation = inputArray.toString();
        logger.log(CLEAR_LOG);
    }

    public void sort() {
        Double[] nativeArray = new Double[sortedArray.size()];
        sortedArray.toArray(nativeArray);

        ArraySorter.sort(nativeArray);

        for (int i = 0; i < nativeArray.length; ++i) {
            sortedArray.set(i, nativeArray[i]);
        }

        changeButtonsEnabling();
        status = Status.SUCCESSFUL;

        sortedArrayStringRepresentation =  sortedArray.toString();
        logger.log(sortedArrayStringRepresentation);
    }

    public String getCurrentState() {
        return status;
    }

    public final class Status {
        public static final String WAITING = "Waiting new element";
        public static final String READY = "Ready to add new element";
        public static final String BAD_FORMAT = "Bad format. Should be double";
        public static final String SUCCESSFUL = "Sort of array is successful";

        private Status() { }
    }

    public void processingAddField() {
        parseInput();
    }

    private boolean parseInput() {

        if (inputValue.isEmpty()) {
            status = Status.WAITING;
            isAddButtonEnabled = false;
            return isAddButtonEnabled;
        }

        try {
            Double.parseDouble(inputValue);
            status = Status.READY;
            isAddButtonEnabled = true;
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isAddButtonEnabled = false;
            logger.log(status);
            return isAddButtonEnabled;
        }

        return isAddButtonEnabled;
    }

    public boolean isAddButtonEnabled() {
        return isAddButtonEnabled;
    }

    public boolean isClearButtonEnabled() {
        return isClearButtonEnabled;
    }

    public boolean isSortButtonEnabled() {
        return isSortButtonEnabled;
    }

    private void changeButtonsEnabling() {
        if (sortedArray.isEmpty()) {
            isSortButtonEnabled = false;
            isClearButtonEnabled = false;
        } else {
            isSortButtonEnabled = true;
            isClearButtonEnabled = true;
        }
    }
}
