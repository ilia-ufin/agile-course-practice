package ru.unn.agile.lengthconverter.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void canGetScalesProperty() {
        ObjectProperty<ObservableList<LengthUnit>> unitsProperty
                = viewModel.unitsProperty();

        assertEquals(unitsProperty.get().get(0), LengthUnit.METERS);
    }



}
