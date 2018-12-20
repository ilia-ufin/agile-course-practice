package ru.unn.agile.lengthconverter.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import ru.unn.agile.lengthconverter.model.LengthUnit;

import java.util.List;

public class ViewModelTest {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        FakeLogger fakeLogger = new FakeLogger();
        viewModel = new ViewModel(fakeLogger);
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
        ObservableList<LengthUnit> units = viewModel.getUnits();

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

        viewModel.checkReady();

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

        viewModel.checkReady();

        assertEquals(Status.INCORRECT_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void setStatusWaitingWithEmptyString() {
        viewModel.convertFromProperty().set("");

        viewModel.checkReady();

        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void notChangingStatusFromWaitingAfterConvertWithEmptyString() {
        viewModel.convertFromProperty().set("");

        viewModel.convert();

        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }
    @Test
    public void checkConvertWithNegativeValue() {
        viewModel.convertFromProperty().set("-100.0");

        viewModel.convert();

        assertEquals(Status.ERROR.toString(), viewModel.getStatus());
    }

    @Test
    public void canInitializeLog() {
        List<String> log = viewModel.getLogList();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logMessageCanConvertFromMToKM() {
        viewModel.convertFromProperty().set("20.0");
        viewModel.unitPropertyFrom().setValue(LengthUnit.METERS);
        viewModel.unitPropertyTo().setValue(LengthUnit.KILOMETERS);

        viewModel.convert();

        String message = viewModel.getLogList().get(0);
        String expectedMessage = String.format(LogMessages.CONVERT_IS_PRESSED,
                viewModel.getConvertFrom(), LengthUnit.METERS,
                viewModel.getConvertTo(), LengthUnit.KILOMETERS);
        assertTrue(message.contains(expectedMessage));
    }

    @Test
    public void logMessageSetIncorrectInputFormat() {
        viewModel.convertFromProperty().set("a");
        viewModel.unitPropertyFrom().setValue(LengthUnit.METERS);
        viewModel.unitPropertyTo().setValue(LengthUnit.MILLIMETERS);

        viewModel.convert();

        String message = viewModel.getLogList().get(0);
        String expectedMessage = String.format(LogMessages.INPUT_VALUE_IS_INCORRECT,
                viewModel.getConvertFrom());
        assertTrue(message.contains(expectedMessage));
    }

    @Test
    public void logMessageSetIncorrectInputValue() {
        viewModel.convertFromProperty().set("-20.0");
        viewModel.unitPropertyFrom().setValue(LengthUnit.METERS);
        viewModel.unitPropertyTo().setValue(LengthUnit.FEET);

        viewModel.convert();

        String message = viewModel.getLogList().get(0);
        String expectedMessage = String.format(LogMessages.INPUT_VALUE_IS_INCORRECT,
                viewModel.getConvertFrom());
        assertTrue(message.contains(expectedMessage));
    }
}
