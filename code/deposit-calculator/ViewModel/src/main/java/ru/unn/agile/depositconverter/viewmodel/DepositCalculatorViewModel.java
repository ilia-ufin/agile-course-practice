package ru.unn.agile.depositconverter.viewmodel;

import ru.unn.agile.depositconverter.model.AccruedInterest;
import ru.unn.agile.depositconverter.model.DepositCalculator;
import ru.unn.agile.depositconverter.model.FrequencyOfCapitalization;

import java.util.List;
import java.util.Locale;

public class DepositCalculatorViewModel {
    private static final double HUNDRED = 100;

    private DepositCalculator model;
    private String depositAmount;
    private String termPlacement;
    private String interestRate;
    private AccruedInterest accruedInterest;
    private FrequencyOfCapitalization frequencyOfCapitalization;
    private String revenue;
    private String income;
    private boolean isCalculateButtonEnabled;
    private String status;
    private boolean isInputChanged;
    private ILogger logger;

    public final class Status {
        public static final String WAITING = "Please provide input data";
        public static final String READY = "Press 'Calculate'";
        public static final String SUCCESS = "Success";
        public static final String BAD_FORMAT_DEPOSIT_AMOUNT =
                "Error format. Incorrect Deposit Amount";
        public static final String BAD_FORMAT_TERM_PLACEMENT =
                "Error format. Incorrect Term Placement";
        public static final String BAD_FORMAT_INTEREST_RATE =
                "Error format. Incorrect Interest Rate";

        private Status() {

        }
    }

    public DepositCalculatorViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }

        this.logger = logger;

        model = new DepositCalculator();

        depositAmount = "";
        termPlacement = "";
        interestRate = "";

        accruedInterest = model.getAccruedInterest();
        frequencyOfCapitalization = model.getFrequencyOfCapitalization();

        isCalculateButtonEnabled = false;
        status = Status.WAITING;
        isInputChanged = true;
    }

    public final class LogMessages {
        public static final String CALCULATE_BUTTON = "Calculate. ";
        public static final String EDITING_ARGUMENT_UPDATED = "Updated input. ";
        public static final String ACCRUED_INTEREST_WAS_CHANGED = "Accrued Interest choose ";
        public static final String FREQUENCY_OF_CAPITALIZATION_WAS_CHANGED =
                "Frequency Of Capitalization choose ";
        public static final String RESULT_DEPOSIT_CALCULATION = "Deposit calculation result.";

        public static final String DEPOSIT_AMOUNT = "Deposit Amount = ";
        public static final String TERM_PLACEMENT = "Term Placement = ";
        public static final String INTEREST_RATE = "Interest Rate = ";
        public static final String ACCRUED_INTEREST = "Accrued Interest = ";
        public static final String FREQUENCY_OF_CAPITALIZATION = "Frequency Of Capitalization = ";
        public static final String REVENUE = "Revenue = ";
        public static final String INCOME = "Income = ";

        private LogMessages() { }
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    private void logInputParams() {
        if (!isInputChanged) {
            return;
        }

        logger.log(editingFinishedLogMessage());
        isInputChanged = false;
    }

    public void focusLost() {
        logInputParams();
    }

    private String editingFinishedLogMessage() {
        return LogMessages.EDITING_ARGUMENT_UPDATED
                + LogMessages.DEPOSIT_AMOUNT + depositAmount + "; "
                + LogMessages.TERM_PLACEMENT + termPlacement + "; "
                + LogMessages.INTEREST_RATE + interestRate + "; ";
    }

    private String calculateLogMessage() {
        return LogMessages.CALCULATE_BUTTON + "Arguments: "
                + LogMessages.DEPOSIT_AMOUNT + depositAmount + "; "
                + LogMessages.TERM_PLACEMENT + termPlacement + "; "
                + LogMessages.INTEREST_RATE + interestRate + "; \n"
                + LogMessages.ACCRUED_INTEREST + accruedInterest + "; "
                + LogMessages.FREQUENCY_OF_CAPITALIZATION + frequencyOfCapitalization + ".";
    }
    public String resultLogMessage() {
        return LogMessages.RESULT_DEPOSIT_CALCULATION + " "
                + LogMessages.REVENUE + revenue + "; "
                + LogMessages.INCOME + income + "; ";
    }

    private double round(final double roundedNum) {
        return Math.round(roundedNum * HUNDRED) / HUNDRED;
    }

    public void checkCountFields() {
        status = Status.READY;
        isCalculateButtonEnabled = isDepositAmountCorrect()
                && isTermPlacementCorrect()
                && isInterestRate();
    }

    public boolean isCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
    }

    public boolean isDepositAmountCorrect() {
        if ("".equals(depositAmount)) {
            isCalculateButtonEnabled = false;
            status = Status.WAITING;
            return false;
        }
        try {
            if (Double.parseDouble(depositAmount) < 0) {
                isCalculateButtonEnabled = false;
                status = Status.BAD_FORMAT_DEPOSIT_AMOUNT;
                return  false;
            }
        } catch (NumberFormatException e) {
            isCalculateButtonEnabled = false;
            status = Status.BAD_FORMAT_DEPOSIT_AMOUNT;
            return false;
        }
        return true;
    }

    public boolean isTermPlacementCorrect() {
        if ("".equals(termPlacement)) {
            isCalculateButtonEnabled = false;
            status = Status.WAITING;
            return false;
        }
        try {
            if (Integer.parseInt(termPlacement) < 0) {
                isCalculateButtonEnabled = false;
                status = Status.BAD_FORMAT_TERM_PLACEMENT;
                return false;
            }
        } catch (NumberFormatException e) {
            isCalculateButtonEnabled = false;
            status = Status.BAD_FORMAT_TERM_PLACEMENT;
            return false;
        }
        return true;
    }

    public boolean isInterestRate() {
        if ("".equals(interestRate)) {
            isCalculateButtonEnabled = false;
            status = Status.WAITING;
            return false;
        }
        try {
            if (Integer.parseInt(interestRate) < 0 || Integer.parseInt(interestRate) >= HUNDRED) {
                isCalculateButtonEnabled = false;
                status = Status.BAD_FORMAT_INTEREST_RATE;
                return false;
            }
        } catch (NumberFormatException e) {
            isCalculateButtonEnabled = false;
            status = Status.BAD_FORMAT_INTEREST_RATE;
            return false;
        }
        return true;
    }

    public void calculate() {
        if (!isCalculateButtonEnabled) {
            return;
        }
        logger.log(calculateLogMessage());
        model.setDepositAmount(Double.parseDouble(depositAmount));
        model.setTermPlacementInMonths(Integer.parseInt(termPlacement));
        model.setInterestRate(Double.parseDouble(interestRate));
        model.setFrequencyOfCapitalization(frequencyOfCapitalization);
        model.setAccruedInterest(accruedInterest);

        revenue = String.format(Locale.ROOT, "%.2f", round(model.calculateRevenue()));
        income = String.format(Locale.ROOT, "%.2f", round(model.getIncome()));
        logger.log(resultLogMessage());
        status = Status.SUCCESS;
    }

    public String getRevenueWhenAddToDeposit() {
        return revenue;
    }

    public String getDepositAmount() {
        return depositAmount;
    }

    public String getTermPlacement() {
        return termPlacement;
    }

    public String getStatus() {
        return status;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setTermPlacement(final String inTermPlacement) {
        if (inTermPlacement.equals(this.termPlacement)) {
            return;
        }
        this.termPlacement = inTermPlacement;
        isInputChanged = true;
    }

    public void setDepositAmount(final String inDepositAmount) {
        if (inDepositAmount.equals(this.depositAmount)) {
            return;
        }
        this.depositAmount = inDepositAmount;
        isInputChanged = true;
    }

    public void setInterestRate(final String inInterestRate) {
        if (inInterestRate.equals(this.interestRate)) {
            return;
        }
        this.interestRate = inInterestRate;
        isInputChanged = true;
    }

    public AccruedInterest getAccruedInterest() {
        return accruedInterest;
    }

    public void setAccruedInterest(final String acInt) {
        if (!(acInt.equals(String.valueOf(accruedInterest)))) {
            logger.log(LogMessages.ACCRUED_INTEREST_WAS_CHANGED
                    + "[" + acInt + "]");
            if ("addToDeposit".equals(acInt)) {
                accruedInterest = AccruedInterest.addToDeposit;
            } else {
                accruedInterest = AccruedInterest.payOut;
            }
        }
    }

    public FrequencyOfCapitalization getFrequencyOfCapitalization() {
        return frequencyOfCapitalization;
    }

    public void setFrequencyOfCapitalization(final String frequency) {
        if (!(frequency.equals(String.valueOf(frequencyOfCapitalization)))) {
            logger.log(LogMessages.FREQUENCY_OF_CAPITALIZATION_WAS_CHANGED
                    + "[" + frequency + "]");
            try {
                frequencyOfCapitalization = FrequencyOfCapitalization.valueOf(frequency);
            } catch (IllegalArgumentException e) {
                frequencyOfCapitalization = model.getFrequencyOfCapitalization();
            }
        }
    }

    public String getIncomeViewModel() {
        return income;
    }
}
