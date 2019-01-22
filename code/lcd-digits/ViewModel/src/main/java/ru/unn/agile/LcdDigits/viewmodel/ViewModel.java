package ru.unn.agile.LcdDigits.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import ru.unn.agile.LcdDigits.model.LcdDigit;

import java.util.ArrayList;
import java.util.List;


public class ViewModel {
    private final StringProperty digits = new SimpleStringProperty();
    private final StringProperty logs = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final BooleanProperty transferringDisabled = new SimpleBooleanProperty();

    private static final String LOG_PATTERN_TRANSFORM = LogMessages.TRANSFORM_WAS_PRESSED
            + "Number %s";
    private static final String LOG_PATTERN_EDIT = LogMessages.EDITING_FINISHED
            + "Input argument is: [%s]";

    private ILogger logger;
    private boolean isInputChanged;
    private List<ValueListener> valueChangedListeners;


    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter cannot be null");
        }
        this.logger = logger;
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);

        digits.set("");

        result.set("");
        logs.set("");
        status.set(Status.WAITING.toString());
        isInputChanged = true;

        bindingTransfer();
    }

    public ViewModel() {
        digits.set("");

        result.set("");
        logs.set("");
        status.set(Status.WAITING.toString());
        isInputChanged = true;

        bindingTransfer();
    }

    private void bindingTransfer() {
        BooleanBinding couldTransfer = new BooleanBinding() {
            {
                super.bind(digits);
            }

            @Override
            protected boolean computeValue() {
                return getEntranceStatus() == Status.READY;
            }
        };
        transferringDisabled.bind(couldTransfer.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() {
            {
                add(digits);
            }
        };
        additionListener(fields);
    }

    private void additionListener(final List<StringProperty> stringFields) {
        valueChangedListeners = new ArrayList<>();

        for (StringProperty f : stringFields) {
            final ValueListener valueListener = new ValueListener();
            f.addListener(valueListener);

            valueChangedListeners.add(valueListener);
        }
    }

    public final String getLogPatternCalculate() {
        return String.format(LOG_PATTERN_TRANSFORM, digits.get());
    }

    public final String getLogPatternEdit() {
        return String.format(LOG_PATTERN_EDIT, digits.get());
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    private Status getEntranceStatus() {
        Status entranceStatus = Status.READY;
        if (digits.get().isEmpty()) {
            entranceStatus = Status.WAITING;
        }
        try {
            if (!digits.get().isEmpty()) {
                Integer.parseInt(digits.get());
            }
        } catch (NumberFormatException nfe) {
            entranceStatus = Status.BAD_FORMAT;
        }
        return entranceStatus;
    }

    public void transformLcdDigits() {
        logger.log(createLogMsg());
        if (transferringDisabled.get()) {
            return;
        }
        int input = Integer.parseInt(digits.get());
        LcdDigit numberLcd = new LcdDigit(input);

        result.set(numberLcd.getLcd());
        status.set(Status.SUCCESS.toString());
    }

    private String createLogMsg() {
        return getLogPatternCalculate();
    }

    public void onFocusChanged(final Boolean oldValue, final Boolean newValue) {
        if (!oldValue && newValue) {
            return;
        }

        for (ValueListener valueListener : valueChangedListeners) {
            if (valueListener.isChanged()) {
                String message = String.format(LOG_PATTERN_EDIT, digits.get());
                logger.log(message);
                updateLogs();

                valueListener.cache();
                break;
            }
        }
    }

    private void logInputParameters() {
        if (!isInputChanged) {
            return;
        }
        logger.log(editingFinishedLogMessage());
        isInputChanged = false;
    }

    private String editingFinishedLogMessage() {
        return getLogPatternEdit();
    }

    public void focusLost() {
        logInputParameters();
    }

    public StringProperty digitsProperty() {
        return digits;
    }

    public void setDigits(final String digits) {
        this.digits.set(digits);
        isInputChanged = true;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty logsProperty() {
        return logs;
    }

    public BooleanProperty transferringDisabledProperty() {
        return transferringDisabled;
    }

    public final String getResult() {
        return result.get();
    }

    public final String getStatus() {
        return status.get();
    }

    public final String getDigits() {
        return digits.get();
    }

    public final String getLogs() {
        return logs.get();
    }

    public final boolean isTransferringDisabled() {
        return transferringDisabled.get();
    }

    private void updateLogs() {
        List<String> allLog = logger.getLog();
        StringBuilder l = new StringBuilder("");

        for (String log : allLog) {
            l.append(log).append("\n");
        }

        logs.set(l.toString());
    }

    private class ValueListener implements ChangeListener<String> {
        private String lastSeenValue = new String("");
        private String acuteValue = new String("");
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            if (oldValue.equals(newValue)) {
                return;
            }
            status.set(getEntranceStatus().toString());
            acuteValue = newValue;
        }

        public boolean isChanged() {
            return !lastSeenValue.equals(acuteValue);
        }

        public void cache() {
            lastSeenValue = acuteValue;
        }
    }
}

enum Status {
    WAITING("Please enter a number"),
    READY("Press 'Transform'"),
    BAD_FORMAT("Wrong format"),
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
    public static final String TRANSFORM_WAS_PRESSED = "Show: ";
    public static final String EDITING_FINISHED = "Updated input: ";

    private LogMessages() { }
}
