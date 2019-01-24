package ru.unn.agile.modifideStack.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.modifideStack.model.ModifideStack;

public class ViewModel {
    public static final String WAITING_FOR_INPUT = "Waiting for new element";
    public static final String READY_TO_ADD = "Ready to add new element";
    public static final String INVALID_FORMAT = "Adding element invalid format";

    public static final String STACK_IS_EMPTY = "Modifide Stack is empty.";
    public static final String STACK_IS_NOT_EMPTY = "Modifide Stack is not empty.";
    public static final String NONE = "None";

    private ModifideStack integerModifideStack;

    private StringProperty modifideStackIsEmptyStatus = new SimpleStringProperty();
    private StringProperty modifideStackSize = new SimpleStringProperty();
    private StringProperty modifideStackTopElement = new SimpleStringProperty();
    private StringProperty modifideStackPopElement = new SimpleStringProperty();
    private StringProperty pushElement = new SimpleStringProperty();
    private StringProperty minElem = new SimpleStringProperty();
    private StringProperty statusMessageTxt = new SimpleStringProperty();

    private BooleanProperty popButtonVisible = new SimpleBooleanProperty();

    public ViewModel() {
        initDefaultValues();
    }

    private void initDefaultValues() {
        integerModifideStack = new ModifideStack();

        modifideStackIsEmptyStatus.set(STACK_IS_EMPTY);
        modifideStackSize.set("0");
        modifideStackTopElement.set(NONE);
        modifideStackPopElement.set(NONE);
        minElem.set(NONE);
        pushElement.set("");
        statusMessageTxt.set(WAITING_FOR_INPUT);

        popButtonVisible.set(false);
    }

    public StringProperty modifideStackEmptyStatusProperty() {
        return modifideStackIsEmptyStatus;
    }

    public String getModifideStackIsEmptyStatus() {
        return modifideStackIsEmptyStatus.get();
    }

    public StringProperty modifideStackSizeProperty() {
        return modifideStackSize;
    }

    public String getModifideStackSize() {
        return modifideStackSize.get();
    }

    public StringProperty modifideStackTopElementProperty() {
        return modifideStackTopElement;
    }

    public String getModifideStackTopElement() {
        return modifideStackTopElement.get();
    }

    public StringProperty modifideStackMinElemElementProperty() {
        return minElem;
    }

    public String getModifideStackMinElement() {
        return minElem.get();
    }

    public StringProperty modifideStackPopElementProperty() {
        return modifideStackPopElement;
    }

    public String getModifideStackPopElement() {
        return modifideStackPopElement.get();
    }

    public StringProperty pushElementProperty() {
        return pushElement;
    }

    public String getPushElement() {
        return pushElement.get();
    }

    public StringProperty statusMessageTxtProperty() {
        return statusMessageTxt;
    }

    public String getStatusMessageTxt() {
        return statusMessageTxt.get();
    }

    public boolean isVisiblePopButton() {
        return popButtonVisible.get();
    }

    public BooleanProperty popButtonVisibleProperty() {
        return popButtonVisible;
    }

    public void setPushElem(final String addElem) {
        pushElement.set(addElem);
    }

    public void pushingElement() {
        String pushElement = getPushElement();
        try {
            if (pushElement.isEmpty()) {
                statusMessageTxt.set(WAITING_FOR_INPUT);
            } else {
                Integer element = Integer.parseInt(pushElement);
                integerModifideStack.push(element);
                statusMessageTxt.set(READY_TO_ADD);
                changeModifideStackProperties();
            }
        } catch (NumberFormatException e) {
            statusMessageTxt.set(INVALID_FORMAT);
        }
    }

    private void changeModifideStackProperties() {
        int stackSize = integerModifideStack.getSize();
        modifideStackSize.set(String.valueOf(stackSize));
        if (integerModifideStack.isEmpty()) {
            modifideStackIsEmptyStatus.set(STACK_IS_EMPTY);
            modifideStackTopElement.set(NONE);
            minElem.set(NONE);
            popButtonVisible.set(false);
        } else {
            modifideStackIsEmptyStatus.set(STACK_IS_NOT_EMPTY);
            modifideStackTopElement.set(Integer.toString(integerModifideStack.peek()));
            minElem.set(Integer.toString(integerModifideStack.getMin()));
            popButtonVisible.set(true);
        }
    }

    public void popElement() {
        if (!integerModifideStack.isEmpty()) {
            modifideStackPopElement.set(Integer.toString(integerModifideStack.pop()));
            changeModifideStackProperties();
        }
    }
}
