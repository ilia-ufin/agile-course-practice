package ru.unn.agile.caesarcipher.viewmodel;

import ru.unn.agile.caesarcipher.model.CaesarCipher;

public class ViewModel {
    private String inputTextBoxValue;
    private String offsetTextBoxValue;
    private String status;
    private String caesarCipher = "";

    private static final String DIGITS_ONLY_REGEX = "\\d+";
    private static final String READY = "Correct";
    private static final String BAD_INPUT = "Input correct value";

    private boolean codeButtonEnabled = false;

    public ViewModel() {
        inputTextBoxValue = "";
        offsetTextBoxValue = "";
        status = "";
    }

    public String getStatus() {
        return status;
    }

    public boolean isCodeButtonEnabled() {
        return codeButtonEnabled;
    }

    public void setTextBoxs(final String inputValue, final String offsetValue) {
        if ("".equals(offsetValue)) {
            codeButtonEnabled = false;
            status = BAD_INPUT;
            return;
        }
        if (!offsetValue.matches(DIGITS_ONLY_REGEX)) {
            codeButtonEnabled = false;
            status = BAD_INPUT;
            return;
        }

        this.inputTextBoxValue = inputValue;
        this.offsetTextBoxValue = offsetValue;
        codeButtonEnabled = true;
        status = READY;
    }

    public void codeCaesar() {
        caesarCipher = CaesarCipher.encode(inputTextBoxValue, Integer.parseInt(offsetTextBoxValue));
    }

    public String getCaesarCipher() {
        return caesarCipher;
    }
}
