package ru.unn.agile.stringcalculator.model.errorhandling;

public class NotANumberException extends NumberFormatException {
    public NotANumberException(final String message) {
        super(message);
    }
}
