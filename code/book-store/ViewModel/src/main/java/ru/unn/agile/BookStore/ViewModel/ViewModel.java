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
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    // FXML needs default c-tor for binding
    public ViewModel() {
        books1.set("");
        books2.set("");
        books3.set("");
        books4.set("");
        books5.set("");
        result.set("");
        status.set(Status.WAITING.toString());

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

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void calculate() {
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

    public StringProperty books1Property() {
        return books1;
    }
    public final String getBooks1() {
        return books1.get();
    }
    public StringProperty books2Property() {
        return books2;
    }
    public final String getBooks2() {
        return books2.get();
    }
    public StringProperty books3Property() {
        return books3;
    }
    public final String getBooks3() {
        return books3.get();
    }
    public StringProperty books4Property() {
        return books4;
    }
    public final String getBooks4() {
        return books4.get();
    }
    public StringProperty books5Property() {
        return books5;
    }
    public final String getBooks5() {
        return books5.get();
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

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue,
                            final String newValue) {
            status.set(getStatusForInput().toString());
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
