package ru.unn.agile.caesarcipher.viewmodel;

public class LogMessage {

    public enum Message {
    INPUT(" Input value to characters: %s encoder digit: %s"),
    FINISHED(" Encoded: characters: %s encoder digit: %s, result: %s");

    private final String description;

    Message(final String description) {
        this.description = description;

    }

    public String getDescription() {
        return description;
    }
};
}
