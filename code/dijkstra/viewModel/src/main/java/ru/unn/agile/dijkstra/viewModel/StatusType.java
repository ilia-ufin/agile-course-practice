package ru.unn.agile.dijkstra.viewModel;

public enum StatusType {
    BAD_REQUEST("Bad format"),
    SUCCESS("Success");

    private final String defaultMessage;
    StatusType(final String name) {
        this.defaultMessage = name;
    }
    public String getDefaultMessage() {
        return defaultMessage;
    }
}
