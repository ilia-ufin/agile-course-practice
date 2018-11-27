package ru.unn.agile.numbersinwords.viewmodel;

import ru.unn.agile.numbersinwords.model.NumbersInWords;

public class NumbersInWordsViewModel {
    private String errorMessage = "";
    private String number = "";
    private String numberInWords = "";
    private boolean convertButtonEnabled = false;
    private static final int LIMITED = 12;

    public boolean isConvertButtonEnabled() {
        return convertButtonEnabled;
    }

    public void setNumber(final String numbers) {
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
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
