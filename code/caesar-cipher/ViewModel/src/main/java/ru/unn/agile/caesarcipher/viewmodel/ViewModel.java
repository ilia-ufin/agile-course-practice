package ru.unn.agile.caesarcipher.viewmodel;
import ru.unn.agile.caesarcipher.model.CaesarCipher;

import java.util.List;

public class ViewModel {
    private String inputTextBoxValue = "";
    private String offsetTextBoxValue = "";
    private String status = "";
    private String caesarCipher = "";

    private static final String DIGITS_ONLY_REGEX = "\\d+";
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
        status = StatusMessage.WAITING.getDescription();
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
            status = StatusMessage.BAD_INPUT.getDescription();
            return;
        }
        if (!offsetValue.matches(DIGITS_ONLY_REGEX)) {
            codeButtonEnabled = false;
            status = StatusMessage.BAD_INPUT.getDescription();
            return;
        }
        if (offsetValue.length() > LIMITED) {
            codeButtonEnabled = false;
            status = StatusMessage.LIMITED_DIGIT.getDescription();
            return;
        }
        codeButtonEnabled = true;
        status = StatusMessage.READY.getDescription();
    }

    public void codeCaesar() {
        caesarCipher = CaesarCipher.encode(inputTextBoxValue, Integer.parseInt(offsetTextBoxValue));
        status = StatusMessage.SUCCESSFUL.getDescription();
        logger.log(editingFinishMessage());
    }

    public String getCaesarCipher() {
        return caesarCipher;
    }

    public String getInputTextBoxValue() {
        return inputTextBoxValue; }

    public String getOffsetTextBoxValue() {
        return offsetTextBoxValue; }

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
        return String.format(LogMessage.INPUT.getDescription(), inputTextBoxValue,
                offsetTextBoxValue);
    }

    public String editingFinishMessage() {
        return String.format(LogMessage.FINISHED.getDescription(), inputTextBoxValue,
                offsetTextBoxValue, caesarCipher);
    }
}
