package ru.unn.agile.ConverterTemperatures.viewmodel;

enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Convert' or Enter"),
    BAD_FORMAT("Bad format"),
    ERROR("Converting error: input value out of range"),
    SUCCESS("Success");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
