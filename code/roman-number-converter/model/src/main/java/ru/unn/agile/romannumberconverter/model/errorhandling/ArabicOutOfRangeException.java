package ru.unn.agile.romannumberconverter.model.errorhandling;

public class ArabicOutOfRangeException extends RuntimeException {
    public ArabicOutOfRangeException() {
        super("Arabic number is out of range [1, 3999].");
    }
}
