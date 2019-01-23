package ru.unn.agile.matrix.model.exceptions;

public class IncorrectMatrixIndexException extends RuntimeException {
    public IncorrectMatrixIndexException() {

        super("Matrix index is out of bounds ");
    }
}

