package ru.unn.agile.romannumberconverter.model.errorhandling;

public class RomanIncorrectValueExeption extends RuntimeException {
    public RomanIncorrectValueExeption() {
        super("Roman number value is incorrect.");
    }
}
