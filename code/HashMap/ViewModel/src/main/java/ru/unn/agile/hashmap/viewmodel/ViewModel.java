package ru.unn.agile.hashmap.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

package ru.unn.agile.HashMap.Model;

import java.util.Arrays;
import java.util.List;

public class ViewModel {

    private StringProperty IsEmptyStatus = new SimpleStringProperty();
    private StringProperty mapSize = new SimpleStringProperty();
	private StringProperty statusMessage = new SimpleStringProperty();
	
    private StringProperty addingInputKey = new SimpleStringProperty();
    private StringProperty addingInputValue = new SimpleStringProperty();
    private StringProperty gettingInputKey = new SimpleStringProperty();
    private StringProperty gettingInputValue = new SimpleStringProperty();
    private StringProperty removingInputKey = new SimpleStringProperty();   

	private BooleanProperty delButtonVisible = new SimpleBooleanProperty();
	private BooleanProperty getButtonVisible = new SimpleBooleanProperty();
	
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

    private HashMap map;

    public ViewModel() {
        this.map = new HashMap();
        IsEmptyStatus.set(STACK_IS_EMPTY);
        statusMessage.set(WAITING_FOR_INPUT);
    }

    public StringProperty IsEmptyStatusProperty() {
        return IsEmptyStatus;
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

    public String GetIsEmptyStatusProperty() {
        return IsEmptyStatus.get();
    }
	public String GetaddingInputKeyProperty() {
        return addingInputKey.get();
    }
	public String GetaddingInputValueProperty() {
        return addingInputValue.get();
    }
	public String GetgettingInputKeyProperty() {
        return gettingInputKey.get();
    }	
	public String GetgettingInputValueProperty() {
        return gettingInputValue.get();
    }
	public String GetremovingInputKeyProperty() {
        return removingInputKey.get();
    }
	public String GetmapSizeProperty() {
        return mapSize.get();
    }
    public String GetstatusMessageProperty() {
        return statusMessage.get();
    }

    public String setIsEmptyStatusProperty(final String addElem){
        return IsEmptyStatus.set(addElem);
    }
	public String setaddingInputKeyProperty(final String addElem){
        return addingInputKey.set(addElem);
    }
	public String setaddingInputValueProperty(final String addElem){
        return addingInputValue.set(addElem);
    }
	public String setgettingInputKeyProperty(final String addElem){
        return settingInputKey.set(addElem);
    }	
	public String setgettingInputValueProperty(final String addElem){
        return settingInputValue.set(addElem);
    }
	public String setremovingInputKeyProperty(final String addElem){
        return removingInputKey.set(addElem);
    }
	public String setmapSizeProperty(final String addElem){
        return mapSize.set(addElem);
    }
    public String setstatusMessageProperty(final String addElem){
        return statusMessage.set(addElem);
    }

    public void addElement() {
        try {
            String addingKey = GetaddingInputKeyProperty();
			String addingValue = GetaddingInputValueProperty();
            if (addingKey.isEmpty()) {
                statusMessage.set(INVALID_KEY_FORMAT);
				setgettingInputValueProperty("");
            }
			else if (addingValue.isEmpty()) {
                statusMessage.set(INVALID_VALUE_FORMAT);
				setgettingInputValueProperty("");
            }
			else {
                map.add(addingKey,addingValue);
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
            String gettingKey = GetgettingInputKeyProperty();
            if (gettingKey.isEmpty()) {
                statusMessage.set(INVALID_KEY_FORMAT);
				setgettingInputValueProperty("");
            }
			else if (map.containsKey(gettingKey)) {
				map.add(addingKey,addingValue);
				setgettingInputValueProperty(map.get(gettingKey));
                statusMessage.set(COMPLETE_GET);
            }
			else {		
                statusMessage.set(NOT_FOUND_BY_KEY);
				setgettingInputValueProperty("");
            }
        } catch (NumberFormatException e) {
            statusMessage.set(INVALID_FORMAT);
        }
    }
    public void delElement() {
        try {
            String removingKey = GetremovingInputKeyProperty();
            if (removingKey.isEmpty()) {
                statusMessage.set(INVALID_KEY_FORMAT);
				setgettingInputValueProperty("");
            }
			else if (map.containsKey(removingKey)) {
				map.remove(addingKey);
				setgettingInputValueProperty("");
                statusMessage.set(COMPLETE_DELETED);
                changeStackProperties();
            }
			else {		
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
        if (mapSize.isEmpty()) {
            IsEmptyStatus.set(IS_EMPTY);
        } else {
            IsEmptyStatus.set(IS_NOT_EMPTY);
        }
    }
}

enum Status {
    WAITING("Please set Vectors"),
    READY("Input data correct. Press calculate."),
    BAD_FORMAT("Bad data inserted"),
    SUCCESS("Success");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
