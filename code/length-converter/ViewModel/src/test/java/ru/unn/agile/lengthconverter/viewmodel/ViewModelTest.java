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
        viewModel = new ViewModel(new FakeLogger());
    }

    protected void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
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


    @Test(expected = IllegalArgumentException.class)
    public void initViewModelWithNullLogger() {
        ILogger logger = null;
        viewModel = new ViewModel(logger);
    }

    @Test
    public void canGetMeasure() {
        ObservableList<LengthUnit> units = viewModel.getUnits();

        assertEquals(units.get(0), LengthUnit.METERS);
    }

    @Test
    public void createEmptyViewModel() {
        assertEquals(LengthUnit.MILLIMETERS, viewModel.getUnitFrom());
        assertEquals("", viewModel.getConvertFrom());
        assertEquals(LengthUnit.METERS, viewModel.getUnitTo());
        assertEquals("", viewModel.getConvertTo());
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void canAddValueToConvert() {
        viewModel.convertFromProperty().set("0.0");

        viewModel.checkReady();

        assertEquals("0.0", viewModel.getConvertFrom());
    }

    @Test
    public void canGetConvertToValue() {
        viewModel.convertFromProperty().set("1000.0");

        viewModel.convert();

        assertEquals("1.0", viewModel.getConvertTo());
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
    public void canGetLog() {
        viewModel.convertFromProperty().set("a");

        viewModel.convert();

        String message = viewModel.getLog();
        String expectedMessage = String.format(ViewModel.LogMessages.INPUT_VALUE_IS_INCORRECT,
                viewModel.getConvertFrom());
        assertTrue(message.contains(expectedMessage));
    }

    @Test
    public void canGetLogProperty() {
        viewModel.convertFromProperty().set("a");

        viewModel.convert();

        String message = viewModel.logProperty().get();
        String expectedMessage = String.format(ViewModel.LogMessages.INPUT_VALUE_IS_INCORRECT,
                viewModel.getConvertFrom());
        assertTrue(message.contains(expectedMessage));
    }

    @Test
    public void logMessageCanConvertFromMToKM() {
        viewModel.convertFromProperty().set("20.0");
        viewModel.unitPropertyFrom().setValue(LengthUnit.METERS);
        viewModel.unitPropertyTo().setValue(LengthUnit.KILOMETERS);

        viewModel.convert();

        String message = viewModel.getLogList().get(0);
        String expectedMessage = String.format(ViewModel.LogMessages.CONVERT_IS_PRESSED,
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
        String expectedMessage = String.format(ViewModel.LogMessages.INPUT_VALUE_IS_INCORRECT,
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
        String expectedMessage = String.format(ViewModel.LogMessages.INPUT_VALUE_IS_INCORRECT,
                viewModel.getConvertFrom());
        assertTrue(message.contains(expectedMessage));
    }
}
