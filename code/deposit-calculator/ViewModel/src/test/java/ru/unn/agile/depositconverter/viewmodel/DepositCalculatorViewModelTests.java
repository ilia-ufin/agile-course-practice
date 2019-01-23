package ru.unn.agile.depositconverter.viewmodel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.depositconverter.model.AccruedInterest;
import ru.unn.agile.depositconverter.model.FrequencyOfCapitalization;
import ru.unn.agile.depositconverter.viewmodel.DepositCalculatorViewModel.Status;

import static org.junit.Assert.*;

public class DepositCalculatorViewModelTests {
    private DepositCalculatorViewModel viewModel;
    private static final String DEPOSIT_AMOUNT_EXAMPLE = "1000";
    private static final String TERM_PLACEMENT_EXAMPLE = "12";
    private static final String INTEREST_RATE = "20";

    private static final String ACCRUED_INTEREST_EXAMPLE = "addToDeposit";
    private static final String ACCRUED_INTEREST_PAY_OUT = "payOut";

    private static final String FREQUENCY_OF_CAPITALIZATION_EXAMPLE = "onceMonth";
    private static final String FREQUENCY_OF_CAPITALIZATION_ONCE_TWO_MONTH = "onceTwoMonth";
    private static final String FREQUENCY_OF_CAPITALIZATION_QUARTERLY = "quarterly";
    private static final String FREQUENCY_OF_CAPITALIZATION_HALF_YEAR = "halfYear";

    private static final String NEGATIVE_NUMBER_INCORRECT_FORM = "-1000";
    private static final String EMPTY_STRING = "";
    private static final String WRONG_LETTER_VALUE = "a";



    @Before
    public void setUp() {
        FakeLogger fakeLogger = new FakeLogger();
        viewModel = new DepositCalculatorViewModel(fakeLogger);
        viewModel.setDepositAmount(DEPOSIT_AMOUNT_EXAMPLE);
        viewModel.setTermPlacement(TERM_PLACEMENT_EXAMPLE);
        viewModel.setInterestRate(INTEREST_RATE);
        viewModel.setAccruedInterest(ACCRUED_INTEREST_EXAMPLE);
        viewModel.setFrequencyOfCapitalization(FREQUENCY_OF_CAPITALIZATION_EXAMPLE);
        setFillValues();
    }

    @After
    public void afterTestsDepositCalculator() {
        viewModel = null;

    }

    public void setViewModel(final DepositCalculatorViewModel inViewModel) {
        this.viewModel = inViewModel;
        setFillValues();
    }

    public void setFillValues() {
        viewModel.setDepositAmount(DEPOSIT_AMOUNT_EXAMPLE);
        viewModel.setTermPlacement(TERM_PLACEMENT_EXAMPLE);
        viewModel.setInterestRate(INTEREST_RATE);
        viewModel.setAccruedInterest(ACCRUED_INTEREST_EXAMPLE);
        viewModel.setFrequencyOfCapitalization(FREQUENCY_OF_CAPITALIZATION_EXAMPLE);
    }

    @Test
    public void canSetDefaultValues() {
        viewModel = null;
        FakeLogger fakeLogger = new FakeLogger();
        viewModel = new DepositCalculatorViewModel(fakeLogger);
        assertEquals(EMPTY_STRING, viewModel.getDepositAmount());
        assertEquals(EMPTY_STRING, viewModel.getTermPlacement());
        assertEquals(EMPTY_STRING, viewModel.getInterestRate());

        assertEquals(AccruedInterest.addToDeposit, viewModel.getAccruedInterest());
        assertEquals(FrequencyOfCapitalization.onceMonth, viewModel.getFrequencyOfCapitalization());

        assertEquals(Status.WAITING, viewModel.getStatus());
        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void canSetAddToDeposit() {
        viewModel.setAccruedInterest(ACCRUED_INTEREST_EXAMPLE);
        assertEquals(AccruedInterest.addToDeposit, viewModel.getAccruedInterest());
    }

    @Test
    public  void canSetFrequencyOfCapitalization() {
        viewModel.setFrequencyOfCapitalization(WRONG_LETTER_VALUE);
    }

    @Test
    public void byDefaultButtonCalculateDisabled() {
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void checkSetters() {
        assertEquals("1000", viewModel.getDepositAmount());
        assertEquals("12", viewModel.getTermPlacement());
        assertEquals("20", viewModel.getInterestRate());
        assertEquals(AccruedInterest.addToDeposit, viewModel.getAccruedInterest());
        assertEquals(FrequencyOfCapitalization.onceMonth, viewModel.getFrequencyOfCapitalization());
    }

    @Test
    public void checkStatusWhenReadyCalculate() {
        viewModel.checkCountFields();
        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void whenDepositAmountEmptyButtonCalculateDisabled() {
        viewModel.setDepositAmount(EMPTY_STRING);
        viewModel.checkCountFields();
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void whenTermPlacementEmptyButtonCalculateDisabled() {
        viewModel.setTermPlacement(EMPTY_STRING);
        viewModel.checkCountFields();
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void whenInterestRateEmptyButtonCalculateDisabled() {
        viewModel.setInterestRate(EMPTY_STRING);
        viewModel.checkCountFields();
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void accruedInterestIsAddToDeposit() {
        assertEquals(AccruedInterest.addToDeposit, viewModel.getAccruedInterest());
    }

    @Test
    public void accruedInterestIsPayment() {
        viewModel.setAccruedInterest(ACCRUED_INTEREST_PAY_OUT);
        assertEquals(AccruedInterest.payOut, viewModel.getAccruedInterest());
    }

    @Test
    public void frequencyOfCapitalizationIsOnceInMonth() {
        assertEquals(FrequencyOfCapitalization.onceMonth,
                viewModel.getFrequencyOfCapitalization());
    }

    @Test
    public void frequencyOfCapitalizationIsOnceTwoMonth() {
        viewModel.setFrequencyOfCapitalization(FREQUENCY_OF_CAPITALIZATION_ONCE_TWO_MONTH);
        assertEquals(FrequencyOfCapitalization.onceTwoMonth,
                viewModel.getFrequencyOfCapitalization());
    }

    @Test
    public void frequencyOfCapitalizationIsQuarterly() {
        viewModel.setFrequencyOfCapitalization(FREQUENCY_OF_CAPITALIZATION_QUARTERLY);
        assertEquals(FrequencyOfCapitalization.quarterly,
                viewModel.getFrequencyOfCapitalization());
    }

    @Test
    public void frequencyOfCapitalizationIsHalfYear() {
        viewModel.setFrequencyOfCapitalization(FREQUENCY_OF_CAPITALIZATION_HALF_YEAR);
        assertEquals(FrequencyOfCapitalization.halfYear,
                viewModel.getFrequencyOfCapitalization());
    }

    @Test
    public void whenCalculateDefaultValuesRevenueIsCorrect() {
        viewModel.checkCountFields();
        viewModel.calculate();
        assertEquals("1219.39", viewModel.getRevenueWhenAddToDeposit());
    }

    @Test
    public void whenCalculateDefaultValuesIncomeIsCorrect() {
        setFillValues();
        viewModel.checkCountFields();
        viewModel.calculate();
        assertEquals("219.39", viewModel.getIncomeViewModel());
    }

    @Test
    public void whenCalculateIsNotCorrect() {
        setFillValues();
        viewModel.setDepositAmount(NEGATIVE_NUMBER_INCORRECT_FORM);
        viewModel.checkCountFields();
        viewModel.calculate();
        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void canReportBadFormatDepositAmount() {
        viewModel.setDepositAmount(WRONG_LETTER_VALUE);
        viewModel.checkCountFields();
        Assert.assertEquals(Status.BAD_FORMAT_DEPOSIT_AMOUNT, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatTermPlacement() {
        viewModel.setTermPlacement(WRONG_LETTER_VALUE);
        viewModel.checkCountFields();
        Assert.assertEquals(Status.BAD_FORMAT_TERM_PLACEMENT, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatInterestRate() {
        viewModel.setInterestRate(WRONG_LETTER_VALUE);
        viewModel.checkCountFields();
        Assert.assertEquals(Status.BAD_FORMAT_INTEREST_RATE, viewModel.getStatus());
    }

    @Test
    public void checkResultWithNegativeDepositAmount() {
        viewModel.setDepositAmount(NEGATIVE_NUMBER_INCORRECT_FORM);
        viewModel.checkCountFields();
        assertEquals(Status.BAD_FORMAT_DEPOSIT_AMOUNT, viewModel.getStatus());
    }

    @Test
    public void checkResultWithNegativeInterestRate() {
        viewModel.setInterestRate(NEGATIVE_NUMBER_INCORRECT_FORM);
        viewModel.checkCountFields();
        assertEquals(Status.BAD_FORMAT_INTEREST_RATE, viewModel.getStatus());
    }

    @Test
    public void checkResultWithNegativeTermPlacement() {
        viewModel.setTermPlacement(NEGATIVE_NUMBER_INCORRECT_FORM);
        viewModel.checkCountFields();
        assertEquals(Status.BAD_FORMAT_TERM_PLACEMENT, viewModel.getStatus());
    }

    @Test
    public void isCalculateButtonDisabledInitially() {
        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }
}
