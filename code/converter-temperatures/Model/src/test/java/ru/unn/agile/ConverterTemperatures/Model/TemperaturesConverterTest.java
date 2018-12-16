package ru.unn.agile.ConverterTemperatures.Model;

import org.junit.Test;
import ru.unn.agile.ConverterTemperatures.model.TemperaturesConverter;
import ru.unn.agile.ConverterTemperatures.model.TemperaturesUnit;
import ru.unn.agile.ConverterTemperatures.model.TemperaturesConverterExceptions;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;
import static org.junit.Assert.*;

public class TemperaturesConverterTest {
    private static final double EPSILON = 0.000001;

    @Test
    public void canGetCoefForFahrenheit() {
        TemperaturesUnit tempUnit = TemperaturesUnit.FAHRENHEIT;
        double coefExpected = 1.8;

        double coefActual = tempUnit.getCoef();

        assertEquals(coefExpected, coefActual, EPSILON);
    }

    @Test
    public void canGetOffsetForFahrenheit() {
        TemperaturesUnit tempUnit = TemperaturesUnit.FAHRENHEIT;
        double offsetExpected = 32;

        double offsetActual = tempUnit.getOffset();

        assertEquals(offsetExpected, offsetActual, EPSILON);
    }

    @Test
    public void canConvertZeroToFahrenheit() {
        TemperaturesUnit tempUnit = TemperaturesUnit.FAHRENHEIT;
        double tempBefore = 0.0;
        double expectedTempAfter = 32.0;

        double calculatingTempAfter = TemperaturesConverter.convert(tempBefore, tempUnit);

        assertEquals(expectedTempAfter, calculatingTempAfter, EPSILON);
    }

    @Test
    public void canConvert100ToFahrenheit() {
        TemperaturesUnit tempUnit = TemperaturesUnit.FAHRENHEIT;
        double tempBefore = 100.0;
        double expectedTempAfter = 212;

        double calculatingTempAfter = TemperaturesConverter.convert(tempBefore, tempUnit);

        assertEquals(expectedTempAfter, calculatingTempAfter, EPSILON);
    }

    @Test (expected = TemperaturesConverterExceptions.class)
    public void canConvertAbsMinToFahrenheit() {
        TemperaturesUnit tempUnit = TemperaturesUnit.FAHRENHEIT;
        double tempBefore = -273.16;

        double calculatingTempAfter = TemperaturesConverter.convert(tempBefore, tempUnit);
    }

    @Test
    public void canGetCoefForKelvin() {
        TemperaturesUnit tempUnit = TemperaturesUnit.KELVIN;
        double coefExpected = 1;

        double coefActual = tempUnit.getCoef();

        assertEquals(coefExpected, coefActual, EPSILON);
    }

    @Test
    public void canGetOffsetForKelvin() {
        TemperaturesUnit tempUnit = TemperaturesUnit.KELVIN;
        double offsetExpected = 273.15;

        double offsetActual = tempUnit.getOffset();

        assertEquals(offsetExpected, offsetActual, EPSILON);
    }

    @Test
    public void canConvertZeroToKelvin() {
        TemperaturesUnit tempUnit = TemperaturesUnit.KELVIN;
        double tempBefore = 0.0;
        double expectedTempAfter = 273.15;

        double calculatingTempAfter = TemperaturesConverter.convert(tempBefore, tempUnit);

        assertEquals(expectedTempAfter, calculatingTempAfter, EPSILON);
    }

    @Test
    public void canConvert100ToKelvin() {
        TemperaturesUnit tempUnit = TemperaturesUnit.KELVIN;
        double tempBefore = 100.0;
        double expectedTempAfter = 373.15;

        double calculatingTempAfter = TemperaturesConverter.convert(tempBefore, tempUnit);

        assertEquals(expectedTempAfter, calculatingTempAfter, EPSILON);
    }

    @Test (expected = TemperaturesConverterExceptions.class)
    public void canConvertAbsMinToKelvin() {
        TemperaturesUnit tempUnit = TemperaturesUnit.KELVIN;
        double tempBefore = -273.16;

        double calculatingTempAfter = TemperaturesConverter.convert(tempBefore, tempUnit);
    }


    @Test
    public void canGetCoefForNewton() {
        TemperaturesUnit tempUnit = TemperaturesUnit.NEWTON;
        double coefExpected = 0.33;

        double coefActual = tempUnit.getCoef();

        assertEquals(coefExpected, coefActual, EPSILON);
    }

    @Test
    public void canGetOffsetForNewton() {
        TemperaturesUnit tempUnit = TemperaturesUnit.NEWTON;
        double offsetExpected = 0;

        double offsetActual = tempUnit.getOffset();

        assertEquals(offsetExpected, offsetActual, EPSILON);
    }

    @Test
    public void canConvertZeroToNewton() {
        TemperaturesUnit tempUnit = TemperaturesUnit.NEWTON;
        double tempBefore = 0.0;
        double expectedTempAfter = 0;

        double calculatingTempAfter = TemperaturesConverter.convert(tempBefore, tempUnit);

        assertEquals(expectedTempAfter, calculatingTempAfter, EPSILON);
    }

    @Test
    public void canConvert100ToNewton() {
        TemperaturesUnit tempUnit = TemperaturesUnit.NEWTON;
        double tempBefore = 100.0;
        double expectedTempAfter = 33;

        double calculatingTempAfter = TemperaturesConverter.convert(tempBefore, tempUnit);

        assertEquals(expectedTempAfter, calculatingTempAfter, EPSILON);
    }

    @Test (expected = TemperaturesConverterExceptions.class)
    public void canConvertAbsMinToNewton() {
        TemperaturesUnit tempUnit = TemperaturesUnit.NEWTON;
        double tempBefore = -273.16;

        double calculatingTempAfter = TemperaturesConverter.convert(tempBefore, tempUnit);
    }

    @Test
    public void testConstructorIsPrivate()
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<TemperaturesConverter> constructor;
        constructor = TemperaturesConverter.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
