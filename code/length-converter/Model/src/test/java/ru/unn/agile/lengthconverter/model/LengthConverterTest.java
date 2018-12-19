package ru.unn.agile.lengthconverter.model;

import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;
import static org.junit.Assert.*;

public class LengthConverterTest {
    private static final double EPSILON = 0.000001;

    @Test
    public void canConvertMetersToMillimeters() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.METERS, 5, LengthUnit.MILLIMETERS);
        assertEquals(5000, convertedValue, EPSILON);
    }

    @Test
    public void canConvertMetersToKilometers() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.METERS, 5, LengthUnit.KILOMETERS);
        assertEquals(0.005, convertedValue, EPSILON);
    }

    @Test
    public void canConvertMetersToMeters() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.METERS, 5, LengthUnit.METERS);
        assertEquals(5, convertedValue, EPSILON);
    }

    @Test
    public void canConvertMetersToFeet() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.METERS, 5, LengthUnit.FEET);
        assertEquals(16.4042, convertedValue, EPSILON);
    }

    @Test
    public void canConvertMillimetersToMeters() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.MILLIMETERS, 5, LengthUnit.METERS);
        assertEquals(0.005, convertedValue, EPSILON);
    }

    @Test
    public void canConvertMillimetersToKilometers() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.MILLIMETERS, 5, LengthUnit.KILOMETERS);
        assertEquals(0.000005, convertedValue, EPSILON);
    }

    @Test
    public void canConvertMillimetersToFeet() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.MILLIMETERS, 5, LengthUnit.FEET);
        assertEquals(0.0164042, convertedValue, EPSILON);
    }

    @Test
    public void canConvertMillimetersToMillimeters() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.MILLIMETERS, 5, LengthUnit.MILLIMETERS);
        assertEquals(5, convertedValue, EPSILON);
    }

    @Test
    public void canConvertKilometersToMeters() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.KILOMETERS, 5, LengthUnit.METERS);
        assertEquals(5000, convertedValue, EPSILON);
    }

    @Test
    public void canConvertKilometersToMillimeters() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.KILOMETERS, 5, LengthUnit.MILLIMETERS);
        assertEquals(5000000, convertedValue, EPSILON);
    }

    @Test
    public void canConvertKilometersToFeet() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.KILOMETERS, 5, LengthUnit.FEET);
        assertEquals(16404.19947506, convertedValue, EPSILON);
    }

    @Test
    public void canConvertKilometersToKilometers() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.KILOMETERS, 5, LengthUnit.KILOMETERS);
        assertEquals(5, convertedValue, EPSILON);
    }

    @Test
    public void canConvertFeetToMeters() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.FEET, 5, LengthUnit.METERS);
        assertEquals(1.524, convertedValue, EPSILON);
    }

    @Test
    public void canConvertFeetToMillimeters() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.FEET, 5, LengthUnit.MILLIMETERS);
        assertEquals(1524, convertedValue, EPSILON);
    }

    @Test
    public void canConvertFeetToKilometers() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.FEET, 5, LengthUnit.KILOMETERS);
        assertEquals(0.001524, convertedValue, EPSILON);
    }

    @Test
    public void canConvertFeetToFeet() {
        double convertedValue =
                LengthConverte.convert(LengthUnit.FEET, 5, LengthUnit.FEET);
        assertEquals(5, convertedValue, EPSILON);
    }

    @Test
    public void testConstructorIsPrivate()
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<LengthConverte> constructor = LengthConverte.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
