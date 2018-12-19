package ru.unn.agile.lengthconverter.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import ru.unn.agile.lengthconverter.model.LengthUnit;

public class ViewModelTest {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canGetMeasureProperty() {
        ObjectProperty<ObservableList<LengthUnit>> unitsProperty
                = viewModel.unitsProperty();

        assertEquals(unitsProperty.get().get(0), LengthUnit.METERS);
    }

    @Test
    public void canInitViewModel() {
        viewModel = new ViewModel();

        assertNotEquals(viewModel, null);
    }

    @Test
    public void canGetMeasure() {
        ObservableList<LengthUnit> units
                = viewModel.getUnits();

        assertEquals(units.get(0), LengthUnit.METERS);
    }

    @Test
    public void createEmptyViewModel() {
        assertEquals("", viewModel.convertFromProperty().get());
        assertEquals(LengthUnit.METERS, viewModel.getUnitTo());
        assertEquals("", viewModel.getConvertTo());
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void canAddValueToConvert() {
        viewModel.convertFromProperty().set("0.0");

        viewModel.checkInputValues();

        assertEquals("0.0", viewModel.convertFromProperty().get());
    }

    @Test
    public void canGetConvertToValue() {
        viewModel.convertFromProperty().set("1000.0");

        viewModel.convert();

        assertEquals("1.0", viewModel.convertToProperty().get());
    }

    @Test
    public void canProcessBadInputFormat() {
        viewModel.convertFromProperty().set("ghf");

        viewModel.checkInputValues();

        assertEquals(Status.INCORRECT_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void setStatusWaitingWithEmptyString() {
        viewModel.convertFromProperty().set("");

        viewModel.checkInputValues();

        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }
}
