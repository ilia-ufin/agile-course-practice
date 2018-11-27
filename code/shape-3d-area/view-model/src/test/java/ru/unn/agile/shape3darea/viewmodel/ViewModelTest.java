package ru.unn.agile.shape3darea.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.shape3darea.model.ShapeType;

import static org.junit.Assert.assertEquals;

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
}
