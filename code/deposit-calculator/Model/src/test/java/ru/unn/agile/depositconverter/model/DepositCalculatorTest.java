package ru.unn.agile.depositconverter.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DepositCalculatorTest {
    private final double delta = 0.001;

    @Test(expected = NumberFormatException.class)
    public void canNegativeDepositAmount() {
        // Arrange
        DepositCalculator depositCalculator = new DepositCalculator();

        // Act & Assert
        depositCalculator.setDepositAmount(-500);
    }

    @Test
    public void canGetDepositAmount() {
        // Arrange
        DepositCalculator depositCalculator = new DepositCalculator();

        // Act
        depositCalculator.setDepositAmount(500);

        // Assert
        assertEquals(500, depositCalculator.getDepositAmount(), delta);
    }

    @Test
    public void canGetTermPlacement() {
        // Arrange
        DepositCalculator depositCalculator = new DepositCalculator();

        // Act
        depositCalculator.setTermPlacementInMonths(10);

        // Assert
        assertEquals(10, depositCalculator.getTermPlacementInMonths());
    }

    @Test(expected = NumberFormatException.class)
    public void cannotSetTermPlacement() {
        // Arrange
        DepositCalculator depositCalculator = new DepositCalculator();

        // Act & Assert
        depositCalculator.setTermPlacementInMonths(-5);
    }

    @Test(expected = NumberFormatException.class)
    public void cannotSetInterestRate() {
        // Arrange
        DepositCalculator depositCalculator = new DepositCalculator();

        // Act & Assert
        depositCalculator.setInterestRate(-5);
    }

    @Test
    public void canGetInterestRate() {
        // Arrange
        DepositCalculator depositCalculator = new DepositCalculator();

        // Act
        depositCalculator.setInterestRate(13);

        // Assert
        assertEquals(13, depositCalculator.getInterestRate(), delta);
    }

    @Test
    public void canCalculateRevenueWhenAddToDeposit() {
        // Arrange
        DepositCalculator depositCalculator = new DepositCalculator();

        // Act
        double revenue = depositCalculator.calculateRevenue();

        // Assert
        assertEquals(758099.654, revenue, delta);
    }

    @Test
    public void canCalculateRevenueWhenPayOutDeposit() {
        // Arrange
        DepositCalculator depositCalculator = new DepositCalculator();

        // Act
        depositCalculator.setAccruedInterest(AccruedInterest.payOut);
        double revenue = depositCalculator.calculateRevenue();

        // Assert
        assertEquals(756000, revenue, delta);
    }

    @Test
    public void canGetAccruedInterest() {
        // Arrange
        DepositCalculator depositCalculator = new DepositCalculator();

        // Assert
        assertEquals(AccruedInterest.addToDeposit, depositCalculator.getAccruedInterest());
    }

    @Test
    public void canCalcRevenueWithCapitalizationTwoMonth() {
        // Arrange
        DepositCalculator depositCalculator = new DepositCalculator();

        // Act
        depositCalculator.setAccruedInterest(AccruedInterest.payOut);
        depositCalculator.setFrequencyOfCapitalization(FrequencyOfCapitalization.onceTwoMonth);
        double revenue = depositCalculator.calculateRevenue();

        // Assert
        assertEquals(756000, revenue, delta);
    }

    @Test
    public void canGetFrequencyOfCapitalization() {
        // Arrange & Act
        DepositCalculator depositCalculator = new DepositCalculator();

        // Assert
        assertEquals(FrequencyOfCapitalization.onceMonth,
            depositCalculator.getFrequencyOfCapitalization());
    }

    @Test
    public void canCalcRevenueWithCapitalizationQuarterly() {
        // Arrange
        DepositCalculator depositCalculator = new DepositCalculator();

        // Act
        depositCalculator.setAccruedInterest(AccruedInterest.payOut);
        depositCalculator.setFrequencyOfCapitalization(FrequencyOfCapitalization.quarterly);
        double revenue = depositCalculator.calculateRevenue();

        // Assert
        assertEquals(756000, revenue, delta);
    }

    @Test
    public void canCalcRevenueWithCapitalizationHalfYear() {
        // Arrange
        DepositCalculator depositCalculator = new DepositCalculator();

        // Act
        depositCalculator.setAccruedInterest(AccruedInterest.payOut);
        depositCalculator.setFrequencyOfCapitalization(FrequencyOfCapitalization.halfYear);
        double revenue = depositCalculator.calculateRevenue();

        // Assert
        assertEquals(756000, revenue, delta);
    }

    @Test
    public void canCalcRevenueWithCapitalizationHalfYearAndAddToDeposit() {
        DepositCalculator depositCalculator = new DepositCalculator();

        depositCalculator.setAccruedInterest(AccruedInterest.addToDeposit);
        depositCalculator.setFrequencyOfCapitalization(FrequencyOfCapitalization.halfYear);
        double revenue = depositCalculator.calculateRevenue();

        assertEquals(757120, revenue, delta);
    }

    @Test
    public void canCalcRevenueWithCapitalizationTwoMonthAndAddToDeposit() {
        // Arrange
        DepositCalculator depositCalculator = new DepositCalculator();

        // Act
        depositCalculator.setAccruedInterest(AccruedInterest.addToDeposit);
        depositCalculator.setFrequencyOfCapitalization(FrequencyOfCapitalization.onceTwoMonth);
        double revenue = depositCalculator.calculateRevenue();

        // Assert
        assertEquals(757900.185, revenue, delta);
    }

    @Test
    public void canCalcRevenueWithCapitalizationQuarterlyAndAddToDeposit() {
        // Arrange
        DepositCalculator depositCalculator = new DepositCalculator();

        // Act
        depositCalculator.setAccruedInterest(AccruedInterest.addToDeposit);
        depositCalculator.setFrequencyOfCapitalization(FrequencyOfCapitalization.quarterly);
        double revenue = depositCalculator.calculateRevenue();

        // Assert
        assertEquals(757702.512, revenue, delta);
    }

    @Test
    public void canGetIncome() {
        // Arrange
        DepositCalculator depositCalculator = new DepositCalculator();

        // Act
        depositCalculator.setAccruedInterest(AccruedInterest.addToDeposit);
        depositCalculator.setFrequencyOfCapitalization(FrequencyOfCapitalization.quarterly);
        depositCalculator.calculateRevenue();

        // Assert
        assertEquals(57702.512, depositCalculator.getIncome(), delta);
    }

    @Test
    public void canUseChargesWhenOneMonth() {
        FrequencyOfCapitalization freqCapital = FrequencyOfCapitalization.onceMonth;

        boolean charges = freqCapital.useCharges(1);

        assertTrue(charges);
    }

    @Test
    public void canUseChargesWhenTwoMonth() {
        FrequencyOfCapitalization freqCapital = FrequencyOfCapitalization.onceTwoMonth;

        boolean charges = freqCapital.useCharges(2);

        assertTrue(charges);
    }

    @Test
    public void cannotUseCharges() {
        FrequencyOfCapitalization freqCapital = FrequencyOfCapitalization.quarterly;

        boolean charges = freqCapital.useCharges(2);

        assertFalse(charges);
    }

}
