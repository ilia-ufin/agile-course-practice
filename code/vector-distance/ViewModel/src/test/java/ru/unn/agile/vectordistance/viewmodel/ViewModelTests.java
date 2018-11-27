package ru.unn.agile.vectordistance.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.unn.agile.vectordistance.model.VectorDistance;
import ru.unn.agile.vectordistance.model.VectorDistance.Distance;

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

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getVectorXProperty());
        assertEquals("", viewModel.getVectorYProperty());
        assertEquals(Distance.L1, viewModel.getDistanceProperty());
        assertEquals("", viewModel.getResultProperty());
        assertEquals(Status.WAITING.toString(), viewModel.getStatusProperty());
        assertTrue(viewModel.isCalculationDisabledProperty());
    }

    @Test
    public void canSetL1DistanceCalculation() {
        viewModel.distanceProperty().set(Distance.L1);

        assertEquals(Distance.L1, viewModel.getDistanceProperty());
    }

    @Test
    public void canSetL2DistanceCalculation() {
        viewModel.distanceProperty().set(Distance.L2);

        assertEquals(Distance.L2, viewModel.getDistanceProperty());
    }

    @Test
    public void canSetL3DistanceCalculation() {
        viewModel.distanceProperty().set(Distance.L3);

        assertEquals(Distance.L3, viewModel.getDistanceProperty());
    }

    @Test
    public void canSetL4DistanceCalculation() {
        viewModel.distanceProperty().set(Distance.L4);

        assertEquals(Distance.L4, viewModel.getDistanceProperty());
    }

    @Test
    public void canSetLinfDistanceCalculation() {
        viewModel.distanceProperty().set(Distance.Linf);

        assertEquals(Distance.Linf, viewModel.getDistanceProperty());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        assertEquals(Status.WAITING.toString(), viewModel.getStatusProperty());
    }

    @Test
    public void canSetVectorX() {
        viewModel.vectorXProperty().set("1 2 3");

        assertEquals("1 2 3", viewModel.getVectorXProperty());
    }

    @Test
    public void canSetVectorY() {
        viewModel.vectorYProperty().set("1 2 3");

        assertEquals("1 2 3", viewModel.getVectorYProperty());
    }

    @Test
    public void canCalculateWithNegativeNumbers() {
        viewModel.vectorXProperty().set("-1");
        viewModel.vectorYProperty().set("-4");
        viewModel.calculate();

        assertEquals("3.0", viewModel.getResultProperty());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();
        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.getStatusProperty());
    }

    @Test
    public void canSetStatusReady() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.getStatusProperty());
    }

    @Test
    public void canReportBadFormatWhenSetVectorX() {
        setInputData();
        viewModel.vectorXProperty().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatusProperty());
    }

    @Test
    public void canReportBadFormatWhenNotANumberBetweenNumbers() {
        setInputData();
        viewModel.vectorXProperty().set("1 a 2");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatusProperty());
    }

    @Test
    public void canReportBadFormatWhenSetVectorY() {
        setInputData();
        viewModel.vectorYProperty().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatusProperty());
    }

    @Test
    public void canReportWaitingIfOnlyOneVectorPassed() {
        viewModel.vectorXProperty().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.getStatusProperty());
    }

    @Test
    public void calculateButtonIsDisabledWhenNoData() {
        assertTrue(viewModel.isCalculationDisabledProperty());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.vectorXProperty().set("ololo");

        assertTrue(viewModel.isCalculationDisabledProperty());
    }

    @Test
    public void canReportCalculationErrors() {
        setInputData();
        viewModel.vectorXProperty().set("1 2");

        viewModel.calculate();

        assertEquals(VectorDistance.EXPECTED_VECTORS_OF_SAME_LENGTH_EXCEPTION_MESSAGE,
                viewModel.getResultProperty());
    }

    @Test
    public void canCalculateL1Distance() {
        viewModel.vectorXProperty().set("1 2 3");
        viewModel.vectorYProperty().set("4 5 6");

        viewModel.calculate();

        assertEquals("9.0", viewModel.getResultProperty());
    }

    @Test
    public void canComputeL2Distance() {
        setInputData();
        viewModel.distanceProperty().set(Distance.L2);

        viewModel.calculate();

        assertEquals("5.196152", viewModel.getResultProperty());
    }

    @Test
    public void canComputeL3Distance() {
        setInputData();
        viewModel.distanceProperty().set(Distance.L3);

        viewModel.calculate();

        assertEquals("4.326749", viewModel.getResultProperty());
    }

    @Test
    public void canComputeL4Distance() {
        setInputData();
        viewModel.distanceProperty().set(Distance.L4);

        viewModel.calculate();

        assertEquals("3.948222", viewModel.getResultProperty());
    }

    @Test
    public void canComputeLinfDistance() {
        setInputData();
        viewModel.distanceProperty().set(Distance.Linf);

        viewModel.calculate();

        assertEquals("3.0", viewModel.getResultProperty());
    }

    @Test
    public void canComputeDistanceWithFloatNumbers() {
        viewModel.vectorXProperty().set("0.5");
        viewModel.vectorYProperty().set("4.5");
        viewModel.distanceProperty().set(Distance.L1);

        viewModel.calculate();

        assertEquals("4.0", viewModel.getResultProperty());
    }

    private void setInputData() {
        viewModel.vectorXProperty().set("1 2 3");
        viewModel.vectorYProperty().set("4 5 6");
    }

}
