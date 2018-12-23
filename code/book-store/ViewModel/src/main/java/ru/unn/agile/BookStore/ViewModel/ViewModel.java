package ru.unn.agile.BookStore.ViewModel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.BookStore.Model.ShoppingBasket;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty books1 = new SimpleStringProperty();
    private final StringProperty books2 = new SimpleStringProperty();
    private final StringProperty books3 = new SimpleStringProperty();
    private final StringProperty books4 = new SimpleStringProperty();
    private final StringProperty books5 = new SimpleStringProperty();

    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();
    private final StringProperty logs = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private static final String LOG_PATTERN_CALCULATE = LogMessages.CALCULATE_WAS_PRESSED
            + "Books" + ": #1 = %s" + "; #2 = %s" + "; #3 = %s" + "; #4 = %s" + "; #5 = %s";
    private static final String LOG_PATTERN_EDIT = LogMessages.EDITING_FINISHED
            + "Input arguments are: [%s; %s; %s; %s]";

    private ILogger logger;
    private boolean isInputChanged;
    private List<ValueListener> valueChangedListeners;

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    // FXML needs default c-tor for binding
    public ViewModel(final ILogger logger) {
        setLogger(logger);

        books1.set("");
        books2.set("");
        books3.set("");
        books4.set("");
        books5.set("");
        result.set("");
        status.set(Status.WAITING.toString());
        isInputChanged = true;

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(books1, books2, books3, books4, books5);
            }
            @Override
            protected boolean computeValue() {
                return getStatusForInput() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        // Add listeners to the input text fields
        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(books1);
            add(books2);
            add(books3);
            add(books4);
            add(books5);
        } };
        valueChangedListeners = new ArrayList<>();
        for (StringProperty field : fields) {
            final ValueListener listener = new ValueListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }

    }

    public final String getLogPatternCalculate() {
        return String.format(LOG_PATTERN_CALCULATE, books1.get(), books2.get(),
                books3.get(), books4.get(), books5.get());
    }

    public final String getLogPatternEdit() {
        return String.format(LOG_PATTERN_EDIT, books1.get(), books2.get(),
                books3.get(), books4.get(), books5.get());
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    public void calculate() {
        logger.log(createLogMessage());

        if (calculationDisabled.get()) {
            return;
        }
        ShoppingBasket basket = new ShoppingBasket();
        basket.assignmentShoppingBasket(new int[]{Integer.parseInt(books1.get()),
                                                  Integer.parseInt(books2.get()),
                                                  Integer.parseInt(books3.get()),
                                                  Integer.parseInt(books4.get()),
                                                  Integer.parseInt(books5.get())});
        result.set((new Double(basket.getFinalPriceOfShoppingBasket())).toString());
        status.set(Status.SUCCESS.toString());
    }

    private String createLogMessage() {
        return getLogPatternCalculate();
    }

    public void onFocusChanged(final Boolean oldValue, final Boolean newValue) {
        if (!oldValue && newValue) {
            return;
        }

        for (ValueListener listener : valueChangedListeners) {
            if (listener.isChanged()) {
                String message = String.format(LOG_PATTERN_EDIT, books1.get(), books2.get(),
                        books3.get(), books4.get(), books5.get());
                logger.log(message);
                updateLogs();

                listener.cache();
                break;
            }
        }
    }


    private void logInputParams() {
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
        logInputParams();
    }

    public StringProperty books1Property() {
        return books1;
    }
    public final String getBooks1() {
        return books1.get();
    }
    public void setBooks1(final String books) {
        books1.set(books);
        isInputChanged = true;
    }

    public StringProperty books2Property() {
        return books2;
    }
    public final String getBooks2() {
        return books2.get();
    }
    public void setBooks2(final String books) {
        books2.set(books);
        isInputChanged = true;
    }

    public StringProperty books3Property() {
        return books3;
    }
    public final String getBooks3() {
        return books3.get();
    }
    public void setBooks3(final String books) {
        books3.set(books);
        isInputChanged = true;
    }

    public StringProperty books4Property() {
        return books4;
    }
    public final String getBooks4() {
        return books4.get();
    }
    public void setBooks4(final String books) {
        books4.set(books);
        isInputChanged = true;
    }

    public StringProperty books5Property() {
        return books5;
    }
    public final String getBooks5() {
        return books5.get();
    }
    public void setBooks5(final String books) {
        books5.set(books);
        isInputChanged = true;
    }

    public final String getResult() {
        return result.get();
    }

    public final String getStatus() {
        return status.get();
    }

    public final boolean isCalculationDisabled() {
        return calculationDisabled.get();
    }

    private Status getStatusForInput() {
        Status inputStatus = Status.READY;
        if (books1.get().isEmpty() || books2.get().isEmpty()
         || books3.get().isEmpty() || books4.get().isEmpty() || books5.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!books1.get().isEmpty()) {
                Integer.parseInt(books1.get());
            }
            if (!books2.get().isEmpty()) {
                Integer.parseInt(books2.get());
            }
            if (!books3.get().isEmpty()) {
                Integer.parseInt(books3.get());
            }
            if (!books4.get().isEmpty()) {
                Integer.parseInt(books4.get());
            }
            if (!books5.get().isEmpty()) {
                Integer.parseInt(books5.get());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }
        return inputStatus;
    }

    private void updateLogs() {
        List<String> wholeLog = logger.getLog();
        StringBuilder line = new StringBuilder("");
        for (String log : wholeLog) {
            line.append(log).append("\n");
        }
        logs.set(line.toString());
    }

    private class ValueListener implements ChangeListener<String> {
        private String lastValue = new String("");
        private String actualValue = new String("");
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            if (oldValue.equals(newValue)) {
                return;
            }
            status.set(getStatusForInput().toString());
            actualValue = newValue;
        }
        public boolean isChanged() {
            return !lastValue.equals(actualValue);
        }
        public void cache() {
            lastValue = actualValue;
        }
    }
}

enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Calculate' or Enter"),
    BAD_FORMAT("Bad format"),
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
    public static final String CALCULATE_WAS_PRESSED = "Calculate: ";
    public static final String EDITING_FINISHED = "Updated input: ";

    private LogMessages() { }
}
