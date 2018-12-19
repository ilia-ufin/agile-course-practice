package ru.unn.agile.depositconverter.viewmodel;
import org.junit.Before;
import ru.unn.agile.depositconverter.viewmodel.DepositCalculatorViewModel.LogMessages;

import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ViewModelWithLoggerTest extends DepositCalculatorViewModelTests {
    private DepositCalculatorViewModel viewModel;
    private static final String DEPOSIT_AMOUNT_EXAMPLE = "1000";
    private static final String ACCRUED_INTEREST_PAY_OUT = "payOut";
    private static final String FREQUENCY_OF_CAPITALIZATION_ONCE_TWO_MONTH = "onceTwoMonth";


    @Override
    public void setViewModel(final DepositCalculatorViewModel viewModel) {
        this.viewModel = viewModel;
        super.setViewModel(this.viewModel);
    }

    @Before
    public void setUp() {
        if (viewModel == null) {
            FakeLogger fakeLogger = new FakeLogger();
            viewModel = new DepositCalculatorViewModel(fakeLogger);
            super.setViewModel(viewModel);
        }
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void checkGetLog() {
        assertNotNull(viewModel.getLog());
    }

    @Test
    public void logContainsProperMessageDepositAmountCalculation() {
        viewModel.setDepositAmount(DEPOSIT_AMOUNT_EXAMPLE);
        viewModel.focusLost();
        String logMsg = viewModel.getLog().get(0);

        assertTrue(logMsg.matches(".*" + LogMessages.EDITING_ARGUMENT_UPDATED + ".*"));
    }

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new DepositCalculatorViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can't be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void logContainsProperMessageCalculateButton() {
        viewModel.checkCountFields();
        viewModel.calculate();

        String logMsg = viewModel.getLog().get(1);
        assertTrue(logMsg.matches(".*" + LogMessages.RESULT_DEPOSIT_CALCULATION + ".*"));
    }

    @Test
    public void logContainsProperMessageFrequencyOfCapitalization() {
        viewModel.setFrequencyOfCapitalization(FREQUENCY_OF_CAPITALIZATION_ONCE_TWO_MONTH);
        viewModel.checkCountFields();
        viewModel.focusLost();

        String logMsg = viewModel.getLog().get(0);
        assertTrue(logMsg.matches(".*" + LogMessages.FREQUENCY_OF_CAPITALIZATION_WAS_CHANGED
                + ".*"));
    }

    @Test
    public void logContainsProperMessageAccruedInterest() {
        viewModel.setAccruedInterest(ACCRUED_INTEREST_PAY_OUT);
        viewModel.checkCountFields();
        viewModel.focusLost();

        String logMsg = viewModel.getLog().get(0);
        assertTrue(logMsg.matches(".*" + LogMessages.ACCRUED_INTEREST_WAS_CHANGED + ".*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        viewModel.checkCountFields();
        viewModel.calculate();
        viewModel.calculate();
        viewModel.calculate();

        assertEquals(6, viewModel.getLog().size());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        viewModel.setDepositAmount(DEPOSIT_AMOUNT_EXAMPLE);
        viewModel.setDepositAmount(DEPOSIT_AMOUNT_EXAMPLE);
        viewModel.setDepositAmount(DEPOSIT_AMOUNT_EXAMPLE);

        viewModel.focusLost();
        viewModel.focusLost();
        viewModel.focusLost();

        assertEquals(1, viewModel.getLog().size());
    }
}
