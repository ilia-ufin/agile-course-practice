package ru.unn.agile.dijkstra.viewModel;

public class Status {
    private StatusType type;
    private String message;

    public Status(final StatusType type, final String message) {
        this.type = type;
        this.message = message;
    }

    public Status(final StatusType type) {
        this.type = type;
        this.message = type.getDefaultMessage();
    }

    public StatusType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
