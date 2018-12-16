package ru.unn.agile.matrix.model.exceptions;

public class IncompatibleSizeMatrixException extends RuntimeException {
    public IncompatibleSizeMatrixException() {

        super("Processing matrices does not have compatible size");
    }
}
