package ru.unn.agile.stack.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.stack.model.Stack;

public class ViewModel {
    public static final String WAITING_FOR_INPUT = "Waiting for new element";
    public static final String READY_TO_ADD = "Ready to add new element";
    public static final String INVALID_FORMAT = "Adding element invalid format";

    public static final String STACK_IS_EMPTY = "Stack is empty.";
    public static final String STACK_IS_NOT_EMPTY = "Stack is not empty.";
    public static final String NONE = "None";

    private Stack<Double> doubleStack;

    private StringProperty stackIsEmptyStatus = new SimpleStringProperty();
    private StringProperty stackSize = new SimpleStringProperty();
    private StringProperty stackTopElement = new SimpleStringProperty();
    private StringProperty stackPopElement = new SimpleStringProperty();
    private StringProperty addingElement = new SimpleStringProperty();
    private StringProperty statusMessage = new SimpleStringProperty();

    private BooleanProperty popButtonVisible = new SimpleBooleanProperty();

    public ViewModel() {
        doubleStack = new Stack<Double>();
        stackIsEmptyStatus.set(STACK_IS_EMPTY);
        stackSize.set("0");
        stackTopElement.set(NONE);
        stackPopElement.set(NONE);
        addingElement.set("");

        popButtonVisible.set(false);

        statusMessage.set(WAITING_FOR_INPUT);
    }

    public StringProperty stackIsEmptyStatusProperty() {
        return stackIsEmptyStatus;
    }

    public String getStackIsEmptyStatus() {
        return stackIsEmptyStatus.get();
    }

    public StringProperty stackSizeProperty() {
        return stackSize;
    }

    public String getStackSize() {
        return stackSize.get();
    }

    public StringProperty stackTopElementProperty() {
        return stackTopElement;
    }

    public String getStackTopElement() {
        return stackTopElement.get();
    }

    public StringProperty stackPopElementProperty() {
        return stackPopElement;
    }

    public String getStackPopElement() {
        return stackPopElement.get();
    }

    public StringProperty addingElementProperty() {
        return addingElement;
    }

    public String getAddingElement() {
        return addingElement.get();
    }

    public StringProperty statusMessageProperty() {
        return statusMessage;
    }

    public String getStatusMessage() {
        return statusMessage.get();
    }

    public boolean isPopButtonVisible() {
        return popButtonVisible.get();
    }

    public BooleanProperty popButtonVisibleProperty() {
        return popButtonVisible;
    }

    public void setAddingElem(final String addElem) {
        addingElement.set(addElem);
    }

    public void addElement() {
        try {
            String addingElement = getAddingElement();
            if (addingElement.isEmpty()) {
                statusMessage.set(WAITING_FOR_INPUT);
            } else {
                doubleStack.push(Double.parseDouble(addingElement));
                statusMessage.set(READY_TO_ADD);
                changeStackProperties();
            }
        } catch (NumberFormatException e) {
            statusMessage.set(INVALID_FORMAT);
        }
    }

    private void changeStackProperties() {
        int doubleStackSize = doubleStack.size();
        stackSize.set(Integer.toString(doubleStackSize));
        if (doubleStack.empty()) {
            stackIsEmptyStatus.set(STACK_IS_EMPTY);
            stackTopElement.set(NONE);
            popButtonVisible.set(false);
        } else {
            stackIsEmptyStatus.set(STACK_IS_NOT_EMPTY);
            stackTopElement.set(Double.toString(doubleStack.peek()));
            popButtonVisible.set(true);
        }
    }

    public void popElement() {
        if (!doubleStack.empty()) {
            stackPopElement.set(Double.toString(doubleStack.pop()));
            changeStackProperties();
        }
    }
}
