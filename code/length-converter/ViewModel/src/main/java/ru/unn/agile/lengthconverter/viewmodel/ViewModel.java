package ru.unn.agile.lengthconverter.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.lengthconverter.model.LengthUnit;

public class ViewModel {

    private final ObjectProperty<ObservableList<LengthUnit>> units =
            new SimpleObjectProperty<>(
                    FXCollections.observableArrayList(LengthUnit.values()));
    private final StringProperty convertFrom = new SimpleStringProperty();
    private final StringProperty convertTo = new SimpleStringProperty();
    private final ObjectProperty<LengthUnit> unit = new SimpleObjectProperty<LengthUnit>();
    private final StringProperty status = new SimpleStringProperty();

    public ViewModel() {
        init();
    }

    private void init() {
        convertFrom.set("");
        convertTo.set("");
        unit.set(LengthUnit.METERS);
        status.set(Status.READY.toString());

    }

    public ObjectProperty<ObservableList<LengthUnit>> unitsProperty() {
        return units;
    }

    public final ObservableList<LengthUnit> getUnits() {
        return units.get();
    }

    public StringProperty convertFromProperty() {
        return convertFrom;
    }

    public LengthUnit getUnit() {
        return unit.get();
    }

    public final String getConvertTo() {
        return convertTo.get();
    }

    public final String getStatus() {
        return status.get();
    }
}
