package ru.unn.agile.lengthconverter.viewmodel;

enum Status {

    READY("Press 'Converting'"),
    WAITING("Please provide input data"),
    INCORRECT_FORMAT("Incorrect format"),
    SUCCESS("Success"),
    ERROR("Converting error");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
