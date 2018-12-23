package ru.unn.agile.caesarcipher.viewmodel;

public enum LogMessage {
    INPUT(" Input value to characters: %s encoder digit: %s"),
    FINISHED(" Encoded: characters: %s encoder digit: %s, result: %s");

    private final String description;

    LogMessage(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
};

