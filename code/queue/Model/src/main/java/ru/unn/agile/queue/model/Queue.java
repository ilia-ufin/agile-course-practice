package ru.unn.agile.queue.model;

import ru.unn.agile.queue.model.errorhandling.EmptyQueueException;

import java.util.LinkedList;
import java.util.List;

public final class Queue<E> {

    private List<E> container;

    public Queue() {
        container = new LinkedList<E>();
    }

    public void push(final E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element should be initialized");
        }

        container.add(element);
    }

    public E pop() {
        checkNotEmpty();

        return container.remove(0);
    }

    public E getHead() {
        checkNotEmpty();

        return container.get(0);
    }

    public E getTail() {
        checkNotEmpty();

        return container.get(container.size() - 1);
    }

    public boolean isEmpty() {
        return container.isEmpty();
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
    }

    public String toString() {
        return container.toString();
    }

    public void clear() {
        container.clear();
    }
}
