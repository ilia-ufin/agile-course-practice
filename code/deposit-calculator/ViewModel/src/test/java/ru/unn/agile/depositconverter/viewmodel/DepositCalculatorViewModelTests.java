package ru.unn.agile.depositconverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.depositconverter.model.AccruedInterest;
import ru.unn.agile.depositconverter.model.FrequencyOfCapitalization;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DepositCalculatorViewModelTests {
    private final double delta = 0.001;
    private DepositCalculatorViewModel viewModel;
    @Before
    public void beforeTestsDepositCalculator() {
        viewModel = new DepositCalculatorViewModel();
    }
    @Test
    public void byDefaultButtonCalculateDisabled() {
        assertTrue(viewModel.isCalculatorButtonEnabled());
    }

    @Test
    public void byDefaultDepositAmountIs700000() {
        viewModel = new DepositCalculatorViewModel();
        assertEquals(700000, viewModel.getDepositAmountView(), delta);
    }

    @Test
    public void whenDepositAmountEmptyButtonCalculateDisabled() {
        viewModel.setDepositAmount("");
        assertFalse(viewModel.isCalculatorButtonEnabled());
    }

    @Test
    public void whenDepositAmountClearButtonCalculateDisabled() {
        viewModel.setDepositAmount("700000");
        viewModel.setDepositAmount("");
        assertFalse(viewModel.isCalculatorButtonEnabled());
    }

    @Test
    public void byDefaultTermPlacementIs12() {
        assertEquals(12, viewModel.getTermPlacementView());
    }

    @Test
    public void whenTermPlacementEmptyButtonCalculateDisabled() {
        viewModel.setTermPlacement("");
        assertFalse(viewModel.isCalculatorButtonEnabled());
    }

    @Test
    public void whenTermPlacementClearButtonCalculateDisabled() {
        viewModel.setTermPlacement("12");
        viewModel.setTermPlacement("");
        assertFalse(viewModel.isCalculatorButtonEnabled());
    }

    @Test
    public void byDefaultInterestRateIs8() {
        assertEquals(8, viewModel.getInterestRateView(), delta);
    }

    @Test
    public void whenInterestRateEmptyButtonCalculateDisabled() {
        viewModel.setInterestRate("");
        assertFalse(viewModel.isCalculatorButtonEnabled());
    }

    @Test
    public void whenInterestRateClearButtonCalculateDisabled() {
        viewModel.setInterestRate("");
        assertFalse(viewModel.isCalculatorButtonEnabled());
    }

    @Test
    public void accruedInterestIsAddToDeposit() {
        viewModel.setAccruedInterest("addToDeposit");
        assertEquals(AccruedInterest.addToDeposit, viewModel.getAccruedInterest());
    }

    @Test
    public void accruedInterestIsPayment() {
        viewModel.setAccruedInterest("payOut");
        assertEquals(AccruedInterest.payOut, viewModel.getAccruedInterest());
    }

    @Test
    public void frequencyOfCapitalizationIsOnceInMonth() {
        viewModel.setFrequencyOfCapitalization("onceInMonth");
        assertEquals(FrequencyOfCapitalization.onceMonth,
                viewModel.getFrequencyOfCapitalization());
    }

    @Test
    public void frequencyOfCapitalizationIsOnceTwoMonth() {
        viewModel.setFrequencyOfCapitalization("onceTwoMonth");
        assertEquals(FrequencyOfCapitalization.onceTwoMonth,
                viewModel.getFrequencyOfCapitalization());
    }

    @Test
    public void frequencyOfCapitalizationIsQuarterly() {
        viewModel.setFrequencyOfCapitalization("quarterly");
        assertEquals(FrequencyOfCapitalization.quarterly,
                viewModel.getFrequencyOfCapitalization());
    }

    @Test
    public void frequencyOfCapitalizationIsHalfYear() {
        viewModel.setFrequencyOfCapitalization("halfYear");
        assertEquals(FrequencyOfCapitalization.halfYear,
                viewModel.getFrequencyOfCapitalization());
    }

    @Test
    public void whenCalculateDefaultValuesRevenueIsCorrect() {
        viewModel.calculate();
        assertEquals("758099.65", viewModel.getRevenueWhenAddToDeposit());
    }

    @Test
    public void whenCalculateDefaultValuesIncomeIsCorrect() {
        viewModel.calculate();
        assertEquals("58099.65", viewModel.getIncomeViewModel());
    }

    @Test(expected = NumberFormatException.class)
    public void isExceptionWhenDepositAmountIncorrectConvert() {
        viewModel.setDepositAmount("String");
        viewModel.calculate();
    }

    @Test(expected = NumberFormatException.class)
    public void isExceptionWhenTermPlacementIncorrectConvert() {
        viewModel.setTermPlacement("String");
        viewModel.calculate();
    }

    @Test(expected = NumberFormatException.class)
    public void isExceptionWhenInterestRateIncorrectConvert() {
        viewModel.setInterestRate("String");
        viewModel.calculate();
    }

    @After
    public void afterTestsDepositCalculator() {
        viewModel = null;
    }
}
