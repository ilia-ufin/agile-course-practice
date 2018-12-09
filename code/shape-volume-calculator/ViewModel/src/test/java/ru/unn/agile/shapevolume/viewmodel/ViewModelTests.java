package ru.unn.agile.shapevolume.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    private void checkParametersNames(final Shape shape) {
        String[] parametersNames = ViewModel.SHAPE_TO_PARAMETERS_NAMES.get(shape);
        assertEquals(parametersNames[0], viewModel.firstArgumentNameProperty().get());
        assertEquals(parametersNames[1], viewModel.secondArgumentNameProperty().get());
        assertEquals(parametersNames[2], viewModel.thirdArgumentNameProperty().get());
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals(ViewModel.DEFAULT_VALUE, viewModel.firstArgumentNameProperty().get());
        assertEquals(ViewModel.DEFAULT_VALUE, viewModel.secondArgumentNameProperty().get());
        assertEquals(ViewModel.DEFAULT_VALUE, viewModel.thirdArgumentNameProperty().get());
    }

    @Test
    public void canSetDefaultNames() {
        checkParametersNames(Shape.UNKNOWN);
    }

    @Test
    public void canSetDefaultShape() {
        assertEquals(Shape.UNKNOWN, viewModel.currentShapeProperty().get());
    }

    @Test
    public void resultWaitingWhenThirdFieldIsEmpty() {
        viewModel.firstArgumentValueProperty().set("1");
        viewModel.secondArgumentValueProperty().set("2");
        assertEquals(Status.WAITING.toString(), viewModel.getResult());
    }

    @Test
    public void resultNoWaitingWhenFieldsAreFilled() {
        viewModel.firstArgumentValueProperty().set("1");
        viewModel.secondArgumentValueProperty().set("2");
        viewModel.thirdArgumentValueProperty().set("3");
        assertNotEquals(Status.WAITING.toString(), viewModel.getResult());
    }

    @Test
    public void checkInvalidSecondArgument() {
        viewModel.firstArgumentValueProperty().set("1");
        viewModel.secondArgumentValueProperty().set("test");
        viewModel.thirdArgumentValueProperty().set("3");
        assertEquals(Status.INVALID_ARGUMENTS.toString(), viewModel.getResult());
    }

    @Test
    public void checkInvalidFirstArgument() {
        viewModel.firstArgumentValueProperty().set("another test");
        viewModel.secondArgumentValueProperty().set("2");
        viewModel.thirdArgumentValueProperty().set("3");
        assertEquals(Status.INVALID_ARGUMENTS.toString(), viewModel.getResult());
    }

    @Test
    public void checkInvalidFirstAndThirdArguments() {
        viewModel.firstArgumentValueProperty().set("stub one");
        viewModel.secondArgumentValueProperty().set("2");
        viewModel.thirdArgumentValueProperty().set("stub 3");
        assertEquals(Status.INVALID_ARGUMENTS.toString(), viewModel.getResult());
    }

    @Test
    public void checkCubeWithTwoThreeFourLengthHasCorrectVolume() {
        viewModel.currentShapeProperty().set(Shape.CUBE);
        viewModel.firstArgumentValueProperty().set("2");
        viewModel.secondArgumentValueProperty().set("3");
        viewModel.thirdArgumentValueProperty().set("4");
        assertEquals("24.000", viewModel.getResult());
    }

    @Test
    public void checkCubeWithThreeFourtFiveLengthHasCorrectVolume() {
        viewModel.currentShapeProperty().set(Shape.CUBE);
        viewModel.firstArgumentValueProperty().set("3");
        viewModel.secondArgumentValueProperty().set("4");
        viewModel.thirdArgumentValueProperty().set("5");
        assertEquals("60.000", viewModel.getResult());
    }

    @Test
    public void canNotCalculateVolumeWithFloatLength() {
        viewModel.currentShapeProperty().set(Shape.CUBE);
        viewModel.firstArgumentValueProperty().set("1");
        viewModel.secondArgumentValueProperty().set("2.0");
        viewModel.thirdArgumentValueProperty().set("3");
        assertEquals(Status.INVALID_ARGUMENTS.toString(), viewModel.getResult());
    }

    @Test
    public void canNotCalculateCubeVolumeWithNegativeFirstLength() {
        viewModel.currentShapeProperty().set(Shape.CUBE);
        viewModel.firstArgumentValueProperty().set("-1");
        viewModel.secondArgumentValueProperty().set("2");
        viewModel.thirdArgumentValueProperty().set("3");
        assertEquals(Status.INVALID_ARGUMENTS.toString(), viewModel.getResult());
    }

    @Test
    public void canNotCalculateCubeVolumeWithNegativeSecondLength() {
        viewModel.currentShapeProperty().set(Shape.CUBE);
        viewModel.firstArgumentValueProperty().set("1");
        viewModel.secondArgumentValueProperty().set("-2");
        viewModel.thirdArgumentValueProperty().set("3");
        assertEquals(Status.INVALID_ARGUMENTS.toString(), viewModel.getResult());
    }

    @Test
    public void canCalculateCubeVolumeWithZeroLength() {
        viewModel.currentShapeProperty().set(Shape.CUBE);
        viewModel.firstArgumentValueProperty().set("1");
        viewModel.secondArgumentValueProperty().set("2");
        viewModel.thirdArgumentValueProperty().set("0");
        assertEquals("0.000", viewModel.getResult());
    }

    @Test
    public void canCalculateRegularPolygonPrismVolume() {
        viewModel.currentShapeProperty().set(Shape.REGULAR_POLYGON_PRISM);
        viewModel.firstArgumentValueProperty().set("6");
        viewModel.secondArgumentValueProperty().set("1");
        viewModel.thirdArgumentValueProperty().set("2");
        assertEquals("5.196", viewModel.getResult());
    }

    @Test
    public void canNotCalculateRegularPolygonPrismVolumeWithFirstNegativeArgument() {
        viewModel.currentShapeProperty().set(Shape.REGULAR_POLYGON_PRISM);
        viewModel.firstArgumentValueProperty().set("-1");
        viewModel.secondArgumentValueProperty().set("3");
        viewModel.thirdArgumentValueProperty().set("4");
        assertEquals(Status.INVALID_ARGUMENTS.toString(), viewModel.getResult());
    }

    @Test
    public void checkSwitchToCube() {
        viewModel.currentShapeProperty().set(Shape.CUBE);
        checkParametersNames(Shape.CUBE);
    }


    @Test
    public void checkSwitchToPrism() {
        viewModel.currentShapeProperty().set(Shape.REGULAR_POLYGON_PRISM);
        checkParametersNames(Shape.REGULAR_POLYGON_PRISM);
    }

    @Test
    public void checkSwitchBackToUnknown() {
        viewModel.currentShapeProperty().set(Shape.CUBE);
        viewModel.currentShapeProperty().set(Shape.UNKNOWN);
        checkParametersNames(Shape.UNKNOWN);
    }

    @Test
    public void checkShapeToString() {
        assertEquals("Куб", Shape.CUBE.toString());
    }
}
