package ru.unn.agile.binarysearch.model;

public class BadArrayException extends Exception {
    public BadArrayException(final String errorMessage) {
        super(errorMessage);
    }
}
