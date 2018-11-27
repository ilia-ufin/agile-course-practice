package ru.unn.agile.calculator.viewmodel;



enum UserMessages {
    WAIT_FOR_INPUT("Please input numbers"),
    READY("Press 'Calculate' button to see result"),
    INPUT_INVALID("Please check your input"),
    SUCCESS("Success");

    private final String name;

    UserMessages(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

