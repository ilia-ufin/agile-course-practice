package ru.unn.agile.stringcalculator.model.errorhandling;

public class NegativeNumberException extends ArithmeticException {
    public NegativeNumberException(final String message) {
        super("Negative not allowed: " + message);
    }
}
