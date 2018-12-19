package ru.unn.agile.lengthconverter.viewmodel;

enum Status {

    READY("Press 'Convert' or Enter");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
