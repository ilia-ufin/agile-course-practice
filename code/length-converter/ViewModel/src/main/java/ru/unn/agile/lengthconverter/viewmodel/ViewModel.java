package ru.unn.agile.lengthconverter.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.lengthconverter.model.LengthUnit;

public class ViewModel {

    private final ObjectProperty<ObservableList<LengthUnit>> units =
            new SimpleObjectProperty<>(
                    FXCollections.observableArrayList(LengthUnit.values()));

    public ObjectProperty<ObservableList<LengthUnit>> unitsProperty() {
        return units;
    }
}
