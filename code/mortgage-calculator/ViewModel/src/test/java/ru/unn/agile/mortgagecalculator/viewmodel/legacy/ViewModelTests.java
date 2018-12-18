package ru.unn.agile.mortgagecalculator.viewmodel.legacy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.mortgagecalculator.viewmodel.legacy.ViewModel.Status;

import javax.swing.table.DefaultTableModel;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

public class ViewModelTests {

    private static final String APARTMENT_PRICE_EXAMPLE = "16000";
    private static final String INCORRECT_LENGTH_VALUES = "100000000000000000000000";
    private static final String INCORRECT_WITH_LETTERS_VALUES = "a";
    private static final String TERM_MORTGAGE_YEAR_EXAMPLE = "12";
    private static final String TERM_MORTGAGE_TWO_YEAR_EXAMPLE = "24";
    private static final String TERM_MORTGAGE_HALF_YEAR_EXAMPLE = "6";

    private static final String INTEREST_RATE_EXAMPLE = "20.0";
    private static final String INTEREST_RATE_ELEVEN_EXAMPLE = "11.0";
    private static final String INCORRECT_INTEREST_RATE_EXAMPLE = "50.0";

    private static final String NEGATIVE_VALUE_FOR_TESTS = "-10";

    private static final String INITIAL_PAYMENT_EXAMPLE = "6000";
    private static final String EMPTY_STRING_FOR_TESTS = "";

    private ViewModel viewModel;

    public void setViewModel(final ViewModel modelView) {
        viewModel = modelView;
    }

    @Before
    public void setUpEmptyExample() {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
        setDefaultSettings();
    }

    public void setDefaultSettings() {
        viewModel.setApartmentPrice(APARTMENT_PRICE_EXAMPLE);
        viewModel.setInitialPayment(INITIAL_PAYMENT_EXAMPLE);
        viewModel.setInterestRate(INTEREST_RATE_EXAMPLE);
        viewModel.setTermMortgage(TERM_MORTGAGE_YEAR_EXAMPLE);
    }

    @After
    public void deleteExampleViewModel() {
        viewModel = null;
    }

    @Test
    public void checkStatusInBegin() {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
        assertEquals(Status.COUNT_WAITING, viewModel.getStatus());
    }

    @Test
    public void checkSetters() {
        viewModel.setApartmentPrice(APARTMENT_PRICE_EXAMPLE);
        viewModel.setInitialPayment(INITIAL_PAYMENT_EXAMPLE);
        viewModel.setInterestRate(INTEREST_RATE_EXAMPLE);
        viewModel.setTermMortgage(TERM_MORTGAGE_YEAR_EXAMPLE);

        assertEquals(APARTMENT_PRICE_EXAMPLE, viewModel.getApartmentPrice());
        assertEquals(INITIAL_PAYMENT_EXAMPLE, viewModel.getInitialPayment());
        assertEquals(INTEREST_RATE_EXAMPLE, viewModel.getInterestRate());
        assertEquals(TERM_MORTGAGE_YEAR_EXAMPLE, viewModel.getTermMortgage());
    }

    @Test
    public void isCalculateButtonEnabled() {
        viewModel.checkCountFields();

        assertEquals(true, viewModel.isCalculateButtonEnable());
    }

    @Test
    public void checkStatusWhenReadyCalculate() {
        viewModel.checkCountFields();

        assertEquals(Status.READY_CALCULATE, viewModel.getStatus());
    }

    @Test
    public void checkStatusFullPriceMortgage() {
        viewModel.checkCountFields();
        viewModel.calculateFullPriceMortgage();

        assertEquals(Status.FULL_PRICE_MORTGAGE, viewModel.getStatus());
    }

    @Test
    public void checkStatusFullPriceMortgageWithIncorrectDate() {
        viewModel.setInterestRate(INCORRECT_INTEREST_RATE_EXAMPLE);
        viewModel.checkCountFields();
        viewModel.calculateFullPriceMortgage();

        assertEquals(false, viewModel.isCalculateButtonEnable());
        assertEquals(Status.BAD_INTEREST_RATE_FORMAT_NUMBERS, viewModel.getStatus());
        assertEquals(null, viewModel.getFullPriceMortgage());
    }

    @Test
    public void isLengthApartmentPriceNotCorrect() {
        viewModel.setApartmentPrice(INCORRECT_LENGTH_VALUES);
        viewModel.checkCountFields();

        assertEquals(Status.BAD_APARTMENT_PRICE_FORMAT_NUMBERS, viewModel.getStatus());
    }

    @Test
    public void isLengthInitialPaymentNotCorrect() {
        viewModel.setInitialPayment(INCORRECT_LENGTH_VALUES);
        viewModel.checkCountFields();

        assertEquals(Status.BAD_INITIAL_PAYMENT_FORMAT_NUMBERS, viewModel.getStatus());
    }

    @Test
    public void isLengthInterestRateNotCorrect() {
        viewModel.setInterestRate(INCORRECT_LENGTH_VALUES);
        viewModel.checkCountFields();

        assertEquals(Status.BAD_INTEREST_RATE_FORMAT_NUMBERS, viewModel.getStatus());
    }

    @Test
    public void checkResultWithNegativeInitialRate() {
        viewModel.setInterestRate(NEGATIVE_VALUE_FOR_TESTS);
        viewModel.checkCountFields();

        assertEquals(Status.BAD_INTEREST_RATE_FORMAT_NUMBERS, viewModel.getStatus());
    }

    @Test
    public void checkResultWithNegativeTermMortgage() {
        viewModel.setTermMortgage(NEGATIVE_VALUE_FOR_TESTS);
        viewModel.checkCountFields();

        assertEquals(Status.BAD_TERM_FORMAT_NUMBERS, viewModel.getStatus());
    }

    @Test
    public void isLengthApartmentPriceBigNotCorrect() {
        viewModel.setApartmentPrice(INCORRECT_LENGTH_VALUES);
        viewModel.checkCountFields();

        assertEquals(Status.BAD_APARTMENT_PRICE_FORMAT_NUMBERS, viewModel.getStatus());
    }

    @Test
    public void isLengthTermMortgageNotCorrect() {
        viewModel.setTermMortgage(INCORRECT_LENGTH_VALUES);
        viewModel.checkCountFields();

        assertEquals(Status.BAD_TERM_FORMAT_NUMBERS, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenOneOfCountFieldEmpty() {
        viewModel.setTermMortgage(EMPTY_STRING_FOR_TESTS);
        viewModel.checkCountFields();

        assertEquals(Status.BAD_TERM_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenInitialPaymentMoreApartmentPrice() {
        viewModel.setInitialPayment(APARTMENT_PRICE_EXAMPLE);
        viewModel.checkCountFields();

        assertEquals(Status.BAD_INITIAL_PAYMENT_FORMAT_NUMBERS, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenCountInputWithCharInInitialPayment() {
        viewModel.setInitialPayment(INCORRECT_WITH_LETTERS_VALUES);
        viewModel.checkCountFields();

        assertEquals(Status.BAD_INITIAL_PAYMENT_COUNT_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenCountInputWithCharInApartmentPrice() {
        viewModel.setApartmentPrice(INCORRECT_WITH_LETTERS_VALUES);
        viewModel.checkCountFields();

        assertEquals(Status.BAD_APARTMENT_PRICE_COUNT_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenCountInputWithCharInInterestRate() {
        viewModel.setInterestRate(INCORRECT_WITH_LETTERS_VALUES);
        viewModel.checkCountFields();

        assertEquals(Status.BAD_INTEREST_RATE_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenCountInputWithIncorrectRate() {
        viewModel.setInterestRate(INCORRECT_INTEREST_RATE_EXAMPLE);
        viewModel.checkCountFields();

        assertEquals(Status.BAD_INTEREST_RATE_FORMAT_NUMBERS, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenCountInputWithIncorrectTermMortgage() {
        viewModel.setTermMortgage(INCORRECT_LENGTH_VALUES);
        viewModel.checkCountFields();

        assertEquals(Status.BAD_TERM_FORMAT_NUMBERS, viewModel.getStatus());
    }

    @Test
    public void checkResultWithNormalParameters() {
        viewModel.checkCountFields();
        viewModel.calculateFullPriceMortgage();

        assertEquals("11083.30", viewModel.getFullPriceMortgage());
    }

    @Test
    public void checkResultWithRate() {
        viewModel.setInterestRate(INTEREST_RATE_ELEVEN_EXAMPLE);
        viewModel.setTermMortgage(TERM_MORTGAGE_HALF_YEAR_EXAMPLE);
        viewModel.checkCountFields();

        viewModel.calculateFullPriceMortgage();

        assertEquals("10320.86", viewModel.getFullPriceMortgage());
    }

    @Test
    public void checkResultWithTermMortgage() {
        viewModel.setTermMortgage(TERM_MORTGAGE_TWO_YEAR_EXAMPLE);
        viewModel.checkCountFields();

        viewModel.calculateFullPriceMortgage();

        assertEquals("12083.40", viewModel.getFullPriceMortgage());
    }


    @Test
    public void checkResultWithNegativeApartmentPrice() {
        viewModel.setApartmentPrice(NEGATIVE_VALUE_FOR_TESTS);

        viewModel.checkCountFields();

        assertEquals(Status.BAD_APARTMENT_PRICE_FORMAT_SIGN, viewModel.getStatus());
    }

    @Test
    public void checkResultWithNegativeInitialPayment() {
        viewModel.setInitialPayment(NEGATIVE_VALUE_FOR_TESTS);

        viewModel.checkCountFields();

        assertEquals(Status.BAD_INITIAL_PAYMENT_FORMAT_SIGN, viewModel.getStatus());
    }

    @Test
    public void checkCorrectlyFilledAccruedInterestDateInTableModel() {
        String[] expectedAccruedInterest = {"166.67", "152.78", "138.89", "125.00",
                "111.11", "97.22", "83.33", "69.44", "55.56", "41.67", "27.78", "13.89"};

        int numberOfMonths = 12;

        viewModel.checkCountFields();
        viewModel.calculateFullPriceMortgage();
        DefaultTableModel tableModel = viewModel.getTableModel();

        for (int indexMonth = 0; indexMonth < numberOfMonths; indexMonth++) {
            assertEquals(expectedAccruedInterest[indexMonth], tableModel.getValueAt(indexMonth, 1));
        }
    }

    @Test
    public void checkCorrectlyFilledFullPriceDateInTableModel() {
        String[] expectedFullPriceStrings = {"1000.00", "986.11", "972.22", "958.33",
                "944.44", "930.55", "916.66", "902.77", "888.89", "875.00", "861.11", "847.22"};

        int numberOfMonths = 12;

        viewModel.checkCountFields();
        viewModel.calculateFullPriceMortgage();
        DefaultTableModel tableModel = viewModel.getTableModel();

        for (int indexMonth = 0; indexMonth < numberOfMonths; indexMonth++) {
            assertEquals(expectedFullPriceStrings[indexMonth],
                    tableModel.getValueAt(indexMonth, 2));
        }
    }

    @Test
    public void checkGetLog() {
        assertNotNull(viewModel.getLog());
    }

    @Test
    public void isNotEmptyLog() {
        viewModel.checkCountFields();

        List<String> res = viewModel.getLog();

        assertFalse(res.isEmpty());
    }
}
