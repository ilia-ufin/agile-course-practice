package ru.unn.agile.mathstatistics.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class ViewModelTests {

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Before
    public void setUp() {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
    }

    private ViewModel viewModel;

    public void setViewModel(final ViewModel vModel) {
        this.viewModel = vModel;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getValueText());
        assertEquals("", viewModel.getProbabilityText());
        assertEquals(Operation.EXPECTED_VALUE, viewModel.getOperation());
        assertEquals("", viewModel.getMomentOrderText());
        assertEquals("", viewModel.getMomentOffsetText());
        assertEquals("", viewModel.getResultText());
        assertEquals(Status.WAITING_INPUT_DATA, viewModel.getStatusMessageText());
    }

    @Test
    public void isStatusWaitingAtTheBeginning() {
        assertEquals(Status.WAITING_INPUT_DATA, viewModel.getStatusMessageText());
    }

    @Test
    public void isCalculateButtonDisabledAtTheBeginning() {
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void canSetExpectedValueOperation() {
        Operation expectedValueOperation = Operation.EXPECTED_VALUE;

        viewModel.setOperation(expectedValueOperation);

        assertEquals(expectedValueOperation, viewModel.getOperation());
    }

    @Test
    public void canSetDispersionOperation() {
        Operation dispersionOperation = Operation.DISPERSION;

        viewModel.setOperation(dispersionOperation);

        assertEquals(dispersionOperation, viewModel.getOperation());
    }

    @Test
    public void canSetInitialMomentOperation() {
        Operation initialMomentOperation = Operation.INITIAL_MOMENT;

        viewModel.setOperation(initialMomentOperation);

        assertEquals(initialMomentOperation, viewModel.getOperation());
    }

    @Test
    public void canSetCentralMomentOperation() {
        Operation centralMomentOperation = Operation.CENTRAL_MOMENT;

        viewModel.setOperation(centralMomentOperation);

        assertEquals(centralMomentOperation, viewModel.getOperation());
    }

    @Test
    public void isOrderTextDisabledWhenExpectedValueOperationIsSelected() {
        Operation expectedValueOperation = Operation.EXPECTED_VALUE;

        viewModel.setOperation(expectedValueOperation);

        assertFalse(viewModel.isOrderTextEnabled());
    }

    @Test
    public void isOrderTextDisabledWhenDispersionOperationIsSelected() {
        Operation dispersionOperation = Operation.DISPERSION;

        viewModel.setOperation(dispersionOperation);

        assertFalse(viewModel.isOrderTextEnabled());
    }

    @Test
    public void isOrderTextEnabledWhenInitialMomentOperationIsSelected() {
        Operation initialMomentOperation = Operation.INITIAL_MOMENT;

        viewModel.setOperation(initialMomentOperation);

        assertTrue(viewModel.isOrderTextEnabled());
    }

    @Test
    public void isOrderTextEnabledWhenCentralMomentOperationIsSelected() {
        Operation centralMomentOperation = Operation.CENTRAL_MOMENT;

        viewModel.setOperation(centralMomentOperation);

        assertTrue(viewModel.isOrderTextEnabled());
    }

    @Test
    public void isOffsetTextDisabledWhenExpectedValueOperationIsSelected() {
        Operation expectedValueOperation = Operation.EXPECTED_VALUE;

        viewModel.setOperation(expectedValueOperation);

        assertFalse(viewModel.isOffsetTextEnabled());
    }

    @Test
    public void isOffsetTextDisabledWhenDispersionOperationIsSelected() {
        Operation dispersionOperation = Operation.DISPERSION;

        viewModel.setOperation(dispersionOperation);

        assertFalse(viewModel.isOffsetTextEnabled());
    }

    @Test
    public void isOffsetTextDisabledWhenInitialMomentOperationIsSelected() {
        Operation initialMomentOperation = Operation.INITIAL_MOMENT;

        viewModel.setOperation(initialMomentOperation);

        assertFalse(viewModel.isOffsetTextEnabled());
    }

    @Test
    public void isOffsetTextEnabledWhenCentralMomentOperationIsSelected() {
        Operation centralMomentOperation = Operation.CENTRAL_MOMENT;

        viewModel.setOperation(centralMomentOperation);

        assertTrue(viewModel.isOffsetTextEnabled());
    }

    @Test
    public void canDetectCorrectDistributionUnit() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("1");

        assertTrue(viewModel.checkDistributionUnit());
    }

    @Test
    public void canDetectEmptyValueOfDistributionUnit() {
        viewModel.setValueText("");
        viewModel.setProbabilityText("1");

        assertFalse(viewModel.checkDistributionUnit());
        assertEquals(Status.BAD_DISTRIBUTION_UNIT_FORMAT, viewModel.getStatusMessageText());
    }

    @Test
    public void canDetectEmptyProbabilityOfDistributionUnit() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("");

        assertFalse(viewModel.checkDistributionUnit());
        assertEquals(Status.BAD_DISTRIBUTION_UNIT_FORMAT, viewModel.getStatusMessageText());
    }

    @Test
    public void canDetectIncorrectValueFormatOfDistributionUnit() {
        viewModel.setValueText("abc");
        viewModel.setProbabilityText("1");

        assertFalse(viewModel.checkDistributionUnit());
        assertEquals(Status.BAD_DISTRIBUTION_UNIT_FORMAT, viewModel.getStatusMessageText());
    }

    @Test
    public void canDetectIncorrectProbabilityFormatOfDistributionUnit() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("abc");

        assertFalse(viewModel.checkDistributionUnit());
        assertEquals(Status.BAD_DISTRIBUTION_UNIT_FORMAT, viewModel.getStatusMessageText());
    }

    @Test
    public void canDetectBadProbabilityByAddToDistributionProcess() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("-1");

        viewModel.addToDistributionProcess();

        assertFalse(viewModel.isCalculateButtonEnabled());
        assertEquals(Status.BAD_PROBABILITY_VALUE, viewModel.getStatusMessageText());
    }

    @Test
    public void canDetectBadDistributionUnitFormatByAddToDistributionProcess() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("abc");

        viewModel.addToDistributionProcess();

        assertFalse(viewModel.isCalculateButtonEnabled());
        assertEquals(Status.BAD_DISTRIBUTION_UNIT_FORMAT, viewModel.getStatusMessageText());
    }

    @Test
    public void canDetectCorrectDistributionUnitByAddToDistributionProcess() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("0.5");

        viewModel.addToDistributionProcess();

        assertFalse(viewModel.isCalculateButtonEnabled());
        assertEquals(Status.ADD_TO_DISTRIBUTION_SUCCESS,
                viewModel.getStatusMessageText());
    }

    @Test
    public void canDetectCalculateReadyStateByAddToDistributionProcess() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("0.5");
        viewModel.addToDistributionProcess();
        viewModel.setValueText("2");
        viewModel.setProbabilityText("0.5");
        viewModel.addToDistributionProcess();

        assertTrue(viewModel.isCalculateButtonEnabled());
        assertEquals(Status.CALCULATE_READY, viewModel.getStatusMessageText());
    }

    @Test
    public void canDetectUnsuitableDistributionUnitByAddToDistributionProcess() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("0.5");
        viewModel.addToDistributionProcess();
        viewModel.setValueText("2");
        viewModel.setProbabilityText("0.7");
        viewModel.addToDistributionProcess();

        assertFalse(viewModel.isCalculateButtonEnabled());
        assertEquals(Status.INCORRECT_PROBABILITIES_SUM, viewModel.getStatusMessageText());
    }

    @Test
    public void canDetectCorrectMomentOrder() {
        viewModel.setMomentOrderText("2");

        assertTrue(viewModel.checkMomentOrder());
    }

    @Test
    public void canDetectLessThanOneMomentOrder() {
        viewModel.setMomentOrderText("0");

        assertFalse(viewModel.checkMomentOrder());
    }

    @Test
    public void canDetectIncorrectFormatOfMomentOrder() {
        viewModel.setMomentOrderText("abc");

        assertFalse(viewModel.checkMomentOrder());
    }

    @Test
    public void canDetectNotIntegerFormatOfMomentOrder() {
        viewModel.setMomentOrderText("2.4");

        assertFalse(viewModel.checkMomentOrder());
    }

    @Test
    public void canDetectCorrectMomentOffset() {
        viewModel.setMomentOffsetText("2.4");

        assertTrue(viewModel.checkMomentOffset());
    }

    @Test
    public void canDetectIncorrectFormatOfMomentOffset() {
        viewModel.setMomentOffsetText("abc");

        assertFalse(viewModel.checkMomentOffset());
    }

    @Test
    public void canReset() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("1.0");
        viewModel.addToDistributionProcess();

        viewModel.resetProcess();

        List<String> distributionUnits = viewModel.getDistributionUnits();
        assertTrue(distributionUnits.isEmpty());
        assertEquals("", viewModel.getProbabilityText());
        assertEquals("", viewModel.getValueText());
        assertEquals(Operation.EXPECTED_VALUE, viewModel.getOperation());
        assertEquals("", viewModel.getResultText());
        assertEquals(Status.WAITING_INPUT_DATA, viewModel.getStatusMessageText());
        assertEquals("", viewModel.getMomentOffsetText());
        assertEquals("", viewModel.getMomentOrderText());
    }

    @Test
    public void canCalculateExpectedValue() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("1.0");
        viewModel.addToDistributionProcess();
        viewModel.setOperation(Operation.EXPECTED_VALUE);

        viewModel.calculateProcess();

        String resultText = viewModel.getResultText();
        assertFalse(resultText.isEmpty());
        assertEquals(Status.SUCCESS, viewModel.getStatusMessageText());
    }

    @Test
    public void canCalculateDispersion() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("1.0");
        viewModel.addToDistributionProcess();
        viewModel.setOperation(Operation.DISPERSION);

        viewModel.calculateProcess();

        String resultText = viewModel.getResultText();
        assertFalse(resultText.isEmpty());
        assertEquals(Status.SUCCESS, viewModel.getStatusMessageText());
    }

    @Test
    public void canCalculateInitialMoment() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("1.0");
        viewModel.setMomentOrderText("2");
        viewModel.addToDistributionProcess();
        viewModel.setOperation(Operation.INITIAL_MOMENT);

        viewModel.calculateProcess();

        String resultText = viewModel.getResultText();
        assertFalse(resultText.isEmpty());
        assertEquals(Status.SUCCESS, viewModel.getStatusMessageText());
    }

    @Test
    public void canDetectIncorrectOrderInInitialMomentCalculating() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("1.0");
        viewModel.setMomentOrderText("abc");
        viewModel.addToDistributionProcess();
        viewModel.setOperation(Operation.INITIAL_MOMENT);

        viewModel.calculateProcess();

        String resultText = viewModel.getResultText();
        assertTrue(resultText.isEmpty());
        assertEquals(Status.BAD_MOMENT_ORDER_FORMAT, viewModel.getStatusMessageText());
    }

    @Test
    public void canCalculateCentralMoment() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("1.0");
        viewModel.setMomentOrderText("1");
        viewModel.setMomentOffsetText("5.5");
        viewModel.addToDistributionProcess();
        viewModel.setOperation(Operation.CENTRAL_MOMENT);

        viewModel.calculateProcess();

        String resultText = viewModel.getResultText();
        assertFalse(resultText.isEmpty());
        assertEquals(Status.SUCCESS, viewModel.getStatusMessageText());
    }

    @Test
    public void canDetectIncorrectOrderInCentralMomentCalculating() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("1.0");
        viewModel.setMomentOrderText("abc");
        viewModel.setMomentOffsetText("5.5");
        viewModel.addToDistributionProcess();
        viewModel.setOperation(Operation.CENTRAL_MOMENT);

        viewModel.calculateProcess();

        String resultText = viewModel.getResultText();
        assertTrue(resultText.isEmpty());
        assertEquals(Status.BAD_MOMENT_ORDER_FORMAT, viewModel.getStatusMessageText());
    }

    @Test
    public void canDetectEmptyOrderInCentralMomentCalculating() {
        viewModel.setValueText("2");
        viewModel.setProbabilityText("1.0");
        viewModel.setMomentOrderText("");
        viewModel.setMomentOffsetText("6.5");
        viewModel.addToDistributionProcess();
        viewModel.setOperation(Operation.CENTRAL_MOMENT);

        viewModel.calculateProcess();

        String resultText = viewModel.getResultText();
        assertEquals(Status.BAD_MOMENT_ORDER_FORMAT, viewModel.getStatusMessageText());
        assertTrue(resultText.isEmpty());
    }

    @Test
    public void canDetectIncorrectOffsetInCentralMomentCalculating() {
        viewModel.setValueText("3");
        viewModel.setProbabilityText("1.0");
        viewModel.setMomentOrderText("1");
        viewModel.setMomentOffsetText("gds");
        viewModel.setOperation(Operation.CENTRAL_MOMENT);

        viewModel.addToDistributionProcess();
        viewModel.calculateProcess();

        String resultText = viewModel.getResultText();
        assertTrue(resultText.isEmpty());
        assertEquals(Status.BAD_MOMENT_OFFSET_FORMAT, viewModel.getStatusMessageText());
    }

    @Test
    public void canDetectEmptyOffsetInCentralMomentCalculating() {
        viewModel.setValueText("7");
        viewModel.setProbabilityText("1.0");
        viewModel.setMomentOrderText("1");
        viewModel.setMomentOffsetText("");
        viewModel.addToDistributionProcess();
        viewModel.setOperation(Operation.CENTRAL_MOMENT);

        viewModel.calculateProcess();

        String resultText = viewModel.getResultText();
        assertTrue(resultText.isEmpty());
        assertEquals(Status.BAD_MOMENT_OFFSET_FORMAT, viewModel.getStatusMessageText());
    }


    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenLoggerIsNull() {
        FakeLogger log = null;

        new ViewModel(log);
    }


    @Test
    public void isStatusWaitingWhenLaunch() {
        String mes = viewModel.getLog().get(0);
        assertTrue(mes.matches(".*" + Status.WAITING_INPUT_DATA + ".*"));
    }

    @Test
    public void canCreateWhenLoggerIsNotNull() {
        FakeLogger log = new FakeLogger();

        new ViewModel(log);
    }

    @Test
    public void canLoggerUpdatedWhenBadMomentOffsetFormat() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("1.0");
        viewModel.setMomentOrderText("1");
        viewModel.setMomentOffsetText("");
        viewModel.addToDistributionProcess();
        viewModel.setOperation(Operation.CENTRAL_MOMENT);

        viewModel.calculateProcess();

        final int lastIndex = viewModel.getLog().size() - 1;
        String mes = viewModel.getLog().get(lastIndex);
        assertTrue(
                mes.matches(".*" + Status.BAD_MOMENT_OFFSET_FORMAT + ".*"));
    }

    @Test
    public void canLoggerUpdatedWhenBadDistributionUnitFormat() {
        viewModel.setValueText("abc");
        viewModel.setProbabilityText("1");

        viewModel.addToDistributionProcess();

        final int lastIndex = viewModel.getLog().size() - 1;
        String mes = viewModel.getLog().get(lastIndex);
        assertTrue(
                mes.matches(".*" + Status.BAD_DISTRIBUTION_UNIT_FORMAT + ".*"));
    }


    @Test
    public void canLoggerUpdatedWhenBadProbabilityValue() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("-1");

        viewModel.addToDistributionProcess();

        final int lastIndex = viewModel.getLog().size() - 1;
        String mes = viewModel.getLog().get(lastIndex);
        assertTrue(
                mes.matches(".*" + Status.BAD_PROBABILITY_VALUE + ".*"));
    }

    @Test
    public void canLoggerUpdatedWhenIncorrectProbabilitiesSum() {
        viewModel.setValueText("1");
        viewModel.setProbabilityText("0.5");
        viewModel.addToDistributionProcess();
        viewModel.setValueText("2");
        viewModel.setProbabilityText("0.7");

        viewModel.addToDistributionProcess();

        final int lastIndex = viewModel.getLog().size() - 1;
        String mes = viewModel.getLog().get(lastIndex);
        assertTrue(
                mes.matches(".*" + Status.INCORRECT_PROBABILITIES_SUM + ".*"));
    }

    @Test
    public void canLoggerUpdatedWhenAddedValueAndProbability() {
        viewModel.setValueText("4");
        viewModel.setProbabilityText("0.5");

        viewModel.addToDistributionProcess();

        List<String> log = viewModel.getLog();
        String prevLastMes = log.get(log.size() - 2);
        assertTrue(prevLastMes.matches(
                ".*" + Status.ADD_TO_DISTRIBUTION_READY + ".*"));
        String lastMes = log.get(log.size() - 1);
        assertTrue(lastMes.matches(
                ".*" + Status.ADD_TO_DISTRIBUTION_SUCCESS + ".*"));
    }

    @Test
    public void canLoggerUpdatedWhenBadMomentOrderValue() {
        viewModel.setMomentOrderText("0");
        viewModel.addToDistributionProcess();
        viewModel.setOperation(Operation.INITIAL_MOMENT);
        viewModel.calculateProcess();

        final int lastIndex = viewModel.getLog().size() - 1;
        String mes = viewModel.getLog().get(lastIndex);
        assertTrue(mes.matches(".*" + Status.BAD_MOMENT_ORDER_VALUE + ".*"));
    }

    @Test
    public void canLoggerUpdatedWhenBadMomentOrderFormat() {
        viewModel.setMomentOrderText("cas");
        viewModel.setOperation(Operation.CENTRAL_MOMENT);
        viewModel.addToDistributionProcess();
        viewModel.calculateProcess();

        final int lastIndex = viewModel.getLog().size() - 1;
        String mes = viewModel.getLog().get(lastIndex);
        assertTrue(mes.matches(".*" + Status.BAD_MOMENT_ORDER_FORMAT + ".*"));
    }

    @Test
    public void canLoggerUpdatedWhenCalculateReady() {
        viewModel.setValueText("2");
        viewModel.setProbabilityText("0.5");
        viewModel.addToDistributionProcess();
        viewModel.setValueText("1");
        viewModel.setProbabilityText("0.5");

        viewModel.addToDistributionProcess();

        final int lastIndex = viewModel.getLog().size() - 1;
        String mes = viewModel.getLog().get(lastIndex);
        assertTrue(mes.matches(".*" + Status.CALCULATE_READY + ".*"));
    }

    @Test
    public void canLoggerUpdatedWhenCalculationSuccessfull() {
        viewModel.setValueText("3");
        viewModel.setProbabilityText("1.0");
        viewModel.addToDistributionProcess();
        viewModel.setOperation(Operation.DISPERSION);

        viewModel.calculateProcess();

        final int lastIndex = viewModel.getLog().size() - 1;
        String mes = viewModel.getLog().get(lastIndex);
        assertTrue(mes.matches(
                ".*" + viewModel.SUCCESSFULL_CALCULAION + viewModel.getResultText() + ".*"));
    }
}
