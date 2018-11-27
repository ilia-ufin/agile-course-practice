package ru.unn.agile.mathstatistics.viewmodel;

public enum Operation {
    EXPECTED_VALUE("Expected value"),
    DISPERSION("Dispersion"),
    INITIAL_MOMENT("Initial moment"),
    CENTRAL_MOMENT("Central moment");
    private String operationName;

    Operation(final String name) {
        this.operationName = name;
    }

    public String toString() {
        return operationName;
    }
}
