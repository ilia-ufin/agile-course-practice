package ru.unn.agile.shape3darea.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShapeParameterTest {
    private ShapeParameter parameter;
    private static final String PARAMETER_NAME = "TestParameter";

    @Before
    public void setUp() {
        parameter = new ShapeParameter(double.class, PARAMETER_NAME, new FakeLogger());
    }

    @After
    public void tearDown() {
        parameter = null;
    }

    @Test
    public void checkLogIsEmptyWhenParameterCreated() {
        assertTrue(parameter.getLog().isEmpty());
    }

    @Test
    public void checkLogSizeWhenSetParameter() {
        parameter.valueProperty().set("1.0");
        assertEquals(1,  parameter.getLog().size());
    }

    @Test
    public void checkLogValueWhenSetParameter() {
        parameter.valueProperty().set("1.0");
        String message = parameter.getLog().get(0);
        assertTrue(message.matches(LogMessages.PARAMETER_WAS_CHANGED
                + "(.*)" + PARAMETER_NAME + "(.*)"));
    }

    @Test
    public void checkLogParameterValuesFromDefaultToTwo() {
        String oldValue = parameter.valueProperty().get();
        String newValue = "2.0";
        parameter.valueProperty().set(newValue);

        String message = parameter.getLog().get(0);
        assertTrue(message.matches("(.*)from " + oldValue + " to " + newValue));
    }

    @Test
    public void checkLogParameterValuesFromTwoToThree() {
        String oldValue = "2.0";
        String newValue = "3.0";

        parameter.valueProperty().set(oldValue);
        parameter.valueProperty().set(newValue);

        String message = parameter.getLog().get(1);
        assertTrue(message.matches("(.*)from " + oldValue + " to " + newValue));
    }
}
