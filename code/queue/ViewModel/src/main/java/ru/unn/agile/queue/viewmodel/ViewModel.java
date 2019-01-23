package ru.unn.agile.queue.viewmodel;

import ru.unn.agile.queue.model.*;

import java.util.List;

public class ViewModel {
    private Queue<Double> queue;

    private String inputElem;
    private String queueStringRepresentation;
    private String statusMessage;

    private boolean isAddButtonEnabled;
    private boolean isRemoveButtonEnabled;
    private boolean isClearButtonEnabled;

    private ILogger logger;

    public ViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger should be initialized");
        }
        this.logger = logger;

        queue = new Queue<Double>();

        inputElem = "";
        queueStringRepresentation = "";
        statusMessage = State.WAITING_FOR_INPUT;

        isAddButtonEnabled = false;
        isRemoveButtonEnabled = false;
        isClearButtonEnabled = false;
    }

    public String getInputElem() {
        return inputElem;
    }

    public String getQueueStringRepresentation() {
        return queueStringRepresentation;
    }

    public void setInputElem(final String inputElem) {
        if (inputElem.equals(this.inputElem)) {
            return;
        }

        this.inputElem = inputElem;
    }

    public void pushProcess() {
        if (!parseInput()) {
            return;
        }

        queue.push(Double.parseDouble(inputElem));
        queueStringRepresentation = queue.toString();
        changeButtonsEnabling();

        logger.log("Push " + inputElem + " element to queue");
    }

    public void popProcess() {
        Double head = queue.pop();

        queueStringRepresentation = queue.toString();
        changeButtonsEnabling();

        logger.log("Pop " + head + " head element from queue");
    }

    public void clear() {
        queue.clear();
        queueStringRepresentation = queue.toString();
        changeButtonsEnabling();

        logger.log("Queue has been cleared");
    }

    public void processingAddField() {
        parseInput();
    }

    public String getCurrentState() {
        return statusMessage;
    }

    public final class State {
        public static final String WAITING_FOR_INPUT = "Waiting new element";
        public static final String READY_TO_ADD = "Ready to add element";
        public static final String INVALID_FORMAT = "Invalid format";
        private State() { }
    }

    public boolean isAddButtonEnabled() {
        return isAddButtonEnabled;
    }

    public boolean isRemoveButtonEnabled() {
        return isRemoveButtonEnabled;
    }

    public boolean isClearButtonEnabled() {
        return isClearButtonEnabled;
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    private boolean parseInput() {
        if (inputElem.isEmpty()) {
            statusMessage = State.WAITING_FOR_INPUT;
            isAddButtonEnabled = false;

            return isAddButtonEnabled;
        }

        try {
            Double.parseDouble(inputElem);
        } catch (Exception e) {
            statusMessage = State.INVALID_FORMAT;
            isAddButtonEnabled = false;

            logger.log(inputElem + " input value is incorrect. Should be Double");

            return isAddButtonEnabled;
        }

        statusMessage = State.READY_TO_ADD;
        isAddButtonEnabled = true;

        return isAddButtonEnabled;
    }

    private void changeButtonsEnabling() {
        if (queue.isEmpty()) {
            isRemoveButtonEnabled = false;
            isClearButtonEnabled = false;
        } else {
            isRemoveButtonEnabled = true;
            isClearButtonEnabled = true;
        }
    }
}
