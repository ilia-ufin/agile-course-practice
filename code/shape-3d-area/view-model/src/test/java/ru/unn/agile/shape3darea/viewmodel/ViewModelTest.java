package ru.unn.agile.shape3darea.viewmodel;

import javafx.beans.property.ObjectProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.shape3darea.model.ShapeType;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ViewModelTest {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        if (viewModel == null) {
            viewModel = new ViewModel(new FakeLogger());
        }
    }

    public void setExternalViewModel(final ViewModel externalViewModel) {
        this.viewModel = externalViewModel;
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void whenCreateViewModelThenSetDefaultValues() {
        assertEquals(ShapeType.SQUARE_PYRAMID, viewModel.selectedShapeProperty().get());

        assertEquals(2, viewModel.getParameters().size());

        assertEquals(double.class, viewModel.getParameters().get(0).getType());
        assertEquals("squareSide", viewModel.getParameters().get(0).getName());
        assertEquals("0", viewModel.getParameters().get(0).valueProperty().get());

        assertEquals(double.class, viewModel.getParameters().get(1).getType());
        assertEquals("triangleSide", viewModel.getParameters().get(1).getName());
        assertEquals("0", viewModel.getParameters().get(1).valueProperty().get());

        assertEquals("", viewModel.resultProperty().get());
        assertEquals("Ready", viewModel.statusProperty().get());
    }

    @Test
    public void whenCalculateWithEmptyFieldsThenShowError() {
        viewModel.calculate();

        assertEquals("", viewModel.resultProperty().get());
        assertEquals(Status.INVALID_INPUT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void whenCalculateWithInvalidInputThenShowError() {
        viewModel.getParameters().get(0).valueProperty().set("1.2.3");
        viewModel.getParameters().get(0).valueProperty().set("52a");
        viewModel.calculate();

        assertEquals("", viewModel.resultProperty().get());
        assertEquals(Status.INVALID_INPUT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void whenCalculateSquarePyramidThenShowResult() {
        viewModel.getParameters().get(0).valueProperty().set("1");
        viewModel.getParameters().get(1).valueProperty().set("1");
        viewModel.calculate();

        assertEquals(Status.OK.toString(), viewModel.statusProperty().get());
        assertEquals(String.valueOf(1 + 2 * Math.sqrt(0.75)), viewModel.resultProperty().get());
    }

    @Test
    public void whenChangeShapeThenUpdateParameters() {
        viewModel.selectedShapeProperty().set(ShapeType.SPHERE);

        assertEquals(ShapeType.SPHERE, viewModel.selectedShapeProperty().get());

        assertEquals(1, viewModel.getParameters().size());

        assertEquals(double.class, viewModel.getParameters().get(0).getType());
        assertEquals("radius", viewModel.getParameters().get(0).getName());
        assertEquals("0", viewModel.getParameters().get(0).valueProperty().get());
    }

    @Test
    public void whenCalculateSphereThenShowResult() {
        viewModel.selectedShapeProperty().set(ShapeType.SPHERE);
        viewModel.getParameters().get(0).valueProperty().set("1");
        viewModel.calculate();

        assertEquals(Status.OK.toString(), viewModel.statusProperty().get());
        assertEquals("12.566370614359172", viewModel.resultProperty().get());
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotSetNullLogger() {
        viewModel.setLogger(null);
    }

    @Test
    public void checkLogIsEmptyAfterViewModelCreation() {
        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void checkLogSizeWhenCallCalculate() {
        viewModel.calculate();
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void checkLogValueWhenCallCalculate() {
        viewModel.calculate();
        String log = viewModel.getLog().get(0);
        assertTrue(log.matches("(.*)" + LogMessages.CALCULATE_WAS_PRESSED + "*"));
    }

    @Test
    public void checkLogSizeWhenSwitchToSphere() {
        viewModel.selectedShapeProperty().set(ShapeType.SPHERE);
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void checkLogValueWhenSwitchToSphere() {
        viewModel.selectedShapeProperty().set(ShapeType.SPHERE);
        String log = viewModel.getLog().get(0);
        assertTrue(log.matches("(.*)" + LogMessages.SHAPE_WAS_CHANGED + ShapeType.SPHERE));
    }

    @Test
    public void checkLogIsEmptyWhenSwitchToSquarePyramid() {
        viewModel.selectedShapeProperty().set(ShapeType.SQUARE_PYRAMID);
        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void checkLogSizeWhenSwitchToSphereAndBack() {
        viewModel.selectedShapeProperty().set(ShapeType.SPHERE);
        viewModel.selectedShapeProperty().set(ShapeType.SQUARE_PYRAMID);
        assertEquals(2, viewModel.getLog().size());
    }

    @Test
    public void checkLogValuesWhenSwitchToSphereAndBack() {
        ObjectProperty<ShapeType> selectedShape = viewModel.selectedShapeProperty();

        selectedShape.set(ShapeType.SPHERE);
        selectedShape.set(ShapeType.SQUARE_PYRAMID);
        List<String> log = viewModel.getLog();

        assertTrue(log.get(0).matches("(.*)" + LogMessages.SHAPE_WAS_CHANGED + ShapeType.SPHERE));
        assertTrue(log.get(1).matches(
                "(.*)" + LogMessages.SHAPE_WAS_CHANGED + ShapeType.SQUARE_PYRAMID));
    }

    @Test
    public void checkLogSizeWhenChangeParameter() {
        viewModel.getParameters().get(0).valueProperty().set("1");
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void checkLogValueWhenChangeParameter() {
        ShapeParameter parameter = viewModel.getParameters().get(0);
        String oldValue = parameter.valueProperty().get();
        String newValue = "2.345";
        String name = parameter.getName();

        parameter.valueProperty().set(newValue);
        String message = viewModel.getLog().get(0);

        assertTrue(message.contains(
                String.format(LogMessages.PARAMETER_WAS_CHANGED, name, oldValue, newValue)));
    }

    @Test
    public void canSwitchToNewLogger() {
        ILogger newLogger = new FakeLogger();
        newLogger.log("First message");
        newLogger.log("Second message");

        viewModel.setLogger(newLogger);
        assertEquals(2, viewModel.getLog().size());
    }

    @Test
    public void canGetDefaultLogger() {
        ViewModel defaultViewModel = new ViewModel();
        assertEquals(0, defaultViewModel.getLog().size());
    }

    @Test
    public void checkInitialStatus() {
        assertEquals(Status.OK.toString(), viewModel.getStatus());
    }

    @Test
    public void checkInitialResult() {
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void checkInitialLogRepresentationProperty() {
        assertEquals("", viewModel.logRepresentationProperty().get());
    }

    @Test
    public void checkInitialLogRepresentation() {
        assertEquals("", viewModel.getLogRepresentation());
    }
}
