package ru.unn.agile.currencyconverter.model.errorhandling;

public class CurrencyConverterException extends RuntimeException {
    public CurrencyConverterException(final String message) {
        super(message);
    }
}
