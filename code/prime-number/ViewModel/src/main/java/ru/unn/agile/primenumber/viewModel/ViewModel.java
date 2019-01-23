package ru.unn.agile.primenumber.viewModel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.primenumber.infrastructure.Logger;
import ru.unn.agile.primenumber.infrastructure.LoggerFactory;
import ru.unn.agile.primenumber.model.PrimeNumber;

import java.util.List;
import java.util.stream.Collectors;

import static ru.unn.agile.primenumber.viewModel.LogMessage.*;

public class ViewModel {
    private final StringProperty leftBound = new SimpleStringProperty();
    private final StringProperty rightBound = new SimpleStringProperty();
    private final BooleanProperty search = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final StringProperty logs = new SimpleStringProperty();
    private Logger logger;

    public ViewModel() {
        leftBound.set("");
        rightBound.set("");
        result.set("");
        status.set("");
        logs.set("");
        BooleanBinding couldSearch = new BooleanBinding() {
            {
                super.bind(leftBound, rightBound);
            }

            @Override
            protected boolean computeValue() {
                return ViewModel.this.isValid();
            }
        };
        search.bind(couldSearch.not());
    }

    private void updateLogState() {
        List<String> listLogs = logger.getLogs();
        logs.set(listLogs.stream().collect(Collectors.joining("\n")));
    }

    private boolean isValid() {
        try {
            if (!rightBound.get().isEmpty()) {
                Double.parseDouble(rightBound.get());
            } else {
                return false;
            }
            if (!leftBound.get().isEmpty()) {
                Double.parseDouble(leftBound.get());
            } else {
                return false;
            }
        } catch (NumberFormatException nfe) {
            status.set("Input values are invalid");
            return false;
        }
        return true;
    }

    public String getLeftBound() {
        return leftBound.get();
    }

    public StringProperty leftBoundProperty() {
        return leftBound;
    }

    public void setLeftBound(final String leftBound) {
        this.leftBound.set(leftBound);
    }

    public String getRightBound() {
        return rightBound.get();
    }

    public StringProperty rightBoundProperty() {
        return rightBound;
    }

    public void setRightBound(final String rightBound) {
        this.rightBound.set(rightBound);
    }

    public boolean isSearchDisabled() {
        return search.get();
    }

    public String getResult() {
        return result.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(final String status) {
        this.status.set(status);
    }

    public BooleanProperty searchDisabledProperty() {
        return search;
    }

    public String getLogs() {
        return logs.get();
    }

    public final void setLogger(final LoggerFactory factory) {
        if (factory == null) {
            throw new IllegalArgumentException("Logger Factory parameter can't be null");
        }
        this.logger = factory.getLogger();
        logger.log(APPLICATION_STARTED.toString());
        updateLogState();
    }

    public void searchPrimeNumber() {
        try {
            logger.log(
                    String.format(CALCULATION_IS_STARTED_MESSAGE.toString(),
                            leftBound.getValue(),
                            rightBound.getValue()));

            updateLogState();
            double leftBoundDouble = Double.parseDouble(leftBound.getValue());
            double rightBoundDouble = Double.parseDouble(rightBound.getValue());
            List<Integer> primaryNumbers = PrimeNumber.findPrimeNumbersInSegment(
                    leftBoundDouble,
                    rightBoundDouble);

            if (primaryNumbers.isEmpty()) {
                result.set("Primary numbers are not found");
            } else {
                result.set(primaryNumbers
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")));
            }
            status.set("Success");

        } catch (IllegalArgumentException e) {
            status.set(e.getMessage());
        } finally {
            logger.log(
                    String.format(CALCULATION_IS_FINISHED_MESSAGE.toString(),
                            status.getValue(),
                            result.getValue()));

            updateLogState();
        }
    }

    public void onFocusChanged(final Boolean oldValue, final Boolean newValue) {
        if (!oldValue && newValue) {
            return;
        }
        logger.log(
                String.format(STATE_OF_OPERANDS_MESSAGE.toString(),
                        leftBound.getValue(),
                        rightBound.getValue()));

        updateLogState();
    }
}
