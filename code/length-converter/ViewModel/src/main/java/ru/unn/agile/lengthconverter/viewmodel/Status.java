package ru.unn.agile.lengthconverter.viewmodel;

enum Status {

    READY("Press 'Convert' or Enter"),
    WAITING("Please provide input data"),
    BAD_FORMAT("Bad format");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
