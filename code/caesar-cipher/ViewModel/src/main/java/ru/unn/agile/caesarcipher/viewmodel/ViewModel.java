package ru.unn.agile.caesarcipher.viewmodel;
import ru.unn.agile.caesarcipher.model.CaesarCipher;

import java.util.List;

public class ViewModel {
    private String inputTextBoxValue;
    private String offsetTextBoxValue;
    private String status;
    private String caesarCipher = "";

    private static final String DIGITS_ONLY_REGEX = "\\d+";
    private static final String STATUS_READY = "Correct input";
    private static final String STATUS_BAD_INPUT = "Input correct value";
    private static final String STATUS_SUCCESSFUL = "Successful";
    private static final String STATUS_LIMITED_DIGIT = "You can enter up to 12 digits";
    public static final String LOG_INPUT = " Input value to ";
    public static final String LOG_FINISHED = " Encoded: ";

    private static final int LIMITED = 12;
    private boolean codeButtonEnabled = false;
    private boolean isInputChanged = true;
    private ILogger logger;

    public ViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
        inputTextBoxValue = "";
        offsetTextBoxValue = "";
        caesarCipher = "";
        status = "Waiting";
        codeButtonEnabled = false;
        isInputChanged = true;
    }

    public boolean isCodeButtonEnabled() {
        return codeButtonEnabled;
    }

    public void setTextBoxInput(final String inputValue) {
        this.inputTextBoxValue = inputValue;
        isInputChanged = true;
    }

    public void setTextBoxOffset(final String offsetValue) {
        isInputChanged = true;
        this.offsetTextBoxValue = offsetValue;

        if ("".equals(offsetValue)) {
            codeButtonEnabled = false;
            status = STATUS_BAD_INPUT;
            return;
        }
        if (!offsetValue.matches(DIGITS_ONLY_REGEX)) {
            codeButtonEnabled = false;
            status = STATUS_BAD_INPUT;
            return;
        }
        if (offsetValue.length() > LIMITED) {
            codeButtonEnabled = false;
            status = STATUS_LIMITED_DIGIT;
            return;
        }
        codeButtonEnabled = true;
        status = STATUS_READY;
    }

    public void codeCaesar() {
        caesarCipher = CaesarCipher.encode(inputTextBoxValue, Integer.parseInt(offsetTextBoxValue));
        status = STATUS_SUCCESSFUL;
        logger.log(editingFinishMessage());
    }

    public String getCaesarCipher() {
        return caesarCipher;
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    public String getStatus() {
        return status;
    }

    public void logInputParams() {
        if (!isInputChanged) {
            return;
        }
        logger.log(editingInputMessage());
        isInputChanged = false;
    }

    public String editingInputMessage() {
        return LOG_INPUT + "characters: " + inputTextBoxValue
               + ", encoder digit: " + offsetTextBoxValue;
    }

    public String editingFinishMessage() {
        return LOG_FINISHED + "characters: " + inputTextBoxValue + ", encoder digit: "
                + offsetTextBoxValue + ", result: " + caesarCipher;
    }
}
