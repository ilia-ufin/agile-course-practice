package ru.unn.agile.mortgagecalculator.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MortgageCalculatorTests {

    private static final double EPSILON = 0.1;
    private static final double FULL_COST_OF_APARTMENT_FOR_TESTS = 1000;
    private static final double FULL_COST_OF_APARTMENT_FIVE_HUNDRED_FOR_TESTS = 500;

    private static final int TERM_MORTGAGE_YEAR_FOR_TESTS = 12;
    private static final int TERM_MORTGAGE_ONE_FOR_TESTS = 1;
    private static final int TERM_MORTGAGE_SIX_FOR_TESTS = 6;
    private static final int TERM_MORTGAGE_TEN_FOR_TESTS = 10;

    private static final double INTEREST_RATE_FOR_TESTS = 20.0;
    private static final double INTEREST_RATE_TEN_FOR_TESTS = 10.0;

    private static final int NEGATIVE_VALUE_FOR_TESTS = -10;

    private static final double INITIAL_PAYMENT_FOR_TESTS = 100;

    private static final int ZERO_VALUE_FOR_TESTS = 0;

    private MortgageCalculator myMortgageCalculator;


    @Before
    public void setUpEmptyExample() {
        myMortgageCalculator = new MortgageCalculator();

        myMortgageCalculator.setFullCostOfApartment(FULL_COST_OF_APARTMENT_FOR_TESTS);
        myMortgageCalculator.setDateOfMortgage(TERM_MORTGAGE_YEAR_FOR_TESTS);
        myMortgageCalculator.setInterestRate(INTEREST_RATE_FOR_TESTS);
    }

    @After
    public void deleteExampleViewModel() {
        myMortgageCalculator = null;
    }

    @Test
    public void checkThatFullCostOfApartmentCorrectInitialized() {
        assertEquals(FULL_COST_OF_APARTMENT_FOR_TESTS,
                myMortgageCalculator.getFullCostOfApartment(), EPSILON);
    }

    @Test(expected = NullPointerException.class)
    public void checkIfFullCostOfApartmentIsZero() {
        myMortgageCalculator.setFullCostOfApartment(ZERO_VALUE_FOR_TESTS);
    }

    @Test(expected = NullPointerException.class)
    public void checkIfFullCostOfApartmentIsNegative() {
        myMortgageCalculator.setFullCostOfApartment(NEGATIVE_VALUE_FOR_TESTS);
    }

    @Test
    public void checkInitialPaymentCorrectInitialized() {
        myMortgageCalculator.setInitialPayment(INITIAL_PAYMENT_FOR_TESTS);
        assertEquals(INITIAL_PAYMENT_FOR_TESTS, myMortgageCalculator.getInitialPayment(), EPSILON);
    }

    @Test
    public void checkThatInitialPaymentIsZero() {
        myMortgageCalculator.setInitialPayment(ZERO_VALUE_FOR_TESTS);

        assertEquals(ZERO_VALUE_FOR_TESTS, myMortgageCalculator.getInitialPayment(), EPSILON);
    }

    @Test(expected = NullPointerException.class)
    public void checkInitialPaymentIsNegative() {
        myMortgageCalculator.setInitialPayment(NEGATIVE_VALUE_FOR_TESTS);

    }

    @Test(expected = NullPointerException.class)
    public void checkInitialPaymentIsMoreThenFullCost() {
        myMortgageCalculator.setInitialPayment(FULL_COST_OF_APARTMENT_FOR_TESTS + 1);
    }

    @Test
    public void checkDateOfMortgageInMonths() {
        myMortgageCalculator.setDateOfMortgage(TERM_MORTGAGE_TEN_FOR_TESTS);

        assertEquals(TERM_MORTGAGE_TEN_FOR_TESTS, myMortgageCalculator.getDateOfMortgage());
    }

    @Test(expected = NullPointerException.class)
    public void checkDateOfMortgageInMonthsIsNegative() {
        myMortgageCalculator.setDateOfMortgage(NEGATIVE_VALUE_FOR_TESTS);

    }

    @Test(expected = NullPointerException.class)
    public void checkDateOfMortgageInMonthsIsZero() {
        myMortgageCalculator.setDateOfMortgage(ZERO_VALUE_FOR_TESTS);

    }

    @Test
    public void checkInterestRate() {
        myMortgageCalculator.setInterestRate(INTEREST_RATE_TEN_FOR_TESTS);

        assertEquals(INTEREST_RATE_TEN_FOR_TESTS, myMortgageCalculator.getInterestRate(), EPSILON);
    }

    @Test(expected = NullPointerException.class)
    public void checkInterestRateIsNegative() {
        myMortgageCalculator.setInterestRate(NEGATIVE_VALUE_FOR_TESTS);
    }

    @Test
    public void checkPrincipalDebtInOneMonth() {
        this.myMortgageCalculator.setInitialPayment(ZERO_VALUE_FOR_TESTS);

        assertEquals(83.3, myMortgageCalculator.getPrincipalDebt(), EPSILON);
    }

    @Test
    public void checkPrincipalDebtInOneMonthWithInitialPayment() {
        myMortgageCalculator.setInitialPayment(INITIAL_PAYMENT_FOR_TESTS);

        assertEquals(75, myMortgageCalculator.getPrincipalDebt(), EPSILON);
    }

    @Test
    public void checkInterestCharges() {
        assertEquals(16.67, myMortgageCalculator.
                getAccruedInterest(myMortgageCalculator.getFullCostOfApartment()), EPSILON);
    }

    @Test
    public void checkFullPriceInFirstMonthOfDate() {
        assertEquals(100.0, myMortgageCalculator.
                getFullPrice(myMortgageCalculator.getFullCostOfApartment()), EPSILON);
    }

    @Test
    public void checkPrincipalDebtIsCorrect() {
        myMortgageCalculator.setFullCostOfApartment(FULL_COST_OF_APARTMENT_FIVE_HUNDRED_FOR_TESTS);
        myMortgageCalculator.setDateOfMortgage(TERM_MORTGAGE_SIX_FOR_TESTS);

        assertEquals(83.33, myMortgageCalculator.getPrincipalDebt(), EPSILON);
    }

    @Test
    public void checkAccruedInterestIsCorrectInMiddleOfDate() {
        myMortgageCalculator.setFullCostOfApartment(FULL_COST_OF_APARTMENT_FIVE_HUNDRED_FOR_TESTS);
        myMortgageCalculator.setDateOfMortgage(TERM_MORTGAGE_SIX_FOR_TESTS);
        assertEquals(8.33, myMortgageCalculator.
                getAccruedInterest(myMortgageCalculator.getFullCostOfApartment()), EPSILON);
    }

    @Test
    public void checkAccruedInterestIsCorrectInLastMonth() {
        myMortgageCalculator.setFullCostOfApartment(FULL_COST_OF_APARTMENT_FIVE_HUNDRED_FOR_TESTS);
        myMortgageCalculator.setDateOfMortgage(TERM_MORTGAGE_ONE_FOR_TESTS);

        assertEquals(8.33, myMortgageCalculator.
                getAccruedInterest(myMortgageCalculator.getFullCostOfApartment()), EPSILON);
    }

    @Test
    public void checkAccruedInterestInTableView() {
        double[] expectedPayments = {16.67, 15.28, 13.89, 12.50,
                11.11, 9.72, 8.33, 6.94, 5.56, 4.17, 2.78, 1.39};
        double[] actualInterest;
        myMortgageCalculator.setAccruedInterestArray();
        actualInterest = myMortgageCalculator.getAccruedInterestArray();

        assertArrayEquals(expectedPayments, actualInterest, EPSILON);
    }

    @Test
    public void checkFullPriceInTableView() {
        double[] expectedPayments = {100, 98.61, 97.22, 95.83,
                94.44, 93.05, 91.66, 90.27, 88.89, 87.50, 86.11, 84.72};
        double[] actualPayments;

        myMortgageCalculator.setFullPriceArray();
        actualPayments = myMortgageCalculator.getFullPriceArray();

        assertArrayEquals(expectedPayments, actualPayments, EPSILON);
    }

    @Test
    public void checkFullPriceForMortgage() {
        myMortgageCalculator.setFullPriceArray();

        assertEquals(1108.34, myMortgageCalculator.getFullPriceForMortgage(), EPSILON);
    }

    @Test
    public void checkFullPriceArrayStrings() {
        String[] expectedFullPriceStrings = {"100.00", "98.61", "97.22", "95.83",
                "94.44", "93.05", "91.66", "90.27", "88.89", "87.50", "86.11", "84.72"};
        String[] actualFullPriceArray;

        myMortgageCalculator.setFullPriceArray();
        actualFullPriceArray = myMortgageCalculator.getFullPriceArrayStrings();

        assertArrayEquals(expectedFullPriceStrings, actualFullPriceArray);
    }

    @Test
    public void checkAccruedInterestArrayStrings() {
        String[] expectedAccruedInterest = {"16.67", "15.28", "13.89", "12.50",
                "11.11", "9.72", "8.33", "6.94", "5.56", "4.17", "2.78", "1.39"};
        String[] actualInterestArray;

        myMortgageCalculator.setAccruedInterestArray();
        actualInterestArray = myMortgageCalculator.getAccruedInterestArrayStrings();

        assertArrayEquals(expectedAccruedInterest, actualInterestArray);
    }


}
