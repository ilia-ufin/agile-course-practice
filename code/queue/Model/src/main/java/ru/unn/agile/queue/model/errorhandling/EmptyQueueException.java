package ru.unn.agile.queue.model.errorhandling;

public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException(final String message) {
        super(message);
    }
}
