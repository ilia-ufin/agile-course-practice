package ru.unn.agile.mortgagecalculator.viewmodel.legacy;

import ru.unn.agile.mortgagecalculator.model.MortgageCalculator;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Locale;

public class ViewModel {
    private static final double MAX_APARTMENT_PRICE = 1000000000;
    private static final int MAX_INTEREST_RATE = 30;
    private static final int MAX_TERM = 360;
    private static final int MAX_COUNT_CHAR_APARTMENT_PRICE = 13;
    private static final int MAX_COUNT_CHAR_TERM = 3;
    private static final int MAX_COUNT_CHAR_RATE = 5;

    private String apartmentPrice;
    private String initialPayment;
    private String interestRate;
    private String termMortgage;
    private String status;
    private String fullPriceMortgage;

    private boolean isCalculateButtonEnabled;

    private DefaultTableModel tableModel;
    private Object[] namesColumns =
            new String[]{"Month", "Interest charges", "Amount of payment"};

    private ILogger logger;

    public ViewModel(final ILogger logger) {
        this.logger = logger;
        apartmentPrice = "";
        initialPayment = "";
        interestRate = "";
        termMortgage = "";
        status = Status.COUNT_WAITING;
        isCalculateButtonEnabled = false;
        tableModel = new DefaultTableModel();
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    public final class Status {
        public static final String COUNT_WAITING =
                "Please provide apartment price and count period";

        public static final String READY_CALCULATE =
                "Press 'Calculate' button";

        public static final String BAD_APARTMENT_PRICE_FORMAT_SIGN =
                "Apartment price must be more 0";

        public static final String BAD_APARTMENT_PRICE_FORMAT_NUMBERS =
                "Apartment price too much";

        public static final String BAD_APARTMENT_PRICE_COUNT_FORMAT =
                "Wrong format of apartment price input";

        public static final String BAD_INITIAL_PAYMENT_FORMAT_SIGN =
                "Initial payment must be more 0";

        public static final String BAD_INITIAL_PAYMENT_FORMAT_NUMBERS =
                "Initial payment too much, should be less than apartment price";

        public static final String BAD_INITIAL_PAYMENT_COUNT_FORMAT =
                "Wrong format of initial payment input";

        public static final String BAD_TERM_FORMAT_NUMBERS =
                "Term mortgage must be between 1 and 360";

        public static final String BAD_TERM_FORMAT =
                "Wrong format of term mortgage input";

        public static final String BAD_INTEREST_RATE_FORMAT_NUMBERS =
                "Interest rate must be between 0 and 30";

        public static final String BAD_INTEREST_RATE_FORMAT =
                "Wrong format of interest rate input";

        public static final String FULL_PRICE_MORTGAGE =
                "This your full price mortgage";

        private Status() {
        }
    }

    public void checkCountFields() {
        status = Status.READY_CALCULATE;
        isCalculateButtonEnabled = isApartmentPriceCorrect()
                && isInitialPaymentCorrect()
                && isInterestRateCorrect()
                && isTermMortgageCorrect();

        if (isCalculateButtonEnabled) {
            logger.log("Button is enabled");
        }
    }

    public void calculateFullPriceMortgage() {
        if (!isCalculateButtonEnabled) {
            return;
        }
        MortgageCalculator calculator = new MortgageCalculator();

        calculator.setFullCostOfApartment(Double.parseDouble(apartmentPrice));
        calculator.setInitialPayment(Double.parseDouble(initialPayment));
        calculator.setInterestRate(Double.parseDouble(interestRate));
        calculator.setDateOfMortgage(Integer.parseInt(termMortgage));
        calculator.setAccruedInterestArray();
        calculator.setFullPriceArray();
        fullPriceMortgage = getMoneyFormatInFullPriceValue(calculator);
        fillingTableModelWithData(calculator);
        status = Status.FULL_PRICE_MORTGAGE;

        logger.log("calculateFullPriceMortgage");
    }

    public void fillingTableModelWithData(final MortgageCalculator isCalculator) {
        int numberOfMonths = Integer.parseInt(termMortgage);

        String[] monthsNumbersAsArray = new String[numberOfMonths];

        for (int indexMonth = 0; indexMonth < numberOfMonths; indexMonth++) {
            monthsNumbersAsArray[indexMonth] = String.valueOf(indexMonth + 1);
        }

        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);

        tableModel.addColumn(namesColumns[0], monthsNumbersAsArray);
        tableModel.addColumn(namesColumns[1], isCalculator.getAccruedInterestArrayStrings());
        tableModel.addColumn(namesColumns[2], isCalculator.getFullPriceArrayStrings());
    }

    private String getMoneyFormatInFullPriceValue(final MortgageCalculator isCalculator) {
        return String.format(Locale.ROOT, "%.2f", isCalculator.getFullPriceForMortgage());
    }

    private boolean isApartmentPriceCorrect() {

        logger.log("isApartmentPriceCorrect");

        if (apartmentPrice.length() > MAX_COUNT_CHAR_APARTMENT_PRICE) {
            status = Status.BAD_APARTMENT_PRICE_FORMAT_NUMBERS;
            return false;
        }
        try {
            if (Double.parseDouble(apartmentPrice) < 0) {
                status = Status.BAD_APARTMENT_PRICE_FORMAT_SIGN;
                return false;
            }
            if (Double.parseDouble(apartmentPrice) > MAX_APARTMENT_PRICE) {
                status = Status.BAD_APARTMENT_PRICE_FORMAT_NUMBERS;
                return false;
            }
        } catch (NumberFormatException e) {
            status = Status.BAD_APARTMENT_PRICE_COUNT_FORMAT;
            return false;
        }

        return true;
    }

    private boolean isInitialPaymentCorrect() {

        logger.log("isInitialPaymentCorrect");

        if (initialPayment.length() > MAX_COUNT_CHAR_APARTMENT_PRICE) {
            status = Status.BAD_INITIAL_PAYMENT_FORMAT_NUMBERS;
            return false;
        }
        try {
            if (Double.parseDouble(initialPayment) < 0) {
                status = Status.BAD_INITIAL_PAYMENT_FORMAT_SIGN;
                return false;
            }
            if (Double.parseDouble(initialPayment) >= Double.parseDouble(apartmentPrice)) {
                status = Status.BAD_INITIAL_PAYMENT_FORMAT_NUMBERS;
                return false;
            }
        } catch (NumberFormatException e) {
            status = Status.BAD_INITIAL_PAYMENT_COUNT_FORMAT;
            return false;
        }

        return true;
    }

    private boolean isInterestRateCorrect() {

        logger.log("isInterestRateCorrect");

        if (interestRate.length() > MAX_COUNT_CHAR_RATE) {
            status = Status.BAD_INTEREST_RATE_FORMAT_NUMBERS;
            return false;
        }
        try {
            if (Double.parseDouble(interestRate) < 0) {
                status = Status.BAD_INTEREST_RATE_FORMAT_NUMBERS;
                return false;
            }
            if (Double.parseDouble(interestRate) > MAX_INTEREST_RATE) {
                status = Status.BAD_INTEREST_RATE_FORMAT_NUMBERS;
                return false;
            }
        } catch (NumberFormatException e) {
            status = Status.BAD_INTEREST_RATE_FORMAT;
            return false;
        }

        return true;
    }

    private boolean isTermMortgageCorrect() {

        logger.log("isTermMortgageCorrect");

        if (termMortgage.length() > MAX_COUNT_CHAR_TERM) {
            status = Status.BAD_TERM_FORMAT_NUMBERS;
            return false;
        }
        try {
            if (Integer.parseInt(termMortgage) <= 0) {
                status = Status.BAD_TERM_FORMAT_NUMBERS;
                return false;
            }
            if (Integer.parseInt(termMortgage) > MAX_TERM) {
                status = Status.BAD_TERM_FORMAT_NUMBERS;
                return false;
            }
        } catch (NumberFormatException e) {
            status = Status.BAD_TERM_FORMAT;
            return false;
        }

        return true;
    }

    public boolean isCalculateButtonEnable() {
        return isCalculateButtonEnabled;
    }

    public void setApartmentPrice(final String isApartmentPrice) {
        this.apartmentPrice = isApartmentPrice;
    }

    public void setInitialPayment(final String isInitialPayment) {
        this.initialPayment = isInitialPayment;
    }

    public void setInterestRate(final String isInterestRate) {
        this.interestRate = isInterestRate;
    }

    public void setTermMortgage(final String isTermMortgage) {
        this.termMortgage = isTermMortgage;
    }

    public String getApartmentPrice() {
        return apartmentPrice;
    }

    public String getInitialPayment() {
        return initialPayment;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public String getTermMortgage() {
        return termMortgage;
    }

    public String getStatus() {
        return status;
    }

    public String getFullPriceMortgage() {
        return fullPriceMortgage;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
