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

    private List<Double> sortedArray = new ArrayList<Double>();
    private List<Double> inputArray = new ArrayList<Double>();

    public ViewModel() {
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
    }

    public void clearProcess() {
        sortedArray.clear();
        inputArray.clear();
        changeButtonsEnabling();

        sortedArrayStringRepresentation = sortedArray.toString();
        inputArrayStringRepresentation = inputArray.toString();
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
    }

    public String getCurrentState() {
        return status;
    }

    public final class Status {
        public static final String WAITING = "Waiting new element";
        public static final String READY = "Ready to add new element";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESSFUL = "Sort of array is successful";

        private Status() { }
    }

    public void processingAddField(final int keyCode) {
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
