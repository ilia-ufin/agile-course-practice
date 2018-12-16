package ru.unn.agile.ConverterTemperatures.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.ConverterTemperatures.model.*;

public class ViewModel {
    private final StringProperty convertFrom = new SimpleStringProperty();
    private final StringProperty convertTo = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<TemperaturesUnit>> scales =
        new SimpleObjectProperty<>(FXCollections.observableArrayList(TemperaturesUnit.values()));
    private final ObjectProperty<TemperaturesUnit> scale =
        new SimpleObjectProperty<TemperaturesUnit>();

    private final StringProperty status = new SimpleStringProperty();

    public ViewModel() {
        convertFrom.set("");
        convertTo.set("");
        scale.set(TemperaturesUnit.FAHRENHEIT);
        status.set(Status.READY.toString());
    }

    public StringProperty convertFromProperty() {
        return convertFrom;
    }
    public StringProperty convertToProperty() {
        return convertTo;
    }
    public final String getConvertTo() {
        return convertTo.get();
    }

    public ObjectProperty<ObservableList<TemperaturesUnit>> scalesProperty() {
        return scales;
    }
    public final ObservableList<TemperaturesUnit> getScales() {
        return scales.get();
    }

    public ObjectProperty<TemperaturesUnit> scaleProperty() {
        return scale;
    }
    public TemperaturesUnit getScale() {
        return scale.get();
    }

    public StringProperty statusProperty() {
        return status;
    }
    public final String getStatus() {
        return status.get();
    }

    public void checkInputValues() {
        if (convertFrom.get().isEmpty()) {
            status.set(Status.WAITING.toString());
        }
        try {
            if (!convertFrom.get().isEmpty()) {
                Double.parseDouble(convertFrom.get());
                status.set(Status.READY.toString());
            }
        } catch (NumberFormatException nfe) {
            status.set(Status.BAD_FORMAT.toString());
        }
    }

    public void convert() {
        checkInputValues();
        if (status.get() != Status.READY.toString()) {
            return;
        }

        try {
            double valueToConvert = Double.parseDouble(convertFrom.get());
            double result = TemperaturesConverter.convert(valueToConvert, scale.get());
            convertTo.set(String.valueOf(result));
            status.set(Status.SUCCESS.toString());
        } catch (TemperaturesConverterExceptions ex) {
            status.set(Status.ERROR.toString());
        }
    }
}

enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Convert' or Enter"),
    BAD_FORMAT("Bad format"),
    ERROR("Converting error: input value out of range"),
    SUCCESS("Success");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
