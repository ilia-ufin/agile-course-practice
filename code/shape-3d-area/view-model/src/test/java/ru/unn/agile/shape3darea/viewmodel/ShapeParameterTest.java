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
        String oldValue = parameter.valueProperty().get();
        String newValue = "1.0";
        String name = parameter.getName();

        parameter.valueProperty().set(newValue);
        String message = parameter.getLog().get(0);

        assertEquals(message,
                String.format(LogMessages.PARAMETER_WAS_CHANGED, name, oldValue, newValue));
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

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenSetNullLogger() {
        parameter.setLogger(null);
    }

    @Test
    public void checkSwitchToNewLogger() {
        ILogger logger = new FakeLogger();
        logger.log("Message");

        parameter.setLogger(logger);
        assertEquals(1, parameter.getLog().size());

        parameter.setLogger(new FakeLogger());
        assertEquals(0, parameter.getLog().size());
    }
}
