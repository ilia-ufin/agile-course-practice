package ru.unn.agile.numbersinwords.viewmodel;

import ru.unn.agile.numbersinwords.model.NumbersInWords;

import java.util.List;

public class NumbersInWordsViewModel {
    private String errorMessage = "";
    private String number = "";
    private String numberInWords = "";
    private boolean convertButtonEnabled = false;
    private static final int LIMITED = 12;

    public static final String LOG_MESSAGE_INPUT = "Input string set to: ";
    public static final String LOG_MESSAGE_CONVERT = "Convert button clicked with result: ";
    private ILogger logger;

    public NumbersInWordsViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    public boolean isConvertButtonEnabled() {
        return convertButtonEnabled;
    }

    public void setNumber(final String numbers) {
        logger.log(LOG_MESSAGE_INPUT + numbers);
        if (!isInputValid(numbers)) {
            return;
        }
        this.number = numbers;
        numberInWords = "";
        errorMessage = "";
        convertButtonEnabled = true;
    }

    private boolean isInputValid(final String numberInput) {
        if (numberInput.length() > LIMITED) {
            statusErrorHandling("You can enter up to 12 digits");
            return  false;
        }
        if ("".equals(numberInput)) {
            statusErrorHandling("");
            return false;
        }
        if (!numberInput.matches("\\d+")) {
            statusErrorHandling("Only digits, please");
            return false;
        }
        return true;
    }

    private void statusErrorHandling(final String message) {
        numberInWords = "";
        errorMessage = message;
        convertButtonEnabled = false;
    }

    public String getNumberInWords() {
        return numberInWords;
    }

    public void convert() {
        numberInWords = NumbersInWords.convert(Long.parseLong(number));
        logger.log(LOG_MESSAGE_CONVERT + numberInWords);
    }

    public List<String> getLogMessages() {
        return logger.getLog();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
