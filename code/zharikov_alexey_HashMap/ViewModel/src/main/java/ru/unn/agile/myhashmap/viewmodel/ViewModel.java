package ru.unn.agile.myhashmap.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.myhashmap.model.MyHashMap;
//import java.util.*;

public class ViewModel {

    private MyHashMap map = new MyHashMap();
    private StringProperty isEmptyStatus = new SimpleStringProperty();
    private StringProperty mapSize = new SimpleStringProperty();
    private StringProperty statusMessage = new SimpleStringProperty();

    private StringProperty addingInputKey = new SimpleStringProperty();
    private StringProperty addingInputValue = new SimpleStringProperty();
    private StringProperty gettingInputKey = new SimpleStringProperty();
    private StringProperty gettingInputValue = new SimpleStringProperty();
    private StringProperty settingInputKey = new SimpleStringProperty();
    private StringProperty settingInputValue = new SimpleStringProperty();
    private StringProperty removingInputKey = new SimpleStringProperty();

    public static final String WAITING_FOR_INPUT = "Waiting for new element";
    public static final String NOT_FOUND_BY_KEY = "Element not found by key";
    public static final String READY_TO_ADD = "Ready to add new element";
    public static final String INVALID_FORMAT = "Adding element invalid format";
    public static final String INVALID_KEY_FORMAT = "Incorrect key or value";
    public static final String INVALID_VALUE_FORMAT = "Incorrect value or value";
    public static final String COMPLETE_DELETED = "Complete deleted";
    public static final String COMPLETE_GET = "Complete got";

    public static final String IS_EMPTY = "Map is empty.";
    public static final String IS_NOT_EMPTY = "Map is not empty.";
    public static final String NONE = "None";


    public ViewModel() {
        isEmptyStatus.set(IS_EMPTY);
        statusMessage.set(WAITING_FOR_INPUT);
    }

    public StringProperty isEmptyStatusProperty() {
        return isEmptyStatus;
    }

    public StringProperty addingInputKeyProperty() {
        return addingInputKey;
    }

    public StringProperty addingInputValueProperty() {
        return addingInputValue;
    }

    public StringProperty gettingInputKeyProperty() {
        return gettingInputKey;
    }

    public StringProperty gettingInputValueProperty() {
        return gettingInputValue;
    }

    public StringProperty removingInputKeyProperty() {
        return removingInputKey;
    }

    public StringProperty mapSizeProperty() {
        return mapSize;
    }

    public StringProperty statusMessageProperty() {
        return statusMessage;
    }

    public String getIsEmptyStatusProperty() {
        return isEmptyStatus.get();
    }

    public String getaddingInputKeyProperty() {
        return addingInputKey.get();
    }

    public String getaddingInputValueProperty() {
        return addingInputValue.get();
    }

    public String getgettingInputKeyProperty() {
        return gettingInputKey.get();
    }

    public String getgettingInputValueProperty() {
        return gettingInputValue.get();
    }

    public String getremovingInputKeyProperty() {
        return removingInputKey.get();
    }

    public String getmapSizeProperty() {
        return mapSize.get();
    }

    public String getstatusMessageProperty() {
        return statusMessage.get();
    }

    public void setIsEmptyStatusProperty(final String addElem) {
        isEmptyStatus.set(addElem);
    }

    public void setaddingInputKeyProperty(final String addElem) {
        addingInputKey.set(addElem);
    }

    public void setaddingInputValueProperty(final String addElem) {
        addingInputValue.set(addElem);
    }

    public void setgettingInputKeyProperty(final String addElem) {
        settingInputKey.set(addElem);
    }

    public void setgettingInputValueProperty(final String addElem) {
        settingInputValue.set(addElem);
    }

    public void setremovingInputKeyProperty(final String addElem) {
        removingInputKey.set(addElem);
    }

    public void setmapSizeProperty(final String addElem) {
        mapSize.set(addElem);
    }

    public void setstatusMessageProperty(final String addElem) {
        statusMessage.set(addElem);
    }

    public void addElement() {
        try {
            String addingKey = getaddingInputKeyProperty();
            String addingValue = getaddingInputValueProperty();
            if (addingKey.isEmpty()) {
                statusMessage.set(INVALID_KEY_FORMAT);
                setgettingInputValueProperty("");
            } else if (addingValue.isEmpty()) {
                statusMessage.set(INVALID_VALUE_FORMAT);
                setgettingInputValueProperty("");
            } else {
                map.add(addingKey, addingValue);
                setgettingInputValueProperty("");
                statusMessage.set(READY_TO_ADD);
                changeStackProperties();
            }
        } catch (NumberFormatException e) {
            statusMessage.set(INVALID_FORMAT);
        }
    }

    public void getElement() {
        try {
            String gettingKey = getgettingInputKeyProperty();
            if (gettingKey.isEmpty()) {
                statusMessage.set(INVALID_KEY_FORMAT);
                setgettingInputValueProperty("");
            } else if (map.containsKey(gettingKey)) {
                setgettingInputValueProperty(map.get(gettingKey).toString());
                statusMessage.set(COMPLETE_GET);
            } else {
                statusMessage.set(NOT_FOUND_BY_KEY);
                setgettingInputValueProperty("");
            }
        } catch (NumberFormatException e) {
            statusMessage.set(INVALID_FORMAT);
        }
    }

    public void delElement() {
        try {
            String removingKey = getremovingInputKeyProperty();
            if (removingKey.isEmpty()) {
                statusMessage.set(INVALID_KEY_FORMAT);
                setgettingInputValueProperty("");
            } else if (map.containsKey(removingKey)) {
                map.remove(removingKey);
                setgettingInputValueProperty("");
                statusMessage.set(COMPLETE_DELETED);
                changeStackProperties();
            } else {
                statusMessage.set(NOT_FOUND_BY_KEY);
                setgettingInputValueProperty("");
            }
        } catch (NumberFormatException e) {
            statusMessage.set(INVALID_FORMAT);
        }
    }

    private void changeStackProperties() {
        int intMapSize = map.size();
        mapSize.set(Integer.toString(intMapSize));
        if (mapSize.isBound()) {
            isEmptyStatus.set(IS_NOT_EMPTY);
        } else {
            isEmptyStatus.set(IS_EMPTY);
        }
    }
}
