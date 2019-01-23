package ru.unn.agile.ConverterTemperatures.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.ConverterTemperatures.model.TemperaturesUnit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ViewModelTest {
    private ViewModel viewModel;

    @Before
    public void initViewModel() {
        viewModel = new ViewModel(new FakeLogger());
    }

    @After
    public void nullViewModel() {
        viewModel = null;
    }

    protected void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Test
    public void canGetScalesProperty() {
        ObjectProperty<ObservableList<TemperaturesUnit>> unitsProperty
                = viewModel.unitsProperty();

        assertEquals(unitsProperty.get().get(0), TemperaturesUnit.FAHRENHEIT);
    }


    @Test
    public void canInitViewModel() {
        viewModel = new ViewModel();

        assertNotEquals(viewModel, null);
    }

    @Test
    public void canGetScales() {
        ObservableList<TemperaturesUnit> units
                = viewModel.getUnits();

        assertEquals(units.get(0), TemperaturesUnit.FAHRENHEIT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canCreateViewModelWithNullLogger() {
        new ViewModel(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canSetNullLogger() {
        viewModel.setLogger(null);
    }

    @Test
    public void canGetLog() {
        String log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void canGetLogProperty() {
        String log = viewModel.logProperty().get();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logIsInit() {
        List<String> log = viewModel.getLogList();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logMessageCanConvertToKelvin() {
        viewModel.convertFromProperty().set("20.0");
        viewModel.unitProperty().setValue(TemperaturesUnit.KELVIN);

        viewModel.convert();

        String message = viewModel.getLogList().get(0);
        String expectedMessage = String.format(LogMessage.CONVERT_WAS_PRESSED,
                viewModel.getConvertFrom(), ViewModel.CELSIUS_SYMBOL,
                viewModel.getConvertTo(), TemperaturesUnit.KELVIN);
        assertTrue(message.contains(expectedMessage));
    }

    @Test
    public void logMessageGetMultiStringsLog() {
        viewModel.convertFromProperty().set("-300.0");
        viewModel.unitProperty().setValue(TemperaturesUnit.KELVIN);
        viewModel.convert();

        viewModel.convertFromProperty().set("20.0");
        viewModel.unitProperty().setValue(TemperaturesUnit.FAHRENHEIT);
        viewModel.convert();

        String message = viewModel.getLogList().get(0);
        String expectedMessage = String.format(LogMessage.VALUE_FROM_IS_NOT_CORRECT, "-300.0");
        assertTrue(message.contains(expectedMessage));

        String message2 = viewModel.getLogList().get(1);
        String expectedMessage2 = String.format(LogMessage.CONVERT_WAS_PRESSED,
                "20.0", ViewModel.CELSIUS_SYMBOL, viewModel.getConvertTo(),
                TemperaturesUnit.FAHRENHEIT);
        assertTrue(message2.contains(expectedMessage2));
    }

    @Test
    public void logMessageGetNotCorrectSymbolValue() {
        viewModel.convertFromProperty().set("sdf");
        viewModel.unitProperty().setValue(TemperaturesUnit.FAHRENHEIT);

        viewModel.convert();

        String message = viewModel.getLogList().get(0);
        String expectedMessage = String.format(LogMessage.VALUE_FROM_IS_NOT_CORRECT,
                viewModel.getConvertFrom());
        assertTrue(message.contains(expectedMessage));
    }

    @Test
    public void createEmptyViewModel() {
        assertEquals("", viewModel.convertFromProperty().get());
        assertEquals(TemperaturesUnit.FAHRENHEIT, viewModel.getUnit());
        assertEquals("", viewModel.getConvertTo());
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void canAddValueToConvert() {
        viewModel.convertFromProperty().set("10.0");

        viewModel.checkInputValues();

        assertEquals("10.0", viewModel.convertFromProperty().get());
    }

    @Test
    public void canGetConvertToValue() {
        viewModel.convertFromProperty().set("10.0");

        viewModel.convert();

        assertEquals("50.0", viewModel.convertToProperty().get());
    }

    @Test
    public void canProcessBadInputFormat() {
        viewModel.convertFromProperty().set("a");

        viewModel.checkInputValues();

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void canConvertToFahrenheit() {
        viewModel.convertFromProperty().set("20.0");
        viewModel.unitProperty().setValue(TemperaturesUnit.FAHRENHEIT);

        viewModel.convert();

        assertEquals("68.0", viewModel.getConvertTo());
        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void canConvertToKelvin() {
        viewModel.convertFromProperty().set("20.0");
        viewModel.unitProperty().setValue(TemperaturesUnit.KELVIN);

        viewModel.convert();

        assertEquals("293.15", viewModel.getConvertTo());
        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void canConvertToNewton() {
        viewModel.convertFromProperty().set("20.0");
        viewModel.unitProperty().setValue(TemperaturesUnit.NEWTON);

        viewModel.convert();

        assertEquals("6.6000000000000005", viewModel.getConvertTo());
        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void getExceptionFromConvert() {
        viewModel.convertFromProperty().set("-300.0");
        viewModel.unitProperty().setValue(TemperaturesUnit.FAHRENHEIT);

        viewModel.convert();

        assertEquals(Status.ERROR.toString(), viewModel.getStatus());
    }

    @Test
    public void getCorrectStatusWithNullInput() {
        viewModel.convertFromProperty().set("");
        viewModel.unitProperty().setValue(TemperaturesUnit.KELVIN);

        viewModel.convert();

        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void fahrenheitIsSetByDefault() {
        assertEquals(TemperaturesUnit.FAHRENHEIT, viewModel.getUnit());
    }

    @Test
    public void getCorrectStatusWithEmptyFields() {
        viewModel.convert();

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void getCorrectStatusWhenFillFields() {
        viewModel.convertFromProperty().set("20.0");

        viewModel.checkInputValues();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void testConstructorIsPrivate()
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<LogMessage> constructor = LogMessage.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
